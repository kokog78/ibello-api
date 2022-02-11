package hu.ibello.testcase;

import java.util.List;

public class MetamorphTestCaseGenerator {

	public <I, O> ComponentTestCase<I, O> generate(ComponentTestCase<I, O> original, MetamorphRelation<I, O> relation) {
		return new MetamorhTestCase<>(original, relation);
	}
	
//	public <I, O> List<ComponentTestCase<I, O>> generate(ComponentTestCase<I, O> original, MetamorphRelations<I, O> relations) {
//		
//	}
}
