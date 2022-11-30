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

import java.util.List;

/**
 * 
 * Represents a graph/chart where the domain axis contains labels. Ibello can print the graph on the screen or can save it into file.
 * @author Kornél Simon
 *
 */
public interface LabeledGraph {

	/**
	 * The name of the graph/chart.
	 * @param name the name of the graph/chart
	 */
	public void setName(String name);
	
	/**
	 * Sets the X axis parameters on the chart.
	 * @param name the name of the axis
	 */
	public void setXAxis(String name);
	
	/**
	 * Sets Y axis parameters on the chart.
	 * @param name the name of the axis
	 */
	public void setYAxis(String name);
	
	/**
	 * Adds a new bar plot to the chart.
	 * @param name the name of the plot
	 * @param data the data points
	 */
	public void addLabeledSeries(String name, List<LabeledDataPoint> data);
	
	/**
	 * Creates a new labeled data point object.
	 * @param label the label for the point
	 * @param y the y value
	 * @return the data point object
	 */
	public LabeledDataPoint point(String label, double y);
}
