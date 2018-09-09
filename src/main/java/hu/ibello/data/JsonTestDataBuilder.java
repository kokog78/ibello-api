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
 * This interface specifies a test data loader which loads data from a JSON files.
 * JSON files are describing a complex structure, therefore the loaded result is
 * also a complex java object.
 * </p>
 * <p>
 * With the {@link #withId(String)} method we can specify an optional identifier for the loaded JSON files.
 * </p>
 * <p>
 * The {@link #load()} method returns the loaded instance.
 * </p>
 * @see TestDataBuilder#fromJson(Class)
 * @author Kornél Simon
 *
 */
public interface JsonTestDataBuilder<T> {

	/**
	 * With this method we can specify an optional identifier for the loaded JSON files.
	 * The identifier should be part of the file name.
	 * @param testDataId an identifier of the loaded JSON file(s)
	 * @return this loader instance
	 */
	public JsonTestDataBuilder<T> withId(String testDataId);
	
	/**
	 * Loads the test data from JSON file(s).
	 * @return the loaded test data
	 */
	public T load();
	
}
