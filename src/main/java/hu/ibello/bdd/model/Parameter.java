/*
 * Ark-Sys Kft. licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package hu.ibello.bdd.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Describes a parameter of a test step.
 * It's content can be a primitive value (string, number or logical value), or a complex data tale.
 * <p>
 * For primitive type parameters, the {@link #getText()},{@link #setText(String)}, {@link #setValue(long)},
 * {@link #setValue(double)}, {@link #setValue(boolean)} methods can be used to access the content.
 * <p>
 * For data tables, the {@link #getRows()}, {@link #getMaps()}, {@link #getObjects(ParameterListTransformer)},
 * {@link #getObjects(ParameterMapTransformer)} and {@link #setRows(List)} methods can help to manipulate
 * the table data.
 * @author Kornél Simon
 */
public class Parameter {

	private ParameterKind kind;
	
	private String text;
	
	private List<List<String>> rows;

	/**
	 * Returns the type of the parameter.
	 * @return the type of the parameter
	 */
	public ParameterKind getKind() {
		return kind;
	}

	/**
	 * Sets the type of the parameter.
	 * @param kind the type of the parameter
	 */
	public void setKind(ParameterKind kind) {
		this.kind = kind;
		if (kind == ParameterKind.DATATABLE) {
			this.text = null;
		} else {
			this.rows = null;
		}
	}

	/**
	 * Returns the text content of the parameter.
	 * For string parameters, it returns the text between the quotation marks (without the quotation marks).
	 * For numeric parameter, it returns the string representation of the number.
	 * @return the text content of the parameter
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets the text content of the parameter.
	 * For string parameters, it should be the text between the quotation marks (without the quotation marks).
	 * For numeric parameter, it should be the string representation of the number.
	 * @param text the text content of the parameter
	 */
	public void setText(String text) {
		if (this.kind == ParameterKind.DATATABLE) {
			throw new UnsupportedOperationException("Cannot set text value of data table parameter.");
		}
		this.text = text;
		this.rows = null;
	}
	
	/**
	 * Sets the numeric content of the parameter.
	 * It calls the {@link #setText(String)} method directly.
	 * @param value the numeric content of the parameter
	 */
	public void setValue(long value) {
		setText(Long.toString(value));
	}
	
	/**
	 * Sets the numeric content of the parameter.
	 * It calls the {@link #setText(String)} method directly.
	 * @param value the numeric content of the parameter
	 */
	public void setValue(double value) {
		setText(Double.toString(value));
	}
	
	/**
	 * Sets the logical content of the parameter.
	 * It calls the {@link #setText(String)} method directly.
	 * @param value the logical content of the parameter
	 */
	public void setValue(boolean value) {
		setText(Boolean.toString(value));
	}
	
	/**
	 * Returns the tabular content of the parameter.
	 * The result is a list of rows, where each row is a list of cells (strings).
	 * If the parameter is not a data table, then this method return <code>null</code>.
	 * @return the tabular content of the parameter
	 */
	public List<List<String>> getRows() {
		return rows;
	}
	
	/**
	 * Transforms the content of the data table to list of maps.
	 * The first row in the table is considered as header.
	 * The cells in the header will be the keys in the maps.
	 * @return the tabular content of the parameter as list of maps
	 */
	public List<Map<String, String>> getMaps() {
		if (rows == null) {
			return null;
		}
		List<Map<String, String>> maps = new ArrayList<>();
		List<String> header = null;
		for (List<String> row : rows) {
			if (header == null) {
				header = row;
			} else {
				Map<String, String> map = new TreeMap<>();
				for (int i=0; i<header.size(); i++) {
					if (i < row.size()) {
						map.put(header.get(i), row.get(i));
					}
				}
				maps.add(map);
			}
		}
		return maps;
	}
	
	/**
	 * Transforms the content of the data table to list of objects.
	 * An object is transformed by the <pre>transformer</pre> argument.
	 * The transformer will receive all rows, including the first one.
	 * @param <T> the type of the elements in the result list
	 * @param transformer transforms a table row into a result object
	 * @return the tabular content of the parameter as list of objects
	 */
	public <T> List<T> getObjects(ParameterListTransformer<T> transformer) {
		if (rows == null) {
			return null;
		}
		List<T> objects = new ArrayList<>();
		for (List<String> row : rows) {
			T object = transformer.transform(row);
			objects.add(object);
		}
		return objects;
	}
	
	/**
	 * Transforms the content of the data table to list of objects.
	 * An object is transformed by the <pre>transformer</pre> argument.
	 * The first row in the table will be considered as header, the transformer will not receive it.
	 * @param <T> the type of the elements in the result list
	 * @param transformer transforms a table row into a result object
	 * @return the tabular content of the parameter as list of objects
	 */
	public <T> List<T> getObjects(ParameterMapTransformer<T> transformer) {
		List<Map<String, String>> maps = getMaps();
		if (maps == null) {
			return null;
		}
		List<T> objects = new ArrayList<>();
		for (Map<String, String> row : maps) {
			T object = transformer.transform(row);
			objects.add(object);
		}
		return objects;
	}
	
	/**
	 * Sets the tabular content of the parameter.
	 * It is a list of rows, where each row is a list of cells (strings).
	 * @param rows the tabular content of the parameter
	 */
	public void setRows(List<List<String>> rows) {
		if (this.kind != ParameterKind.DATATABLE) {
			throw new UnsupportedOperationException("Only data table parameter can have rows.");
		}
		this.rows = rows;
	}
	
}
