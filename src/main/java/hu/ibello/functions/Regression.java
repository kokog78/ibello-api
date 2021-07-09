package hu.ibello.functions;

import java.util.List;

public interface Regression<F extends Function> {

	public F getFunction();
	
	public List<DataPoint> getData();
	
	public void run();
	
}
