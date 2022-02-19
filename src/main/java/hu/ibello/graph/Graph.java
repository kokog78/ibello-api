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

import hu.ibello.functions.DataPoint;
import hu.ibello.functions.Function;

/**
 * Represents a graph/chart. The ibello can print the graph on the screen or can save into file.
 *
 * @author Kornél Simon
 */
public interface Graph {
	
	/**
	 * The name of the graph/chart.
	 * @param name the name of the graph/chart
	 */
	public void setName(String name);

	/**
	 * Sets the X axis parameters on the chart.
	 * If the minimum and/or maximum values are <code>null</code>s, ibello will determine them automatically from the content.
	 * @param name the name of the axis
	 * @param min minimum value of the axis, can be <code>null</code>
	 * @param max maximum value of the axis, can be <code>null</code>
	 */
	public void setXAxis(String name, Double min, Double max);
	
	/**
	 * Sets Y axis parameters on the chart.
	 * @param name the name of the axis
	 */
	public void setYAxis(String name);
	
	/**
	 * Adda a new function plot to the chart.
	 * @param name the name of the plot
	 * @param function the function which should be plotted
	 */
	public void add(String name, Function function);
	
	/**
	 * Adds a new scatter plot to the chart.
	 * @param name the name of the plot
	 * @param data the data points
	 */
	public void add(String name, List<DataPoint> data);
	
	/**
	 * Creates a new 2-dimension data point object.
	 * @param x the x value
	 * @param y the y value
	 * @return the data point object
	 */
	public DataPoint point(double x, double y);
	
	/**
	 * Creates a new 2-dimension data point object with label.
	 * @param label the label of the data point
	 * @param x the x value
	 * @param y the y value
	 * @return the data point object
	 */
	public LabeledDataPoint point(String label, double x, double y);
	
}
