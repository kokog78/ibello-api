package hu.ibello.functions;

import static org.assertj.core.api.Assertions.*;
import org.junit.Test;

public class Logistic4FunctionTest {

	@Test
	public void inverse_function_should_return_the_right_value() throws Exception {
		Function fn = function(1, 2, 3, 4);
		Function inverse = inverse(1, 2, 3, 4);
		double value1 = fn.value(1);
		assertThat(inverse.value(value1)).isEqualTo(1.0, within(0.0000001));
		double value2 = fn.value(5);
		assertThat(inverse.value(value2)).isEqualTo(5.0, within(0.0000001));
		double value3 = fn.value(10);
		assertThat(inverse.value(value3)).isEqualTo(10.0, within(0.0000001));
	}
	
	private Function function(double y0, double y1, double b, double c) {
		Logistic4Function fn = new Logistic4Function();
		fn.setY0(y0);
		fn.setY1(y1);
		fn.setB(b);
		fn.setC(c);
		return fn;
	}
	
	private Function inverse(double y0, double y1, double b, double c) {
		Logistic4InverseFunction fn = new Logistic4InverseFunction();
		fn.setY0(y0);
		fn.setY1(y1);
		fn.setB(b);
		fn.setC(c);
		return fn;
	}
}
