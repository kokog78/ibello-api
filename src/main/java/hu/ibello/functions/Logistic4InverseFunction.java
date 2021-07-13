package hu.ibello.functions;

public class Logistic4InverseFunction extends Logistic4Function {

	@Override
	public double value(double x) {
		double ratio = (y1 - y0) / (x - y0);
		return c * Math.pow(ratio - 1, 1/b);
	}

	@Override
	public String toString() {
		String _y0 = getFormattedParameter(0);
		String _y1 = getFormattedParameter(1);
		String _b = getFormattedParameter(2);
		String _c = getFormattedParameter(3);
		return String.format("%s * ((%s - %s) / (x - %s) - 1) ^(1/%s)", _c, _y1, _y0, _y0, _b);
	}
}
