package hu.ibello.functions;

import static org.assertj.core.api.Assertions.*;
import org.junit.Test;

public class PowerFunctionTest {

	@Test
	public void inverse_function_should_return_the_right_value() throws Exception {
		Function fn = function(2, 3);
		Function inverse = inverse(2, 3);
		double value1 = fn.value(1);
		assertThat(inverse.value(value1)).isEqualTo(1.0, within(0.0000001));
		double value2 = fn.value(5);
		assertThat(inverse.value(value2)).isEqualTo(5.0, within(0.0000001));
		double value3 = fn.value(10);
		assertThat(inverse.value(value3)).isEqualTo(10.0, within(0.0000001));
	}
	
	private Function function(double a, double b) {
		PowerFunction fn = new PowerFunction(a, b);
		return fn;
	}
	
	private Function inverse(double a, double b) {
		PowerFunction fn = new PowerFunction(1 / Math.pow(a, 1/b), 1/b);
		return fn;
	}
}
