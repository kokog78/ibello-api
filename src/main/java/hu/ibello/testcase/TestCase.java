package hu.ibello.testcase;

public interface TestCase<I, O> {

	public I given();
	
	public O when(I inputState);
	
	public void then(O outputState);
}
