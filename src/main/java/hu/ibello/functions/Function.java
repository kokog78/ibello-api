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
