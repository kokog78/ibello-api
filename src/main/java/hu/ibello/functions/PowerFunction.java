package hu.ibello.functions;

public class PowerFunction implements Function {

	public PowerFunction() {
	}
	
	public PowerFunction(double a, double b) {
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
		return a * Math.pow(x, b);
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
		return String.format("%s * x ^%s", getFormattedParameter(0), getFormattedParameter(1));
	}
}
