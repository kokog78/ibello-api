package hu.ibello.functions;

import static org.assertj.core.api.Assertions.*;
import org.junit.Test;

public class Logistic5FunctionTest {

	@Test
	public void inverse_function_should_return_the_right_value() throws Exception {
		Function fn = function(1, 2, 3, 4, 5);
		Function inverse = inverse(1, 2, 3, 4, 5);
		double value1 = fn.value(1);
		assertThat(inverse.value(value1)).isEqualTo(1.0, within(0.0000001));
		double value2 = fn.value(5);
		assertThat(inverse.value(value2)).isEqualTo(5.0, within(0.0000001));
		double value3 = fn.value(10);
		assertThat(inverse.value(value3)).isEqualTo(10.0, within(0.0000001));
	}
	
	@Test
	public void parameters_can_be_changed() throws Exception {
		Logistic5Function f = function(1, 2, 3, 4, 5);
		assertThat(f.getY0()).isEqualTo(1.00);
		assertThat(f.getY1()).isEqualTo(2.00);
		assertThat(f.getB()).isEqualTo(3.00);
		assertThat(f.getC()).isEqualTo(4.00);
		assertThat(f.getM()).isEqualTo(5.00);
		f.setY0(5);
		f.setY1(6);
		f.setB(7);
		f.setC(8);
		f.setM(9);
		assertThat(f.getY0()).isEqualTo(5.00);
		assertThat(f.getY1()).isEqualTo(6.00);
		assertThat(f.getB()).isEqualTo(7.00);
		assertThat(f.getC()).isEqualTo(8.00);
		assertThat(f.getM()).isEqualTo(9.00);
	}
	
	@Test
	public void parameters_can_be_changed_by_index() throws Exception {
		Logistic5Function f = function(1, 2, 3, 4, 5);
		assertThat(f.getParameter(0)).isEqualTo(1.00);
		assertThat(f.getParameter(1)).isEqualTo(2.00);
		assertThat(f.getParameter(2)).isEqualTo(3.00);
		assertThat(f.getParameter(3)).isEqualTo(4.00);
		assertThat(f.getParameter(4)).isEqualTo(5.00);
		f.setParameter(0, 5);
		f.setParameter(1, 6);
		f.setParameter(2, 7);
		f.setParameter(3, 8);
		f.setParameter(4, 9);
		assertThat(f.getParameter(0)).isEqualTo(5.00);
		assertThat(f.getParameter(1)).isEqualTo(6.00);
		assertThat(f.getParameter(2)).isEqualTo(7.00);
		assertThat(f.getParameter(3)).isEqualTo(8.00);
		assertThat(f.getParameter(4)).isEqualTo(9.00);
		assertThat(f.getY0()).isEqualTo(5.00);
		assertThat(f.getY1()).isEqualTo(6.00);
		assertThat(f.getB()).isEqualTo(7.00);
		assertThat(f.getC()).isEqualTo(8.00);
		assertThat(f.getM()).isEqualTo(9.00);
	}
	
	private Logistic5Function function(double y0, double y1, double b, double c, double m) {
		Logistic5Function fn = new Logistic5Function();
		fn.setY0(y0);
		fn.setY1(y1);
		fn.setB(b);
		fn.setC(c);
		fn.setM(m);
		return fn;
	}
	
	private Logistic5InverseFunction inverse(double y0, double y1, double b, double c, double m) {
		Logistic5InverseFunction fn = new Logistic5InverseFunction();
		fn.setY0(y0);
		fn.setY1(y1);
		fn.setB(b);
		fn.setC(c);
		fn.setM(m);
		return fn;
	}
}
