package hu.ibello.functions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Test;

public class LinearFunctionTest {

	@Test
	public void toString_should_display_formula() throws Exception {
		assertThat(linear(1, 2).toString()).isEqualTo("1 + 2 * x");
		assertThat(linear(0.5, 0.0006).toString()).isEqualTo("0.5 + 0.0006 * x");
	}
	
	@Test
	public void value_should_calculate_right_value() throws Exception {
		assertThat(linear(1, 2).value(2)).isEqualTo(5.00);
	}
	
	@Test
	public void parameters_can_be_changed() throws Exception {
		LinearFunction f = linear(1,  2);
		assertThat(f.getA()).isEqualTo(1.00);
		assertThat(f.getB()).isEqualTo(2.00);
		f.setA(3);
		f.setB(4);
		assertThat(f.getA()).isEqualTo(3.00);
		assertThat(f.getB()).isEqualTo(4.00);
	}
	
	@Test
	public void parameters_can_be_changed_by_index() throws Exception {
		LinearFunction f = linear(1,  2);
		assertThat(f.getParameter(0)).isEqualTo(1.00);
		assertThat(f.getParameter(1)).isEqualTo(2.00);
		f.setParameter(0, 3);
		f.setParameter(1, 4);
		assertThat(f.getParameter(0)).isEqualTo(3.00);
		assertThat(f.getParameter(1)).isEqualTo(4.00);
	}
	
	@Test
	public void getParameterCount_should_return_2() throws Exception {
		assertThat(linear(1, 2).getParameterCount()).isEqualTo(2);
	}
	
	@Test
	public void getParameter_should_throw_exception_if_index_is_invalid() throws Exception {
		LinearFunction f = linear(1, 2);
		assertThatThrownBy(() -> f.getParameter(-1)).isInstanceOf(IllegalArgumentException.class);
		assertThatThrownBy(() -> f.getParameter(3)).isInstanceOf(IllegalArgumentException.class);
	}
	
	@Test
	public void setParameter_should_throw_exception_if_index_is_invalid() throws Exception {
		LinearFunction f = linear(1, 2);
		assertThatThrownBy(() -> f.setParameter(-1, 0)).isInstanceOf(IllegalArgumentException.class);
		assertThatThrownBy(() -> f.setParameter(3, 0)).isInstanceOf(IllegalArgumentException.class);
	}
	
	private LinearFunction linear(double a, double b) {
		LinearFunction f = new LinearFunction();
		f.setA(a);
		f.setB(b);
		return f;
	}
}
