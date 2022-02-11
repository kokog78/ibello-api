package hu.ibello.testcase;

public interface MetamorphRelation<I, O> {

	I arrange(I originalInput);
	
	void assertOutput(O originalOutput, O output) throws AssertionError;
	
}
