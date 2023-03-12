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

import java.util.List;

/**
 * <p>
 * This interface specifies a test data loader which loads data from CSV files.
 * CSV files are describing a tabular structure, therefore the loaded result is
 * a list of java objects.
 * </p>
 * <p>
 * With the {@link #withId(String)} method we can specify an optional identifier for the loaded CSV file.
 * </p>
 * <p>
 * The {@link #load()} method returns the loaded java list.
 * The {@link #loadString()} method returns the CSV content of the test data.
 * The {@link #openStream()} method opens a stream which contains this content.
 * </p>
 * <p>
 * By default, the file is loaded with UTF-8 character set. We can change this with the {@link #withCharset(java.nio.charset.Charset)}
 * method.
 * </p>
 * @see TestDataBuilder#fromCsv(Class)
 * @author Kornél Simon
 *
 */
public interface CsvTestDataBuilder<T> extends StringBasedBuilder<CsvTestDataBuilder<T>> {

	/**
	 * With this method we can specify an optional identifier for the loaded CSV file.
	 * The identifier should be part of the file name.
	 * @param testDataId an identifier of the loaded CSV file
	 * @return this loader instance
	 */
	public CsvTestDataBuilder<T> withId(String testDataId);
	
	/**
	 * Loads the test data from CSV file, and convert it to a list of java objects.
	 * The objects will be automatically injected with dependencies.
	 * If the test data cannot be found then returns <code>null</code>.
	 * @return the loaded test data as a java list
	 */
	public List<T> load();
	
	/**
	 * Loads the test data from CSV file.
	 * If the test data cannot be found then returns <code>null</code>.
	 * If the test data is coming from multiple files then the header of these files should be the same, otherwise a runtime exception will be thrown.
	 * @return the loaded test data as {@link String}
	 */
	public String loadString();
}
