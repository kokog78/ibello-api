package hu.ibello.functions;

public class Logistic3Function implements Function {

	protected double b;
	protected double c;
	protected double m;
	
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
	
	public double getM() {
		return m;
	}
	
	public void setM(double m) {
		this.m = m;
	}
	
	@Override
	public int getParameterCount() {
		return 3;
	}
	
	@Override
	public double value(double x) {
		return 1 / Math.pow(1 + Math.pow(x / c, b), m);
	}

	@Override
	public double getParameter(int paramIndex) {
		switch (paramIndex) {
		case 0:
			return b;
		case 1:
			return c;
		case 2:
			return m;
		}
		throw new IllegalArgumentException("Unknown parameter index: " + paramIndex);
	}

	@Override
	public void setParameter(int paramIndex, double value) {
		switch (paramIndex) {
		case 0:
			b = value;
			break;
		case 1:
			c = value;
			break;
		case 2:
			m = value;
			break;
		default:
			throw new IllegalArgumentException("Unknown parameter index: " + paramIndex);
		}
	}
	
	@Override
	public String toString() {
		String _b = getFormattedParameter(0);
		String _c = getFormattedParameter(1);
		String _m = getFormattedParameter(2);
		return String.format("1 / ((1 + (x / %s) ^%s) ^%s)", _c, _b, _m);
	}
}
