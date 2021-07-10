package hu.ibello.functions;

import static org.assertj.core.api.Assertions.*;
import org.junit.Test;

public class Logistic3FunctionTest {

	@Test
	public void inverse_function_should_return_the_right_value() throws Exception {
		Function fn = function(1, 2, 3);
		Function inverse = inverse(1, 2, 3);
		double value1 = fn.value(1);
		assertThat(inverse.value(value1)).isEqualTo(1.0, within(0.0000001));
		double value2 = fn.value(5);
		assertThat(inverse.value(value2)).isEqualTo(5.0, within(0.0000001));
		double value3 = fn.value(10);
		assertThat(inverse.value(value3)).isEqualTo(10.0, within(0.0000001));
	}
	
	private Logistic3Function function(double b, double c, double m) {
		Logistic3Function fn = new Logistic3Function();
		fn.setB(b);
		fn.setC(c);
		fn.setM(m);
		return fn;
	}
	
	private Logistic3InverseFunction inverse(double b, double c, double m) {
		Logistic3InverseFunction fn = new Logistic3InverseFunction();
		fn.setB(b);
		fn.setC(c);
		fn.setM(m);
		return fn;
	}
}
