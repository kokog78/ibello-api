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

import hu.ibello.core.Values;

/**
 * <p>
 * This interface specifies a test data loader which loads data from a java property file.
 * Because java property files are simple name-value maps, therefore the loaded result is
 * also a plain {@link Values} instance which stores key-value pairs.
 * </p>
 * <p>
 * The main method of this interface is the {@link #load()}. It returns the loaded {@link Values} instance.
 * The {@link #loadString()} method returns the property file content of the test data.
 * The {@link #openStream()} method opens a stream which contains this content.
 * </p>
 * @see TestDataBuilder#fromProperties(String)
 * @author Kornél Simon
 *
 */
public interface PropertiesTestDataBuilder extends TestDataBuilderBase {

	/**
	 * Loads the test data from java property file(s).
	 * @return the loaded test data
	 */
	public Values load();
	
	/**
	 * Loads the test data from java property file(s).
	 * If the test data cannot be found then returns <code>null</code>.
	 * @return the loaded test data as {@link String}
	 */
	public String loadString();
}
