package hu.ibello.data;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import hu.ibello.core.Name;

public class TestDataToolsTest {

	@Test(expected = NullPointerException.class)
	public void getEnumName_should_throw_NPE_if_parameter_is_null() throws Exception {
		TestDataTools.getEnumName(null);
	}
	
	@Test
	public void getEnumName_should_read_name_annotation() throws Exception {
		String result = TestDataTools.getEnumName(TestEnum.X);
		assertThat(result).isEqualTo("X name");
	}
	
	@Test
	public void getEnumName_should_return_original_name() throws Exception {
		String result = TestDataTools.getEnumName(TestEnum.Y);
		assertThat(result).isEqualTo("Y");
	}
	
	@Test(expected = NullPointerException.class)
	public void getClassName_should_throw_NPE_if_parameter_is_null() throws Exception {
		TestDataTools.getClassName(null);
	}
	
	@Test
	public void getClassName_should_read_name_annotation() throws Exception {
		String result = TestDataTools.getClassName(TestEnum.class);
		assertThat(result).isEqualTo("Name of enum");
	}
	
	@Test
	public void getClassName_should_result_simple_name() throws Exception {
		String result = TestDataTools.getClassName(TestDataToolsTest.class);
		assertThat(result).isEqualTo("TestDataToolsTest");
	}
	
	@Name("Name of enum")
	public enum TestEnum {
		
		@Name("X name")
		X,
		
		Y;
	}
}
