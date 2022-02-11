package hu.ibello.testcase;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class ComponentTestCaseTest {
	
	@Test
	public void should_update_execution_state_for_successful_test() throws Exception {
		SimpleTestCase tc = new SimpleTestCase(1, 10, 11);

		assertThat(tc.isStarted()).isFalse();
		assertThat(tc.isFinished()).isFalse();
		
		tc.run();

		assertThat(tc.isStarted()).isTrue();
		assertThat(tc.isFinished()).isTrue();
		assertThat(tc.getOutput()).isEqualTo(11);
	}

	@Test
	public void should_update_execution_state_for_failed_test() throws Exception {
		SimpleTestCase tc = new SimpleTestCase(1, 10, 2);

		assertThat(tc.isStarted()).isFalse();
		assertThat(tc.isFinished()).isFalse();
		
		org.assertj.core.api.Assertions.assertThatThrownBy(() -> tc.run())
			.isInstanceOf(AssertionError.class);

		assertThat(tc.isStarted()).isTrue();
		assertThat(tc.isFinished()).isTrue();
		assertThat(tc.getOutput()).isEqualTo(11);
	}
	
}
