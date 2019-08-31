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

/**
 * <p>
 * This interface specifies a test data loader which loads data from JSON files.
 * JSON files are describing a complex structure, therefore the loaded result is
 * also a complex java object.
 * </p>
 * <p>
 * With the {@link #withId(String)} method we can specify an optional identifier for the loaded JSON files.
 * </p>
 * <p>
 * The {@link #load()} method returns the loaded instance.
 * The {@link #loadString()} method returns the JSON content of the test data.
 * The {@link #openStream()} method opens a stream which contains this content.
 * </p>
 * @see TestDataBuilder#fromJson(Class)
 * @author Kornél Simon
 *
 */
public interface JsonTestDataBuilder<T> extends StringBasedBuilder {

	/**
	 * With this method we can specify an optional identifier for the loaded JSON files.
	 * The identifier should be part of the file name.
	 * @param testDataId an identifier of the loaded JSON file(s)
	 * @return this loader instance
	 */
	public JsonTestDataBuilder<T> withId(String testDataId);
	
	/**
	 * <p>
	 * After this method is called, this {@link JsonTestDataBuilder} instance will not join arrays anymore. Array properties
	 * loaded from higher priority JSON files will fully replace properties loaded from lower priority files.
	 * </p>
	 * <p>For example,
	 * if two JSON files contains array property with the same name, the value of the lowest priority is <code>[1, 2]</code> and the highest
	 * priority file contains <code>[3, 4]</code>, then the value of this property in the result will be <code>[3, 4]</code>.
	 * </p>
	 * @return this loader instance
	 */
	public JsonTestDataBuilder<T> doNotJoinArrays();
	
	/**
	 * <p>
	 * After this method is called, this {@link JsonTestDataBuilder} instance will not merge object properties. Every child object
	 * loaded from higher priority JSON files will fully replace properties loaded from lower priority files.
	 * </p>
	 * <p>
	 * For example,
	 * if two JSON files contains an object property with the same name, the value of the lowest priority is <code>{"b1": 1, "b2": 1}</code> and the highest
	 * priority file contains <code>{"b1": 2}</code>, then the value of this property in the result will be <code>{"b1": 2}</code>.
	 * </p>
	 * @return this loader instance
	 */
	public JsonTestDataBuilder<T> doNotMergeObjects();
	
	/**
	 * Loads the test data from JSON file(s), and convert it to a java object.
	 * @return the loaded test data as a java object
	 */
	public T load();
	
	/**
	 * Loads the test data from JSON file(s).
	 * If the test data cannot be found then returns <code>null</code>.
	 * @return the loaded test data as {@link String}
	 */
	public String loadString();
	
}
