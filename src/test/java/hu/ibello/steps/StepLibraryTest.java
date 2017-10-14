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
package hu.ibello.steps;

import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;

import hu.ibello.steps.StepLibrary;
import hu.ibello.steps.StepLibraryTool;
import hu.ibello.test.AbstractBaseTest;
import hu.ibello.test.MethodCaller;

public class StepLibraryTest extends AbstractBaseTest {

	private MySteps steps;
	
	@Before
	public void init() throws Exception {
		steps = new MySteps();
	}
	
	@Test
	public void test_method_calls() throws Exception {
		testMethodCalls(steps, StepLibraryTool.class, (tool) -> {
			try {
				setField(steps, "tool", tool);
			} catch (Exception e) {
				throw new AssertionError("Cannot set tool", e);
			}
		});
	}
	
	public class MySteps extends StepLibrary implements MethodCaller {
		
		public void callMethod(Method method, Object[] args) throws Exception {
			method.invoke(this, args);
		}
	}
}
