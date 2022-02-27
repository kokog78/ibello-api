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

import hu.ibello.core.Value;
import hu.ibello.transform.SerializedName;

/**
 * This class is designed for load test data from the filesystem.
 * Test data can be stored in java property file, or in JSON file.
 * According to the file format, we can start a loader operation with one of these methods:
 * <ul>
 * <li>{@link #fromProperties(String)}: data loading from java property file(s)</li>
 * <li>{@link #fromJson(Class)}: data loading from JSON file(s)</li>
 * <li>{@link #fromBinary(String)}: data loading from any kind of file(s)</li>
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
	 * contain tags, separated by dash signs. The extension of the file is always <code>json</code>. Examples:
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
	 * followed by a dash sign and the identifier. If a file name also have tags, then it will be validated
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
	 * @return object which loads the parsed (and optionally merged) JSON data
	 */
	public <T> JsonTestDataBuilder<T> fromJson(Class<T> dataType);
	
	/**
	 * <p>
	 * Returns an object which can be used to load test data from a CSV file.
	 * For that we must specify a java class which will be the type of the loaded items.
	 * </p>
	 * <p>
	 * The name of the file starts with the lower-case name of the java class (without package name).
	 * It may be followed by a dash sign and an identifier. Then comes a dot. At this position the file name may
	 * contain tags, separated by dash signs. The extension of the file is always <code>csv</code>. Examples:
	 * </p>
	 * <ul>
	 * <li><code>myclass.csv</code></li>
	 * <li><code>myclass.tag1.csv</code></li>
	 * <li><code>myclass-id.tag1.csv</code></li>
	 * <li><code>myclass-id.tag1-tag2.csv</code></li>
	 * </ul>
	 * <p>
	 * During the loading process, the loader creates a list of available and matching files,
	 * then it sorts them by precedence. At first, the first file will be loaded. If they are other matching files,
	 * then the second one will be loaded, and it's content will be appended to the result.
	 * Other matching files are loaded in that order, and also are appended the result.
	 * </p>
	 * <p>
	 * Each loaded files should have the class name prefix. If an identifier was specified, then the prefix should be
	 * followed by a dash sign and the identifier. If a file name also have tags, then it will be validated
	 * against the current ibello tags. If a file has a tag which was <b>not</b> specified as an ibello tag, then
	 * that file will be <b>not</b> loaded. In other words, loaded files should not have unspecified tags.
	 * </p>
	 * <p>
	 * The first loaded file will be the one without identifier and tags (if any). Then comes the files without identifier but
	 * with one or more tags. The remaining files (with identifier) will be sorted by the number of tags and
	 * the tags itself (in alphabetical order). Examples:
	 * </p>
	 * <ul>
	 * <li><code>myclass.csv</code> will be loaded before <code>myclass.a.csv</code></li>
	 * <li><code>myclass.a.csv</code> will be loaded before <code>myclass-id.csv</code></li>
	 * <li><code>myclass.a.csv</code> will be loaded before <code>myclass.b.csv</code></li>
	 * <li><code>myclass.c.csv</code> will be loaded before <code>myclass.a-b.csv</code></li>
	 * </ul>
	 * <p>
	 * The files can be placed into sub-directories, the loader will find them automatically.
	 * </p>
	 * <p>
	 * The data values in the CSV file should be separated by comma (and not semicolon or tabulator).
	 * </p>
	 * <p>
	 * Each CSV file should have a header - this is the first line in the file.
	 * This line should contain the field names of the data type where we want to place the values. Complex field names are also
	 * allowed - the field separator is the dot character. If a field has a {@link SerializedName} annotation, then - instead of the field name -
	 * the value of that annotation should be in the header.
	 * </p>
	 * <p>
	 * Let's say we have these java classes:
	 * </p>
	 * <pre>
	 * public class MyClass {
	 *     
	 *     public int number;
	 *     
	 *     {@literal @}SerializedName("x-y")
	 *     public String xy;
	 *     
	 *     public ChildClass child;
	 * }
	 * 
	 * public class ChildClass {
	 * 
	 *     public LocalDate date;
	 *     
	 * }
	 * </pre>
	 * <p>
	 * For that data structure we may have a CSV file with this content:
	 * </p>
	 * <pre>
	 * number,x-y,child.date
	 * 1,"test","2001-02-03"
	 * </pre>
	 * @param <T> type of the items in the result list
	 * @param dataType type of the items in the result list
	 * @return object which loads the CSV data
	 */
	public <T> CsvTestDataBuilder<T> fromCsv(Class<T> dataType);
	
	/**
	 * <p>
	 * Returns an object which can be used to load test data from a TXT file.
	 * For that, we must specify a prefix for the loaded file.
	 * </p>
	 * <p>
	 * The name of the loaded file starts with the given prefix. Then comes a dot. Optionally, one or more tags can be
	 * added to the name here, separated by dash signs. The extension of the file is always <code>txt</code>. Examples:
	 * </p>
	 * <ul>
	 * <li><code>myprefix.txt</code></li>
	 * <li><code>myprefix.tag1.txt</code></li>
	 * <li><code>myprefix.tag1-tag2.txt</code></li>
	 * </ul>
	 * <p>
	 * During the loading process, the loader creates a list of available and matching files,
	 * then it sorts them by precedence. Only the last one will be loaded.
	 * </p>
	 * <p>
	 * If a file name have tags, then it will be validated
	 * against the current ibello tags. If a file has a tag which was <b>not</b> specified as an ibello tag, then
	 * that file will be <b>not</b> selected. In other words, selected files should not have unspecified tags.
	 * </p>
	 * <p>
	 * The first selected file will be the one without tags (if any). Then come the files
	 * with one or more tags (in alphabetical order). Examples:
	 * </p>
	 * <ul>
	 * <li><code>myprefix.txt</code> is before <code>myprefix.a.txt</code></li>
	 * <li><code>myprefix.a.txt</code> is before <code>myprefix-id.txt</code></li>
	 * <li><code>myprefix.a.txt</code> is before <code>myprefix.b.txt</code></li>
	 * <li><code>myprefix.c.txt</code> is before <code>myprefix.a-b.txt</code></li>
	 * </ul>
	 * <p>
	 * The files can be placed into sub-directories, the loader will find them automatically.
	 * </p>
	 * @param fileNamePrefix prefix of the file name
	 * @return the object which loads the text data from a file
	 */
	public TxtTestDataBuilder fromTxt(String fileNamePrefix);
	
	/**
	 * <p>
	 * Returns an object which can be used to load test data from a binary file.
	 * For that, we must specify the name of the loaded file.
	 * </p>
	 * <p>
	 * The loader uses the given file name as a template for the finally loaded file. Based on that,
	 * it creates a list of available and matching files,
	 * then it sorts them by precedence. Only the last one will be loaded.
	 * </p>
	 * <p>
	 * The name of the selected files should have the same extension as the given file name, and should start with the first part
	 * of that name. Between them, optionally, one or more tags can be included, separated by dash signs. Between the first part and the
	 * tags there should be a dot. For example, if the given file name is <code>myfile.dat</code>, then these files can be selected:
	 * </p>
	 * <ul>
	 * <li><code>myfile.dat</code></li>
	 * <li><code>myfile.tag1.dat</code></li>
	 * <li><code>myfile.tag1-tag2.dat</code></li>
	 * </ul>
	 * <p>
	 * If a file name have tags, then it will be validated
	 * against the current ibello tags. If a file has a tag which was <b>not</b> specified as an ibello tag, then
	 * that file will be <b>not</b> selected. In other words, selected files should not have unspecified tags.
	 * </p>
	 * <p>
	 * The first selected file will be the one without tags (if any). Then come the files
	 * with one or more tags (in alphabetical order). Examples:
	 * </p>
	 * <ul>
	 * <li><code>myfile.dat</code> is before <code>myfile.a.dat</code></li>
	 * <li><code>myfile.a.dat</code> is before <code>myfile-id.dat</code></li>
	 * <li><code>myfile.a.dat</code> is before <code>myfile.b.dat</code></li>
	 * <li><code>myfile.c.dat</code> is before <code>myfile.a-b.dat</code></li>
	 * </ul>
	 * <p>
	 * The files can be placed into sub-directories, the loader will find them automatically.
	 * </p>
	 * @param fileName the name of the loaded file, with extension
	 * @return the object which loads the binary data from a file
	 */
	public BinaryTestDataBuilder fromBinary(String fileName);
	
	/**
	 * Creates an objects which can be used to evaluate a test data expression.
	 * <p>
	 * In the expression we can use references with the form: <code>${...}</code>.
	 * References can be even complex ones, for example: <code>${person.fullName}</code>.
	 * Complex references are using fields and not getter methods, so the previous example will return with the
	 * content of the <code>fullName</code> field of the <code>person</code> object.
	 * </p>
	 * <p>
	 * Referenced objects can be defined with the {@link TestDataExpressionEvaluator#withReference(String, Object)} method.
	 * </p>
	 * <p>
	 * If a referenced object is not defined, then ibello tries to read the reference as a configuration property name.
	 * In this case, the value of the property will be used.
	 * </p>
	 * <p>
	 * The result of the evaluation is a {@link Value} instance. Specific typed value can be accessed from that interface,
	 * for example with the {@link Value#toInteger()} method.
	 * </p>
	 * <p>
	 * Example:
	 * </p>
	 * <pre>
	 * Screenshot screenshot = new Screenshot("http://ibello.eu", "/var/log/screenshot.png", "12");
	 * 
	 * // result is 12.0
	 * Double number = expression("${ref.label}")
	 *   .withReference("ref", object)
	 *   .evaluate()
	 *   .toDouble();
	 * 
	 * // result is "Screenshot path: /var/log/screenshot.png"
	 * String text = expression("Screenshot path: ${ref.path}.")
	 *   .withReference("ref", object)
	 *   .evaluate()
	 *   .toString();
	 * 
	 * // result is the value of the ibello.report.name configuration property
	 * String configValue = expression("${ibello.report.name}")
	 *   .evaluate()
	 *   .toString();
	 * </pre>
	 * @param expression the expression we want to evaluate
	 * @return an objects which can be used to evaluate the expression
	 */
	public TestDataExpressionEvaluator expression(String expression);
	
}
