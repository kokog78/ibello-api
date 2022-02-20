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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

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
	 * @param <E> type of the enum constant
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
	
	private static String timestamp;
	private static String uuid;
	
	static {
		Date now = new Date();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		timestamp = fmt.format(now);
		uuid = UUID.randomUUID().toString();
	}
	
	/**
	 * <p>
	 * Returns a timestamp value. The timestamp does not change during the whole test run.
	 * It is generated at the beginning. With timestamp we can create unique (time-related) identifiers in a database.
	 * </p>
	 * <p>
	 * This method returns the first <code>length</code> characters of the timestamp. For example, the first 8 characters
	 * contains the year, the month and the day, the first 12 characters contains the hours and the minutes too.
	 * </p>
	 * @param length maximum length of the returned timestamp
	 * @return timestamp value as string
	 */
	public static String getTimestamp(int length) {
		if (length >= timestamp.length()) {
			return timestamp;
		} else {
			return timestamp.substring(0, length);
		}
	}
	
	/**
	 * Returns a UUID as string. This UUID is generated at the beginning of the test execution and does not change during the test run.
	 * @return the static UUID
	 */
	public static String getUUID() {
		return uuid;
	}
}
