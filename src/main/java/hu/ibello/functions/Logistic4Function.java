package hu.ibello.functions;

/**
 * A 4-parameter logistic function (4PL or symmetrical sigmodial).
 * <pre>
 * f(x) = y0 + (y1 - y0) / (1 + (x / c)^b)
 * </pre>
 * Parameters are:
 * <ul>
 * <li>y0</li>
 * <li>y1</li>
 * <li>b</li>
 * <li>c</li>
 * </ul>
 * @author kokog
 *
 */
public class Logistic4Function implements Function {

	protected double y0;
	protected double y1;
	protected double b;
	protected double c;
	
	public double getY0() {
		return y0;
	}
	
	public void setY0(double d) {
		this.y0 = d;
	}
	
	public double getY1() {
		return y1;
	}
	
	public void setY1(double a) {
		this.y1 = a;
	}
	
	public double getB() {
		return b;
	}
	
	public void setB(double b) {
		this.b = b;
	}
	
	public double getC() {
		return c;
	}
	
	public void setC(double c) {
		this.c = c;
	}
	
	@Override
	public int getParameterCount() {
		return 4;
	}
	
	@Override
	public double value(double x) {
		return y0 + (y1-y0) / (1 + Math.pow(x / c, b));
	}

	@Override
	public double getParameter(int paramIndex) {
		switch (paramIndex) {
		case 0:
			return y0;
		case 1:
			return y1;
		case 2:
			return b;
		case 3:
			return c;
		}
		throw new IllegalArgumentException("Unknown parameter index: " + paramIndex);
	}

	@Override
	public void setParameter(int paramIndex, double value) {
		switch (paramIndex) {
		case 0:
			y0 = value;
			break;
		case 1:
			y1 = value;
			break;
		case 2:
			b = value;
			break;
		case 3:
			c = value;
			break;
		default:
			throw new IllegalArgumentException("Unknown parameter index: " + paramIndex);
		}
	}
	
	@Override
	public String toString() {
		String _y0 = getFormattedParameter(0);
		String _y1 = getFormattedParameter(1);
		String _b = getFormattedParameter(2);
		String _c = getFormattedParameter(3);
		return String.format("%s + (%s - %s) / (1 + (x / %s) ^%s)",
				_y0, _y1, _y0, _c, _b);
	}
}
