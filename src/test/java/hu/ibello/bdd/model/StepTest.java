package hu.ibello.bdd.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class StepTest {

	@Test
	public void toString_should_use_keyword_and_text() throws Exception {
		Step step = step("Given", "A");
		assertThat(step.toString()).isEqualTo("Given A");
	}
	
	@Test
	public void toString_should_use_kind_and_text() throws Exception {
		Step step = step(StepKind.WHEN, "A");
		assertThat(step.toString()).isEqualTo("When A");
	}
	
	@Test
	public void toString_should_prefer_keyword_over_kind() throws Exception {
		Step step = step("When", "A");
		step.setKind(StepKind.GIVEN);
		assertThat(step.toString()).isEqualTo("When A");
	}
	
	@Test
	public void toString_should_use_asterisk_if_kind_is_unknown() throws Exception {
		Step step = step(StepKind.UNKNOWN, "A");
		assertThat(step.toString()).isEqualTo("* A");
	}
	
	@Test
	public void toString_should_skip_keyword() throws Exception {
		Step step = step("", "A");
		assertThat(step.toString()).isEqualTo("A");
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
