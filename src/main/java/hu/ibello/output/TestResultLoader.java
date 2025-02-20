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
package hu.ibello.output;

import java.io.File;
import java.util.List;
import java.util.regex.Pattern;

import hu.ibello.model.TestRun;
import hu.ibello.plugins.IbelloReportReader;

/**
 * Utility class which loads test run results from the local "ibello/results" directory.
 * 
 * @author Kornél Simon
 */
public interface TestResultLoader {

	/**
	 * Loads a local test result from the sub-folder of the "ibello/results" directory.
	 * Test results are stored in "results.xml" files. This tool transforms them to java objects.
	 * Example:
	 * <pre>
	 * TestResultLoader loader = ...;
	 * TestRun result = loader.loadTestResult("20000101_123456");
	 * </pre>
	 * If the sub-folder does not contain an ibello format test result then the framework tries to use the registered
	 * {@link IbelloReportReader} instances to convert the result to {@link TestRun} object.
	 * @param dirName the name of the sub-folder in the "ibello/results" directory which contains the "results.xml" file
	 * @return the test run results in java object structure
	 * @throws TestResultLoaderException if the file cannot be loaded
	 */
	public TestRun loadTestResult(String dirName) throws TestResultLoaderException;
	
	/**
	 * Loads a local test result from the given file.
	 * Test results are stored in "results.xml" files. This tool transforms them to java objects.
	 * @param file the XML file containing the test results
	 * @return the test results object
	 * @throws TestResultLoaderException if the file cannot be loaded
	 */
	public TestRun loadTestResult(File file) throws TestResultLoaderException;
	
	/**
	 * Loads local test results from the sub-folder of the "ibello/results" directory.
	 * Test results are stored in "results.xml" files. This tool transforms them to java objects.
	 * Example:
	 * <pre>
	 * TestResultLoader loader = ...;
	 * List&lt;TestRun&gt; results = loader.loadTestResults(Pattern.compile("20000101_.*"));
	 * </pre>
	 * If a sub-folder does not contain an ibello format test result then the framework tries to use the registered
	 * {@link IbelloReportReader} instances to convert the result to {@link TestRun} object.
	 * @param dirNamePattern pattern for the sub-folder names in the "ibello/results" directory
	 * @return list of test run results
	 * @throws TestResultLoaderException if one of the files cannot be loaded
	 */
	public List<TestRun> loadTestResults(Pattern dirNamePattern) throws TestResultLoaderException;
	
}
