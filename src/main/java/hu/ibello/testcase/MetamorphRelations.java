package hu.ibello.testcase;

import java.util.List;

public interface MetamorphRelations<I, O> {

	List<MetamorphRelation<I, O>> getRelations();
	
	void assertAll(O originalOutput, List<O> outputs) throws AssertionError;
}
