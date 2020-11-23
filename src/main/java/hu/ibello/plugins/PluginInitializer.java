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

import hu.ibello.apitest.HttpClient;
import hu.ibello.apitest.RestClient;
import hu.ibello.core.Value;
import hu.ibello.data.TestDataBuilder;

/**
 * A simple interface, which is used in ibello plugins.
 * It can be used for logging: all log messages received by the {@link #info(String)} or {@link #error(String)}
 * methods will be added to the ibello logs automatically. Another use case of this interface is to get
 * configuration parameters with the {@link #getConfigurationValue(String)} method.
 * @author Kornél Simon
 *
 */
public interface PluginInitializer {

	/**
	 * Adds an information-level message to the ibello logs.
	 * @param message the log message
	 */
	public void info(String message);
	
	/**
	 * Adds an error-level message to the ibello logs.
	 * @param message the log message
	 */
	public default void error(String message) {
		this.error(message, null);
	}
	
	/**
	 * Adds an error-level message to the ibello logs.
	 * @param message the log message
	 * @param exception an exception
	 */
	public void error(String message, Throwable exception);
	
	/**
	 * Returns a configuration property as a {@link Value}. The returned value offers some public methods to
	 * transform the configuration property into different java types.
	 * This method always has a non-null result, even if the configuration value does not exist - in this case,
	 * the wrapped value will be <code>null</code>.
	 * @param name name of the configuration parameter
	 * @return value of the configuration parameter wrapped into a {@link Value} instance
	 */
	public Value getConfigurationValue(String name);
	
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
	public String getMergedURL(String url);
	
	/**
	 * Starts a new test data loading. The result is a {@link TestDataBuilder} instance which can be used
	 * to configure and perform the test loading operation.
	 * @return a test data loader instance
	 * @see TestDataBuilder
	 */
	public TestDataBuilder testData();
	
	/**
	 * Returns a REST client. With that we can perform a REST operation - send a JSON REST request and receive it's response.
	 * The request and response data will be logged automatically.
	 * @return the REST client instance
	 * @see RestClient
	 */
	public RestClient restClient();
	
	/**
	 * Returns a HTTP client. With that we can perform a HTTP call - send a request and receive it's response.
	 * The request and response data will be logged automatically.
	 * @return the HTTP client instance
	 * @see HttpClient
	 */
	public HttpClient httpClient();
	
}
