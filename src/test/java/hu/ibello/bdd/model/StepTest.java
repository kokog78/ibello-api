package hu.ibello.bdd.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class StepTest {

	@Test
	public void getKeyword_should_calculate_keyword() throws Exception {
		Step step1 = step("Given", "A");
		Step step2 = step(StepKind.WHEN, "A");
		Step step3 = step((String)null, "A");
		assertThat(step1.getKeyword()).isEqualTo("Given");
		assertThat(step2.getKeyword()).isEqualTo("When");
		assertThat(step3.getKeyword()).isEqualTo("*");
	}
	
	private Step step(String keyword, String text) {
		Step step = new Step();
		step.setKeyword(keyword);
		step.setText(text);
		return step;
	}

	private Step step(StepKind kind, String text) {
		Step step = new Step();
		step.setKind(kind);
		step.setText(text);
		return step;
	}
}
