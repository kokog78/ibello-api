package hu.ibello.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class OutcomeTest {

	@Test
	public void compare_outcome_with_itself() throws Exception {
		assertStronger(Outcome.PENDING, Outcome.PENDING, Outcome.PENDING);
		assertStronger(Outcome.SUCCESS, Outcome.SUCCESS, Outcome.SUCCESS);
		assertStronger(Outcome.FAILURE, Outcome.FAILURE, Outcome.FAILURE);
		assertStronger(Outcome.ERROR, Outcome.ERROR, Outcome.ERROR);
	}
	
	@Test
	public void compare_outcome_with_null() throws Exception {
		assertStronger(Outcome.PENDING, null, Outcome.PENDING);
		assertStronger(Outcome.SUCCESS, null, Outcome.SUCCESS);
		assertStronger(Outcome.FAILURE, null, Outcome.FAILURE);
		assertStronger(Outcome.ERROR, null, Outcome.ERROR);
	}
	
	@Test
	public void pending_is_weaker_than_success() throws Exception {
		assertStronger(Outcome.PENDING, Outcome.SUCCESS, Outcome.SUCCESS);
	}
	
	@Test
	public void pending_is_weaker_than_failure() throws Exception {
		assertStronger(Outcome.PENDING, Outcome.FAILURE, Outcome.FAILURE);
	}
	
	@Test
	public void pending_is_weaker_than_error() throws Exception {
		assertStronger(Outcome.PENDING, Outcome.ERROR, Outcome.ERROR);
	}
	
	@Test
	public void success_is_weaker_than_failure() throws Exception {
		assertStronger(Outcome.SUCCESS, Outcome.FAILURE, Outcome.FAILURE);
	}
	
	@Test
	public void success_is_weaker_than_error() throws Exception {
		assertStronger(Outcome.SUCCESS, Outcome.ERROR, Outcome.ERROR);
	}
	
	@Test
	public void failure_is_weaker_than_error() throws Exception {
		assertStronger(Outcome.FAILURE, Outcome.ERROR, Outcome.ERROR);
	}
	
	@Test
	public void pending_is_weaker_than_skipped() throws Exception {
		assertStronger(Outcome.SKIPPED, Outcome.PENDING, Outcome.SKIPPED);
	}
	
	@Test
	public void skipped_is_weaker_than_blocked() throws Exception {
		assertStronger(Outcome.SKIPPED, Outcome.BLOCKED, Outcome.BLOCKED);
	}
	
	@Test
	public void success_is_weaker_than_blocked() throws Exception {
		assertStronger(Outcome.BLOCKED, Outcome.SUCCESS, Outcome.BLOCKED);
	}
	
	@Test
	public void blocked_is_weaker_than_failure() throws Exception {
		assertStronger(Outcome.BLOCKED, Outcome.FAILURE, Outcome.FAILURE);
	}
	
	@Test
	public void skipped_has_same_power_than_skipped() throws Exception {
		assertStronger(Outcome.SKIPPED, Outcome.SKIPPED, Outcome.SKIPPED);
	}
	
	private void assertStronger(Outcome outcome1, Outcome outcome2, Outcome result) {
		Outcome stronger = Outcome.getStronger(outcome1, outcome2);
		assertThat(stronger).isEqualTo(result);
		stronger = Outcome.getStronger(outcome2, outcome1);
		assertThat(stronger).isEqualTo(result);
	}
	
}
