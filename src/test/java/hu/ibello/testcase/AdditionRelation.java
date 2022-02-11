package hu.ibello.testcase;

import static org.assertj.core.api.Assertions.assertThat;

class AdditionRelation implements MetamorphRelation<Integer[], Integer> {
	
	private final int addition;
	
	public AdditionRelation(int addition) {
		super();
		this.addition = addition;
	}

	@Override
	public Integer[] arrange(Integer[] originalInput) {
		return new Integer[] {originalInput[0], originalInput[1] + addition};
	}

	@Override
	public void assertOutput(Integer originalOutput, Integer output) {
		assertThat(output).isGreaterThan(originalOutput);
		System.out.println(String.format("%d is greater than %s", output, originalOutput));
	}
	
}
