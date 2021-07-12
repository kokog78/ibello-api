package hu.ibello.graph;

import java.util.List;

import hu.ibello.functions.DataPoint;
import hu.ibello.functions.Function;

public interface Graph {
	
	public void setName(String name);

	public void setXAxis(String name, Double min, Double max);
	
	public void setYAxis(String name);
	
	public void add(String name, Function function);
	
	public void add(String name, List<DataPoint> data);
	
	public DataPoint point(double x, double y);
	
}
