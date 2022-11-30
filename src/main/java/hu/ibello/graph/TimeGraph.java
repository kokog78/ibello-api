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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import hu.ibello.functions.Function;

/**
 * 
 * Represents a graph/chart where the domain axis is the time. Ibello can print the graph on the screen or can save it into file.
 * @author Kornél Simon
 *
 */
public interface TimeGraph {

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
	 * Sets the X axis parameters on the chart.
	 * If the minimum and/or maximum values are <code>null</code>s, ibello will determine them automatically from the content.
	 * @param name the name of the axis
	 * @param min minimum value of the axis, can be <code>null</code>
	 * @param max maximum value of the axis, can be <code>null</code>
	 */
	public void setXAxis(String name, LocalDate min, LocalDate max);
	
	/**
	 * Sets the X axis parameters on the chart.
	 * If the minimum and/or maximum values are <code>null</code>s, ibello will determine them automatically from the content.
	 * @param name the name of the axis
	 * @param min minimum value of the axis, can be <code>null</code>
	 * @param max maximum value of the axis, can be <code>null</code>
	 */
	public void setXAxis(String name, LocalDateTime min, LocalDateTime max);
	
	/**
	 * Sets Y axis parameters on the chart.
	 * @param name the name of the axis
	 */
	public void setYAxis(String name);
	
	/**
	 * Adds a new scatter plot to the chart.
	 * @param name the name of the plot
	 * @param data the data points
	 */
	public void addTimeSeries(String name, List<TimeDataPoint> data);
	
	/**
	 * Adds a new function plot to the chart.
	 * The domain axis is the function will be converted into time values.
	 * For the conversion, each x value will be considered as epoch millis.
	 * @param name the name of the plot
	 * @param function the function which should be plotted
	 */
	public void addFunction(String name, Function function);
	
	/**
	 * Creates a new 2-dimension data point object.
	 * @param x the x value as a local date
	 * @param y the y value
	 * @return the data point object
	 */
	public TimeDataPoint point(LocalDate x, double y);

	/**
	 * Creates a new 2-dimension data point object.
	 * @param x the x value as a local date/time
	 * @param y the y value
	 * @return the data point object
	 */
	public TimeDataPoint point(LocalDateTime x, double y);
}
