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
package hu.ibello.plugins;

import java.io.File;

import hu.ibello.model.TestRun;

/**
 * Implementations of this interface are used to read non-ibello format test results from directories.
 */
public interface IbelloReportReader extends Plugin {

	/**
	 * Reads the test report from the specified directory and returns as an ibello {@link TestRun} instance.
	 * <p>
	 * If the directory does not contain valid test results that can be loaded by this instance then this method should throw an exception.
	 * Instead of it it should return <code>null</code>.
	 * The ibello framework knows from this that reading the test result should be tried with another {@link IbelloReportReader} instance.
	 * If the method throws an exception, then ibello will not try to read the results with another reader instance. 
	 * </p>
	 * @param directory the directory containing the results, in many cases it is a sub-folder of the "ibello/reports" directory
	 * @return the test results or <code>null</code>
	 * @throws PluginException if an error occurs during reading the test results
	 */
	public TestRun readReport(File directory) throws PluginException;
	
}
