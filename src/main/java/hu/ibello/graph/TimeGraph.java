package hu.ibello.graph;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 
 * Represents a graph/chart where the domain axis is the time. Ibello can print the graph on the screen or can save into file.
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
