package hu.ibello.functions;

import java.util.List;

public interface RegressionTool {

	public Regression<LinearFunction> getLinearRegression(List<DataPoint> data);
	
	public <F extends Function> Regression<F> getNonLinearRegression(F function, List<DataPoint> data);
}
