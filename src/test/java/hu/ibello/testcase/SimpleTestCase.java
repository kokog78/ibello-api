package hu.ibello.testcase;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleTestCase extends ComponentTestCase<Integer[], Integer> {
	
	private final int number1, number2, number3;
	
	public SimpleTestCase(int number1, int number2, int number3) {
		this.number1 = number1;
		this.number2 = number2;
		this.number3 = number3;
	}
	
	@Override
	protected Integer[] arrange() throws Exception {
		return new Integer[] {number1, number2};
	};
	
	@Override
	protected Integer act(Integer[] input) throws Exception {
		return input[0] + input[1];
	}
	
	@Override
	protected void assertOutput(Integer output) throws AssertionError {
		assertThat(output).isEqualTo(number3);
		System.out.println(String.format("%d equals %s", output, number3));
	}
}
