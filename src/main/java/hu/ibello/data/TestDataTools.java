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
package hu.ibello.data;

import java.lang.reflect.Field;

import hu.ibello.core.Name;

/**
 * Utility methods for test data management.
 * @author Kornél Simon
 *
 */
public class TestDataTools {
	
	/**
	 * Translates an enumeration constant's name.
	 * <p>
	 * This method tries to find a {@link Name} annotation on the given constant.
	 * If the annotation present then the result will be it's value.
	 * Otherwise the constant's original name will be used.
	 * </p>
	 * @param item the enum constant
	 * @return the translated name of the constant
	 */
	public static <E extends Enum<?>> String getEnumName(E item) {
		try {
			Field field = item.getClass().getField(item.name());
			Name name = field.getAnnotation(Name.class);
			if (name != null) {
				return name.value();
			}
		} catch (Exception e) {
			// impossible error :-)
			e.printStackTrace();
		}
		return item.name();
	}
	
	/**
	 * Translates a class name.
	 * <p>
	 * This method tries to find a {@link Name} annotation on the given class.
	 * If the annotation present then the result will be it's value.
	 * Otherwise the simple name of the class will be used.
	 * </p>
	 * @param clazz the java class
	 * @return the translated name of the java class
	 */
	public static String getClassName(Class<?> clazz) {
		Name name = clazz.getAnnotation(Name.class);
		if (name != null) {
			return name.value();
		}
		return clazz.getSimpleName();
	}
}
