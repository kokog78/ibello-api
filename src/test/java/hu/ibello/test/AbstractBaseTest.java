/*
 * Ark-Sys Kft. licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package hu.ibello.test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class AbstractBaseTest {

	protected void setField(Object owner, String fieldName, Object value) throws Exception {
		Field field;
		try {
			field = owner.getClass().getDeclaredField(fieldName);
		} catch (Exception ex) {
			field = owner.getClass().getSuperclass().getDeclaredField(fieldName);
		}
		boolean accessible = field.isAccessible();
		field.setAccessible(true);
		field.set(owner, value);
		field.setAccessible(accessible);
	}
	
	protected <W> void testMethodCalls(MethodCaller wrapper, Class<W> wrappedClass, Consumer<W> spyReceiver) throws Exception {
		for (Method wrappedMethod : wrappedClass.getDeclaredMethods()) {
			Method wrapperMethod = null;
			try {
				wrapperMethod = wrapper.getClass().getDeclaredMethod(wrappedMethod.getName(), wrappedMethod.getParameterTypes());
			} catch (Exception ex) {
				// does not exists
			}
			if (wrapperMethod==null) {
				try {
					wrapperMethod = wrapper.getClass().getSuperclass().getDeclaredMethod(wrappedMethod.getName(), wrappedMethod.getParameterTypes());
				} catch (Exception ex) {
					// does not exists
				}
			}
			if (wrapperMethod!=null) {
				System.out.println(String.format("checking %s", wrapperMethod));
				int argCount = wrappedMethod.getParameterTypes().length;
				Object[] args = new Object[argCount];
				for (int i=0; i<argCount; i++) {
					Class<?> argType = wrappedMethod.getParameterTypes()[i];
					if (String.class.isAssignableFrom(argType)) {
						args[i] = "str";
					} else if (List.class.isAssignableFrom(argType)) {
						args[i] = new ArrayList<>();
					} else if (Class.class.isAssignableFrom(argType)) {
						args[i] = String.class;
					} else {
						args[i] = Mockito.mock(argType);
					}
				}
				boolean[] result = new boolean[]{false};
				W spy = Mockito.mock(wrappedClass, new Answer<Object>() {
					@Override
					public Object answer(InvocationOnMock invocation) throws Throwable {
						if (invocation.getMethod().getName().equals(wrappedMethod.getName()) && args.length==invocation.getArguments().length) {
							result[0] = true;
							for (int j=0; j<args.length; j++) {
								if (!args[j].equals(invocation.getArguments()[j])) {
									result[0] = false;
									break;
								}
							}
						}
						return null;
					}
				});
				spyReceiver.accept(spy);
				wrapper.callMethod(wrapperMethod, args);
				if (!result[0]) {
					throw new AssertionError("Method does not calls method with same name: " + wrapperMethod);
				}
			}
		}
	}
	
}
