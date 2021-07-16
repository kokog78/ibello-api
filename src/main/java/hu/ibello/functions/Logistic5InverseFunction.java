package hu.ibello.functions;

/**
 * Inverse of the 5-parameter logistic function.
 * 
 * @author kokog
 *
 */
public class Logistic5InverseFunction extends Logistic5Function {

	@Override
	public double value(double x) {
		double ratio = (y1 - y0) / (x - y0);
		return c * Math.pow(Math.pow(ratio, 1/m) - 1, 1/b);
	}

	@Override
	public String toString() {
		String _y0 = getFormattedParameter(0);
		String _y1 = getFormattedParameter(1);
		String _b = getFormattedParameter(2);
		String _c = getFormattedParameter(3);
		String _m = getFormattedParameter(4);
		return String.format("%s * (((%s - %s) / (x - %s)) ^(1/%s) - 1) ^(1/%s)", _c, _y1, _y0, _y0, _m, _b);
	}
}
