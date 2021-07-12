package hu.ibello.functions;

/**
 * Represents a function which can be fittted to a dataset.
 * @author Kornél Simon
 *
 */
public interface Function {

	/**
	 * Value of the function at x.
	 * @param x the input value
	 * @return function's value at x
	 */
	public double value(double x);
	
	/**
	 * How many parameters this function has?
	 * @return number of function parameters
	 */
	public int getParameterCount();
	
	/**
	 * Returns the value of a parameter by index.
	 * The index of the first parameter is 0.
	 * @param paramIndex index of the parameter
	 * @return the parameter's value
	 */
	public double getParameter(int paramIndex);
	
	/**
	 * Sets the value of a parameter by index.
	 * The index of the first parameter is 0.
	 * @param paramIndex index of the parameter
	 * @param value new value of the parameter
	 */
	public void setParameter(int paramIndex, double value);
	
	/**
	 * Returns an array with the parameters.
	 * The index in this array correlates with the index in the {@link #getParameter(int)} and {@link #setParameter(int, double)} methods.
	 * @return array of parameters
	 */
	public default double[] getParameters() {
		int count = getParameterCount();
		double[] parameters = new double[count];
		for (int i=0; i<count; i++) {
			parameters[i] = getParameter(i);
		}
		return parameters;
	}
	
	/**
	 * Sets all the parameters.
	 * The index in the input array should correlate with the index in the {@link #getParameter(int)} and {@link #setParameter(int, double)} methods.
	 * @param parameters array of parameters
	 */
	public default void setParameters(double ... parameters) {
		for (int i=0; i<parameters.length; i++) {
			setParameter(i, parameters[i]);
		}
	}
	
	/**
	 * Formats the value of an indexed parameter.
	 * The index of the first parameter is 0.
	 * Negative value will be in parenthesis.
	 * @param paramIndex index of the parameter
	 * @return the formatted value
	 */
	public default String getFormattedParameter(int paramIndex) {
		double d = getParameter(paramIndex);
		String result = String.format("%.10f", d).replace(',', '.');
		result = result.replaceAll("\\.?0+$", "");
		if (d < 0.0) {
			result = "(" + result + ")";
		}
		return result;
	}
}
