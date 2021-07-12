package hu.ibello.functions;

/**
 * A constant function.
 * <pre>
 * f(x) = a
 * </pre>
 * Parameters are:
 * <ul>
 * <li>a</li>
 * </ul>
 * @author kokog
 *
 */
public class ConstantFunction implements Function {

	protected double a;
	
	public double getA() {
		return a;
	}
	
	public void setA(double a) {
		this.a = a;
	}
	
	@Override
	public double value(double x) {
		return a;
	}

	@Override
	public int getParameterCount() {
		return 1;
	}

	@Override
	public double getParameter(int paramIndex) {
		if (paramIndex == 0) {
			return a;
		}
		throw new IllegalArgumentException("Unknown parameter index: " + paramIndex);
	}

	@Override
	public void setParameter(int paramIndex, double value) {
		if (paramIndex == 0) {
			a = value;
		} else {
			throw new IllegalArgumentException("Unknown parameter index: " + paramIndex);
		}
	}
	
	@Override
	public String toString() {
		return getFormattedParameter(0);
	}

}
