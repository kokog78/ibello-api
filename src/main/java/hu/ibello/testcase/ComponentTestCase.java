package hu.ibello.testcase;

public abstract class ComponentTestCase<I, O> {

	private byte state = 0;
	private O output;
	
	public boolean isStarted() {
		return state >= 1;
	}
	
	public boolean isFinished() {
		return state > 1;
	}
	
	public O getOutput() {
		return output;
	}
	
	public void run() throws Exception, AssertionError {
		state = 1;
		try {
			I input = arrange();
			output = act(input);
			assertOutput(output);
		} finally {
			state = 2;
		}
	}
	
	protected abstract I arrange() throws Exception;
	
	protected abstract O act(I input) throws Exception;
	
	protected abstract void assertOutput(O output) throws AssertionError;
	
}
