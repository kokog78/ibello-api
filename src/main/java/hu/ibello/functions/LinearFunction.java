package hu.ibello.functions;

/**
 * A simple linear function.
 * <pre>
 * f(x) = a + b * x
 * </pre>
 * Parameters are:
 * <ul>
 * <li>a</li>
 * <li>b</li>
 * </ul>
 * @author Kornél Simon
 *
 */
public class LinearFunction implements Function {

	public LinearFunction() {
	}
	
	public LinearFunction(double a, double b) {
		this.a = a;
		this.b = b;
	}
	
	private double a;
	private double b;
	
	public double getA() {
		return a;
	}
	
	public void setA(double a) {
		this.a = a;
	}
	
	public double getB() {
		return b;
	}
	
	public void setB(double b) {
		this.b = b;
	}
	
	@Override
	public double value(double x) {
		return a + b * x;
	}

	@Override
	public int getParameterCount() {
		return 2;
	}

	@Override
	public double getParameter(int paramIndex) {
		switch (paramIndex) {
		case 0:
			return a;
		case 1:
			return b;
		}
		throw new IllegalArgumentException("Unknown parameter index: " + paramIndex);
	}

	@Override
	public void setParameter(int paramIndex, double value) {
		switch (paramIndex) {
		case 0:
			a = value;
			break;
		case 1:
			b = value;
			break;
		default:
			throw new IllegalArgumentException("Unknown parameter index: " + paramIndex);
		}
	}

	@Override
	public String toString() {
		return String.format("%s + %s * x", getFormattedParameter(0), getFormattedParameter(1));
	}
}
