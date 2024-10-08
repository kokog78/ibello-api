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
import hu.ibello.bdd.ExamplesHandler;
import hu.ibello.bdd.FeatureHandler;
import hu.ibello.core.ConfigurationTool;
import hu.ibello.core.Value;
import hu.ibello.data.TestDataBuilder;
import hu.ibello.functions.RegressionTool;
import hu.ibello.graph.GraphTool;
import hu.ibello.output.TestResultLoader;
import hu.ibello.pdf.PdfTool;
import hu.ibello.requirements.FunctionalityHandler;
import hu.ibello.requirements.RequirementHandler;
import hu.ibello.table.TableTool;
import hu.ibello.transform.CsvTransformer;
import hu.ibello.transform.JsonTransformer;

/**
 * A simple interface, which is used in ibello plugins.
 * <p>
 * It can be used for logging: all log messages received by the {@link #info(String)} or {@link #error(String)}
 * methods will be added to the ibello logs automatically.
 * </p><p>
 * Another use case of this interface is to get configuration parameters with the {@link #getConfigurationValue(String)} method.
 * </p><p>
 * It has different method to access ibello's infrastructure in the current project. With the {@link #testData()} method, we access
 * test data stored in files. With {@link #features()} and {@link #examples()}, we can access the BDD features and examples.
 * With {@link #requirements()}, we can access the requirements. The {@link #testResults()} method gives a handler object
 * which loads local test results.
 * </p><p>
 * Some methods can be used to transform data from one format to another. The {@link #json()} and the {@link #csv()} are used to JSON
 * and CSV transformation.
 * </p><p>
 * Some methods return objects which give us dome extra functions to build better output for the task. With the {@link #graph()}
 * method we can define graphs, with the {@link #table()} method we can create tabular data.
 * </p>
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
	 * @deprecated Use <code>configuration().getValue(name)</code> method.
	 * @param name name of the configuration parameter
	 * @return value of the configuration parameter wrapped into a {@link Value} instance
	 */
	@Deprecated
	default Value getConfigurationValue(String name) {
		return configuration().getValue(name);
	}
	
	/**
	 * Sets a configuration property to the given value.
	 * The type of the value can be any type which is known by the {@link Value} interface.
	 * @deprecated Use <code>configuration().setValue(name, value)</code> method.
	 * @param name name of the configuration parameter
	 * @param value value of the configuration parameter
	 */
	@Deprecated
	public default void setConfigurationValue(String name, Object value) {
		configuration().setValue(name, value);
	}
	
	/**
	 * Returns an object which has some methods to access ibello configuration.
	 * @see ConfigurationTool
	 * @return the configuration object
	 */
	ConfigurationTool configuration();
	
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
	
	/**
	 * Returns an object which is designed to load Cucumber feature files from an ibello project.
	 * @return the feature handler object
	 */
	public FeatureHandler features();
	
	/**
	 * Returns an object which is designed to load Cucumber example files from an ibello project.
	 * @return the example handler object
	 */
	public ExamplesHandler examples();
	
	/**
	 * Returns an object which is designed to load requirement files from an ibello project.
	 * @return the requirement handler object
	 */
	public RequirementHandler requirements();
	
	/**
	 * Returns an object which is designed to load functionality files from an ibello project.
	 * @return the functionality handler object
	 */
	public FunctionalityHandler functionalities();
	
	/**
	 * Returns an object which can load ibello tests results.
	 * @return the test result reader tool
	 */
	public TestResultLoader testResults();
	
	/**
	 * Returns an object which can be used to transform java objects into JSON and back.
	 * @return JSON transformer instance
	 */
	public JsonTransformer json();
	
	/**
	 * Returns an object which can be used to transform CSV file to java objects.
	 * @return CSV transformer instance
	 */
	public CsvTransformer csv();
	
	/**
	 * Returns an object which can be used to save HTML pages to PDF files.
	 * @return PDF tool instance
	 */
	public PdfTool pdf();
	
	/**
	 * Returns a regression tool instance.
	 * With this tool, we can run linear and non-linear regression on datasets.
	 * @return a regression tool instance
	 */
	public RegressionTool regression();
	
	/**
	 * Returns a graph drawing tool instance.
	 * With it, we can create and display graphs.
	 * @return a graph tool instance
	 */
	public GraphTool graph();
	
	/**
	 * Returns a table creation tool instance.
	 * With it, we can create and display simple tables in the result.
	 * @return a graph tool instance
	 */
	public TableTool table();
	
}
