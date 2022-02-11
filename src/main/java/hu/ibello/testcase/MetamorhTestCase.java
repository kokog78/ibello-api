package hu.ibello.testcase;

class MetamorhTestCase<I, O> extends ComponentTestCase<I, O> {

	private final ComponentTestCase<I, O> original;
	private final MetamorphRelation<I, O> relation;
	
	MetamorhTestCase(ComponentTestCase<I, O> original, MetamorphRelation<I, O> relation) {
		super();
		this.original = original;
		this.relation = relation;
	}
	
	@Override
	protected I arrange() throws Exception {
		return relation.arrange(original.arrange());
	}
	
	@Override
	protected O act(I input) throws Exception {
		return original.act(input);
	}
	
	@Override
	protected void assertOutput(O ouptput) throws AssertionError {
		relation.assertOutput(original.getOutput(), ouptput);
	}
}
