package hu.ibello.testcase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Test;

public class MetamorphTestCaseGeneratorTest {
	
	private MetamorphTestCaseGenerator generator = new MetamorphTestCaseGenerator();

	@Test
	public void should_generate_successful_metamorph_test_case() throws Exception {
		SimpleTestCase tc = new SimpleTestCase(1, 10, 11);
		tc.run();
		
		ComponentTestCase<Integer[], Integer> metamorh = generator.generate(tc, new AdditionRelation(5));
		metamorh.run();

		assertThat(metamorh.getOutput()).isEqualTo(16);
	}

	@Test
	public void should_generate_failed_metamorph_test_case() throws Exception {
		SimpleTestCase tc = new SimpleTestCase(1, 10, 11);
		tc.run();
		
		ComponentTestCase<Integer[], Integer> metamorh = generator.generate(tc, new AdditionRelation(-5));
		assertThatThrownBy(() -> metamorh.run()).isInstanceOf(AssertionError.class);

		assertThat(metamorh.getOutput()).isEqualTo(6);
	}

	@Test
	public void should_chain_metamorph_test_cases() throws Exception {
		SimpleTestCase tc = new SimpleTestCase(1, 10, 11);
		tc.run();
		
		ComponentTestCase<Integer[], Integer> metamorh = generator.generate(tc, new AdditionRelation(5));
		metamorh.run();
		metamorh = generator.generate(metamorh, new AdditionRelation(10));
		metamorh.run();

		assertThat(metamorh.getOutput()).isEqualTo(26);
	}
}
