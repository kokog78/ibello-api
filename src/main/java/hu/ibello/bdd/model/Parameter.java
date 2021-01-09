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

/**
 * Describes a parameter of a test step.
 * @author Kornél Simon
 */
public class Parameter {

	private ParameterKind kind;
	
	private String text;

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
		this.text = text;
	}
	
	/**
	 * Sets the numeric content of the parameter.
	 * It calls the {@link #setText(String)} method directly.
	 * @param value the numeric content of the parameter
	 */
	public void setValue(long value) {
		setText(Long.toString(value));
	}
	
}
