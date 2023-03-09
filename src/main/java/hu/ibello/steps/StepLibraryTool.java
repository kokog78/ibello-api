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

import hu.ibello.apitest.HttpClient;
import hu.ibello.apitest.RestClient;
import hu.ibello.core.Value;
import hu.ibello.data.TestDataBuilder;
import hu.ibello.output.OutputHandler;
import hu.ibello.pages.PageObject;
import hu.ibello.transform.CsvTransformer;
import hu.ibello.transform.JsonTransformer;

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
	 * Returns an URL which is merged with the <code>ibello.url.base</code> configuration property.
	 * <p>
	 * If the given URL is an absolute one with protocol, then it will be returned (without any changes).
	 * </p>
	 * <p>
	 * If the URL is relative, then it will be concatenated to the configuration property, and the result will be returned.
	 * </p>
	 * @param url the absolute or relative URL
	 * @return the merged URL
	 */
	String getMergedURL(String url);

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
	
	/**
	 * Returns a REST client. With that we can perform a REST operation - send a JSON REST request and receive it's response.
	 * The request and response data will be logged automatically.
	 * @return the REST client instance
	 * @see RestClient
	 */
	RestClient restClient();
	
	/**
	 * Returns a HTTP client. With that we can perform a HTTP call - send a request and receive it's response.
	 * The request and response data will be logged automatically.
	 * @return the HTTP client instance
	 * @see HttpClient
	 */
	HttpClient httpClient();
	
	/**
	 * Returns an object which van be used to transform java objects into JSON and back.
	 * @return JSON transformer instance
	 */
	JsonTransformer json();
	
	/**
	 * Returns an object which can be used to transform CSV file to java objects.
	 * @return CSV transformer instance
	 */
	CsvTransformer csv();

	/**
	 * Returns a step library with the given type.
	 * @param <S> type of the step library
	 * @param type step library class
	 * @param windowId the window identifier - if available
	 * @return the instantiated step library with the given type
	 * @throws IllegalArgumentException if the given class cannot be instantiated as step library
	 */
	<S extends StepLibrary> S stepLibrary(Class<S> type, String windowId) throws IllegalArgumentException;
	
	/**
	 * Returns a page object with the given type.
	 * @param <P> type of the page object
	 * @param type page object class
	 * @param windowId the window identifier - if available
	 * @return the instantiated page object with the given type
	 * @throws IllegalArgumentException if the given class cannot be instantiated as page object
	 */
	<P extends PageObject> P pageObject(Class<P> type, String windowId) throws IllegalArgumentException;
}
