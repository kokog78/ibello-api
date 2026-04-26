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
package hu.ibello.graph;

import java.io.IOException;

import hu.ibello.transform.TransformerException;

public interface GraphTool {

	/**
	 * Creates a new graph/chart which displays XY values and functions.
	 * @param name the name of the chart
	 * @return the graph/chart instance
	 */
	public Graph createGraph(String name);
	
	/**
	 * Creates a new graph/chart which displays time-related values. 
	 * @param name the name of the chart
	 * @return the graph/chart instance
	 */
	public TimeGraph createTimeGraph(String name);
	
	/**
	 * Creates a new graph/chart which displays labeled values. 
	 * @param name the name of the chart
	 * @return the graph/chart instance
	 */
	public LabeledGraph createLabeledGraph(String name);
	
	/**
	 * Generates a performance measurement time series graph from an existing measurement definition and data.
	 * @param performanceDefinitionId performance measurement definition id
	 * @return the graph created from the measurement data
	 * @throws TransformerException if performance measurement definition file or data file is in the wrong format
	 * @throws IOException if performance measurement definition file or data file cannot be loaded
	 */
	public TimeGraph generatePerformanceTimeSeriesGraph(String performanceDefinitionId) throws TransformerException, IOException;

	/**
	 * Generates an APDEX graph from an existing performance measurement definition and data.
	 * @param performanceDefinitionId performance measurement definition id
	 * @return the graph created from the measurement data
	 * @throws TransformerException if performance measurement definition file or data file is in the wrong format
	 * @throws IOException if performance measurement definition file or data file cannot be loaded
	 */
	public Graph generatePerformanceApdexGraph(String performanceDefinitionId) throws TransformerException, IOException;

	/**
	 * Generates an average response time graph from an existing performance measurement definition and data.
	 * @param performanceDefinitionId performance measurement definition id
	 * @return the graph created from the measurement data
	 * @throws TransformerException if performance measurement definition file or data file is in the wrong format
	 * @throws IOException if performance measurement definition file or data file cannot be loaded
	 */
	public Graph generatePerformanceAverageResponseTimeGraph(String performanceDefinitionId) throws TransformerException, IOException;

	/**
	 * Generates a 90% percentile graph from an existing performance measurement definition and data.
	 * @param performanceDefinitionId performance measurement definition id
	 * @return the graph created from the measurement data
	 * @throws TransformerException if performance measurement definition file or data file is in the wrong format
	 * @throws IOException if performance measurement definition file or data file cannot be loaded
	 */
	public Graph generatePerformancePercentile90Graph(String performanceDefinitionId) throws TransformerException, IOException;

	/**
	 * Generates an error graph from an existing performance measurement definition and data.
	 * @param performanceDefinitionId performance measurement definition id
	 * @return the graph created from the measurement data
	 * @throws TransformerException if performance measurement definition file or data file is in the wrong format
	 * @throws IOException if performance measurement definition file or data file cannot be loaded
	 */
	public Graph generatePerformanceErrorGraph(String performanceDefinitionId) throws TransformerException, IOException;
}
