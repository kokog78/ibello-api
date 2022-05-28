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
	
	@Test
	public void parameters_can_be_changed() throws Exception {
		PowerFunction f = function(1,  2);
		assertThat(f.getA()).isEqualTo(1.00);
		assertThat(f.getB()).isEqualTo(2.00);
		f.setA(3);
		f.setB(4);
		assertThat(f.getA()).isEqualTo(3.00);
		assertThat(f.getB()).isEqualTo(4.00);
	}
	
	@Test
	public void parameters_can_be_changed_by_index() throws Exception {
		PowerFunction f = function(1,  2);
		assertThat(f.getParameter(0)).isEqualTo(1.00);
		assertThat(f.getParameter(1)).isEqualTo(2.00);
		f.setParameter(0, 3);
		f.setParameter(1, 4);
		assertThat(f.getParameter(0)).isEqualTo(3.00);
		assertThat(f.getParameter(1)).isEqualTo(4.00);
		assertThat(f.getA()).isEqualTo(3.00);
		assertThat(f.getB()).isEqualTo(4.00);
	}
	
	private PowerFunction function(double a, double b) {
		PowerFunction fn = new PowerFunction(a, b);
		return fn;
	}
	
	private Function inverse(double a, double b) {
		PowerFunction fn = new PowerFunction(1 / Math.pow(a, 1/b), 1/b);
		return fn;
	}
}
