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
package hu.ibello.steps;

import hu.ibello.core.Value;
import hu.ibello.data.TestDataBuilder;
import hu.ibello.output.OutputHandler;

/**
 * Helper interface for step libraries.
 * <p>
 * There is no need to use this interface directly.
 * The <em>ibello</em> dependency injection system automatically instantiates and injects
 * an instance into step libraries.
 * </p>
 * @see StepLibrary
 * @author Kornél Simon
 */
public interface StepLibraryTool {

	/**
	 * Returns a configuration property as a {@link Value}. The returned value offers some public methods to
	 * transform the configuration property into different java types.
	 * This method always has a non-null result, even if the configuration value does not exist - in this case,
	 * the wrapped value will be <code>null</code>.
	 * @param name name of the configuration parameter
	 * @return value of the configuration parameter wrapped into a {@link Value} instance
	 */
	Value getConfigurationValue(String name);
	
	/**
	 * Starts a new test data loading. The result is a {@link TestDataBuilder} instance which can be used
	 * to configure and perform the test loading operation.
	 * @return a test data loader instance
	 * @see TestDataBuilder
	 */
	TestDataBuilder testData();
	
	/**
	 * Returns the output handler tool. With that we can add custom actions and expectations to the test report.
	 * For example, a custom logging can be performed as:
	 * <pre>
	 * output().recordCustomAction("Username is " + username);
	 * </pre>
	 * We can do custom expectation with the following code (using the junit assertion library):
	 * <pre>
	 * org.junit.Assert.assertEquals("Username is wrong", "Martin", username);
	 * output().recordCustomExpectation("Username is the expected " + username);
	 * </pre>
	 * This code will throw an {@link AssertionError}, if the username is not the expected one. (All {@link AssertionError} type
	 * exceptions are handled by the ibello automatically.) If the username is correct, then the second row will add a
	 * message to the test report.
	 * @return the output handler instance
	 * @see OutputHandler
	 */
	OutputHandler output();
}
