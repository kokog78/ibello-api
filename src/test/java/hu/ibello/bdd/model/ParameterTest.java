package hu.ibello.bdd.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Test;

public class ParameterTest {

	@Test(expected=UnsupportedOperationException.class)
	public void setText_should_throw_exception_if_parameter_is_a_datatable() throws Exception {
		@SuppressWarnings("unchecked")
		Parameter parameter = dataTable();
		parameter.setText("");
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void setRows_should_throw_exception_if_parameter_is_not_a_datatable() throws Exception {
		Parameter parameter = string("abc");
		parameter.setRows(Collections.emptyList());
	}
	
	@Test
	public void getMaps_should_return_null_if_parameter_is_not_a_datatable() throws Exception {
		Parameter parameter = string("abc");
		List<Map<String, String>> maps = parameter.getMaps();
		assertThat(maps).isNull();
	}
	
	@Test
	public void getMaps_should_transform_the_datatable_into_maps() throws Exception {
		@SuppressWarnings("unchecked")
		Parameter parameter = dataTable(
				row("a", "b", "c"),
				row("1", "2", "3"),
				row("x", "y"),
				row("9", "8", "7", "6")
			);
		List<Map<String, String>> maps = parameter.getMaps();
		ParameterMapTransformer<String> transformer = mapTransformer();
		assertThat(maps)
			.isNotNull()
			.extracting(map -> transformer.transform(map))
			.containsExactly("1|2|3", "x|y|null", "9|8|7");
	}
	
	@Test
	public void getOjects_should_return_null_if_parameter_is_not_a_datatable() throws Exception {
		Parameter parameter = string("abc");
		ParameterListTransformer<String> transformer1 = listTransformer();
		List<String> objects1 = parameter.getObjects(transformer1);
		ParameterMapTransformer<String> transformer2 = mapTransformer();
		List<String> objects2 = parameter.getObjects(transformer2);
		assertThat(objects1).isNull();
		assertThat(objects2).isNull();
	}
	
	@Test
	public void getOjects_should_transform_the_datatable_into_object_list() throws Exception {
		@SuppressWarnings("unchecked")
		Parameter parameter = dataTable(
				row("a", "b", "c"),
				row("1", "2", "3"),
				row("x", "y"),
				row("9", "8", "7", "6")
			);
		ParameterListTransformer<String> transformer = listTransformer();
		List<String> objects = parameter.getObjects(transformer);
		assertThat(objects)
			.isNotNull()
			.containsExactly("a|b|c", "1|2|3", "x|y", "9|8|7|6");
	}
	
	@Test
	public void getOjects_should_transform_the_datatable_into_mapped_objects() throws Exception {
		@SuppressWarnings("unchecked")
		Parameter parameter = dataTable(
				row("a", "b", "c"),
				row("1", "2", "3"),
				row("x", "y"),
				row("9", "8", "7", "6")
			);
		ParameterMapTransformer<String> transformer = mapTransformer();
		List<String> objects = parameter.getObjects(transformer);
		assertThat(objects)
			.isNotNull()
			.containsExactly("1|2|3", "x|y|null", "9|8|7");
	}
	
	@Test
	public void toString_should_generate_docstring() throws Exception {
		Parameter param1 = docstring("abc\ndef", null);
		String result1 = param1.toString();
		Parameter param2 = docstring("abc\ndef", "html");
		String result2 = param2.toString();
		assertThat(result1).isEqualTo("\"\"\"\nabc\ndef\n\"\"\"");
		assertThat(result2).isEqualTo("\"\"\"html\nabc\ndef\n\"\"\"");
	}
	
	@Test
	public void toString_should_generate_datatable() throws Exception {
		@SuppressWarnings("unchecked")
		Parameter param1 = dataTable(
				row("a", "b", "c"),
				row("111", "2", "3"),
				row("x", "yy"),
				row("9", "8", "7", "6")
			);
		String result1 = param1.toString();
		assertThat(result1).isEqualTo("| a   | b  | c |\n| 111 | 2  | 3 |\n| x   | yy |\n| 9   | 8  | 7 | 6 |");
	}
	
	private Parameter string(String text) {
		Parameter parameter = new Parameter();
		parameter.setKind(ParameterKind.STRING);
		parameter.setText(text);
		return parameter;
	}
	
	private Parameter docstring(String text, String type) {
		Parameter parameter = new Parameter();
		parameter.setKind(ParameterKind.DOCSTRING);
		parameter.setText(text);
		parameter.setContentType(type);
		return parameter;
	}
	
	@SuppressWarnings("unchecked")
	private Parameter dataTable(List<String> ... rows) {
		Parameter parameter = new Parameter();
		parameter.setKind(ParameterKind.DATATABLE);
		parameter.setRows(rows(rows));
		return parameter;
	}
	
	@SuppressWarnings("unchecked")
	private List<List<String>> rows(List<String> ... rows) {
		return Arrays.asList(rows);
	}
	
	private List<String> row(String ... cells) {
		return Arrays.asList(cells);
	}
	
	private ParameterListTransformer<String> listTransformer() {
		return cells -> cells.stream().collect(Collectors.joining("|"));
	}
	
	private ParameterMapTransformer<String> mapTransformer() {
		return map -> String.format("%s|%s|%s", map.get("a"), map.get("b"), map.get("c"));
	}
	
}
