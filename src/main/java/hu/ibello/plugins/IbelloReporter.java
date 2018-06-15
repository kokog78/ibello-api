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

import hu.ibello.model.ITestRun;
import hu.ibello.model.SpecElement;
import hu.ibello.model.TestRun;

/**
 * A special ibello {@link Plugin} interface which receives information about test execution progress and therefore
 * can be used to generate custom test reports.
 * @author Kornél Simon
 *
 */
public interface IbelloReporter extends Plugin {
	
	/**
	 * This method is called before any test specification. It receives a {@link ITestRun} instance which contains only
	 * the root-level parameters of the test execution.
	 * @param tests information about tests to be executed
	 */
	public void startup(ITestRun tests);

	/**
	 * This method is called after a specification is executed. It receives every information about the specification,
	 * including it's outcome and duration.
	 * @param spec information about the executed specification
	 */
	public void specificationFinished(SpecElement spec);
	
	/**
	 * This method is called after the last specification is executed and the tests are finished.
	 * It receives the full log of the test execution.
	 * @param tests the full information about test execution
	 */
	public void shutdown(TestRun tests);
	
}
