package hu.ibello.testcase;

import java.util.ArrayList;
import java.util.List;

class MetamorphSummaryTestCase<I, O> extends ComponentTestCase<I, O> {

	private final ComponentTestCase<I, O> original;
	private final MetamorphRelations<I, O> relations;
	private final List<MetamorhTestCase<I, O>> testCases;
	
	public MetamorphSummaryTestCase(ComponentTestCase<I, O> original, MetamorphRelations<I, O> relations, List<MetamorhTestCase<I, O>> testCases) {
		super();
		this.original = original;
		this.relations = relations;
		this.testCases = testCases;
	}
	
	@Override
	protected I arrange() throws Exception {
		return null;
	}
	
	@Override
	protected O act(I input) throws Exception {
		return null;
	}
	
	@Override
	protected void assertOutput(O ouptput) throws AssertionError {
		List<O> outputs = new ArrayList<>();
		for (MetamorhTestCase<I, O> tc : testCases) {
			outputs.add(tc.getOutput());
		}
		relations.assertAll(original.getOutput(), outputs);
	}
	
}
