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
 * This class is designed for load test data from the filesystem.
 * Test data can be stored in java property file, or in JSON file.
 * According to the file format, we can start a loader operation with one of these methods:
 * <ul>
 * <li>{@link #fromProperties(String)}: data loading from java property file(s)</li>
 * <li>{@link #fromJson(Class)}: data loading from JSON file(s)</li>
 * <li>{@link #fromFile(String)}: data loading from any kind of file(s)</li>
 * </ul>
 * Data files are stored in the data directory. By default this is the <code>ibello/data</code>
 * folder, but we can change this with the <code>ibello.dir.data</code> configuration parameter.
 * @author Kornél Simon
 *
 */
public interface TestDataBuilder {

	/**
	 * <p>
	 * Returns an object which can be used to load test data from a java property file.
	 * For that, we need to specify a file name prefix. The name of the loaded file
	 * will start with that prefix, followed by a dot. The final extension of the file will
	 * be <code>.properties</code>. Between the prefix and the extension the file name may contain
	 * tags, separated by dashes. Examples:
	 * </p>
	 * <ul>
	 * <li><code>prefix.properties</code></li>
	 * <li><code>prefix.tag1.properties</code></li>
	 * <li><code>prefix.tag1-tag2.properties</code></li>
	 * </ul>
	 * <p>
	 * During the loading process, the loader creates a list of available and matching files,
	 * then it sorts them by precedence. At first, the first file will be loaded. If they are other matching files,
	 * then the second one will be loaded, and it's properties will override the properties of the first file.
	 * Other matching files are loaded in that order, and also update the previously loaded properties.
	 * </p>
	 * <p>
	 * Each loaded files should have the specified prefix. If a file name also have tags, then they will be validated
	 * against the current ibello tags. If a file has a tag which was <b>not</b> specified as an ibello tag, then
	 * that file will be <b>not</b> loaded. In other words, loaded files should not have unspecified tags.
	 * </p>
	 * <p>
	 * The first loaded file will be the one without tags (if any). The remaining files will be sorted by the number of tags
	 * and the tags itself (in alphabetical order). Examples:
	 * </p>
	 * <ul>
	 * <li><code>prefix.a.properties</code> will be loaded before <code>prefix.b.properties</code></li>
	 * <li><code>prefix.c.properties</code> will be loaded before <code>prefix.a-b.properties</code></li>
	 * </ul>
	 * <p>
	 * If two loaded files have the same property, then the latest file's property will be in the final result.
	 * </p>
	 * <p>
	 * The files can be placed into sub-directories, the loader will find them automatically.
	 * </p>
	 * @param fileNamePrefix file name prefix, cannot be <code>null</code>
	 * @return a loader object which loads test data from a java property file
	 */
	public PropertiesTestDataBuilder fromProperties(String fileNamePrefix);

	/**
	 * <p>
	 * Returns an object which can be used to load test data from a JSON file.
	 * For that we must specify a java class which will receive all data from the JSON file.
	 * </p>
	 * <p>
	 * The name of the file starts with the lower-case name of the java class (without package name).
	 * It may be followed by a dash sign and an identifier. Then comes a dot. At this position the file name may
	 * contain tags, separated by dash signs. The extension of the file is always <code>.json</code>. Examples:
	 * </p>
	 * <ul>
	 * <li><code>myclass.json</code></li>
	 * <li><code>myclass.tag1.json</code></li>
	 * <li><code>myclass-id.tag1.json</code></li>
	 * <li><code>myclass-id.tag1-tag2.json</code></li>
	 * </ul>
	 * <p>
	 * During the loading process, the loader creates a list of available and matching files,
	 * then it sorts them by precedence. At first, the first file will be loaded. If they are other matching files,
	 * then the second one will be loaded, and it's properties will override the properties of the first file.
	 * Other matching files are loaded in that order, and also update the previously loaded properties.
	 * </p>
	 * <p>
	 * Each loaded files should have the class name prefix. If an identifier was specified, then the prefix should be
	 * followed by a dash sign and the identifier. If a file name also have tags, then they will be validated
	 * against the current ibello tags. If a file has a tag which was <b>not</b> specified as an ibello tag, then
	 * that file will be <b>not</b> loaded. In other words, loaded files should not have unspecified tags.
	 * </p>
	 * <p>
	 * The first loaded file will be the one without identifier and tags (if any). Then comes the files without identifier but
	 * with one or more tags. The remaining files (with identifier) will be sorted by the number of tags and
	 * the tags itself (in alphabetical order). Examples:
	 * </p>
	 * <ul>
	 * <li><code>myclass.json</code> will be loaded before <code>myclass.a.json</code></li>
	 * <li><code>myclass.a.json</code> will be loaded before <code>myclass-id.json</code></li>
	 * <li><code>myclass.a.json</code> will be loaded before <code>myclass.b.json</code></li>
	 * <li><code>myclass.c.json</code> will be loaded before <code>myclass.a-b.json</code></li>
	 * </ul>
	 * <p>
	 * The files can be placed into sub-directories, the loader will find them automatically.
	 * </p>
	 * <p>
	 * By default, the objects loaded from the JSON files will be fully merged in the result. Element of arrays will be joined together.
	 * For example, the first two JSON object will result the third one:
	 * </p>
	 * <pre>
	 * {
	 *     "a": 1,
	 *     "b": {
	 *         "b1": 1, "b2": 1, "b3": {"b3_1": 1}
	 *     },
	 *     "c": [1]
	 * }
	 * 
	 * {
	 *     "a": 2,
	 *     "b": {
	 *         "b1": 2, "b3": {"b3_2": 2}
	 *     },
	 *     "c": [2]
	 * }
	 * 
	 * {
	 *     "a": 2,
	 *     "b": {
	 *         "b1": 2, "b2": 1, "b3": {"b3_1": 1, "b3_2": 2}
	 *     },
	 *     "c": [1,2]
	 * }
	 * </pre>
	 * <p>
	 * We can change this behavior with the {@link JsonTestDataBuilder#doNotMergeObjects()} and {@link JsonTestDataBuilder#doNotJoinArrays()}
	 * methods.
	 * </p>
	 * @param <T> type of the result object
	 * @param dataType type of the result object
	 * @return the parsed (and optionally merged) JSON data
	 */
	public <T> JsonTestDataBuilder<T> fromJson(Class<T> dataType);
	
	public FileTestDataBuilder fromFile(String fileName);
	
}
