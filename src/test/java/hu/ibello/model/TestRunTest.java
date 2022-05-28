package hu.ibello.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class TestRunTest {

	@Test
	public void addSuccessCount_handles_null_value() throws Exception {
		TestRun testRun = new TestRun();
		assertThat(testRun.getSuccessCount()).isNull();
		testRun.addSuccessCount(0);
		assertThat(testRun.getSuccessCount()).isNull();
		testRun.addSuccessCount(1);
		assertThat(testRun.getSuccessCount()).isEqualTo(1);
		testRun.addSuccessCount(0);
		assertThat(testRun.getSuccessCount()).isEqualTo(1);
		testRun.addSuccessCount(1);
		assertThat(testRun.getSuccessCount()).isEqualTo(2);
	}
	
	@Test
	public void addPendingCount_handles_null_value() throws Exception {
		TestRun testRun = new TestRun();
		assertThat(testRun.getPendingCount()).isNull();
		testRun.addPendingCount(0);
		assertThat(testRun.getPendingCount()).isNull();
		testRun.addPendingCount(1);
		assertThat(testRun.getPendingCount()).isEqualTo(1);
		testRun.addPendingCount(0);
		assertThat(testRun.getPendingCount()).isEqualTo(1);
		testRun.addPendingCount(1);
		assertThat(testRun.getPendingCount()).isEqualTo(2);
	}
	
	@Test
	public void addFailureCount_handles_null_value() throws Exception {
		TestRun testRun = new TestRun();
		assertThat(testRun.getFailureCount()).isNull();
		testRun.addFailureCount(0);
		assertThat(testRun.getFailureCount()).isNull();
		testRun.addFailureCount(1);
		assertThat(testRun.getFailureCount()).isEqualTo(1);
		testRun.addFailureCount(0);
		assertThat(testRun.getFailureCount()).isEqualTo(1);
		testRun.addFailureCount(1);
		assertThat(testRun.getFailureCount()).isEqualTo(2);
	}
	
	@Test
	public void addErrorCount_handles_null_value() throws Exception {
		TestRun testRun = new TestRun();
		assertThat(testRun.getErrorCount()).isNull();
		testRun.addErrorCount(0);
		assertThat(testRun.getErrorCount()).isNull();
		testRun.addErrorCount(1);
		assertThat(testRun.getErrorCount()).isEqualTo(1);
		testRun.addErrorCount(0);
		assertThat(testRun.getErrorCount()).isEqualTo(1);
		testRun.addErrorCount(1);
		assertThat(testRun.getErrorCount()).isEqualTo(2);
	}
	
}
