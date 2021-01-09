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

/**
 * Describes a test step from a BDD document.
 * @author Kornél Simon
 */
public class Step {

	private String keyword;
	
	private StepKind kind;
	
    private String text;
    
    private List<Parameter> parameters;
    
    /**
	 * Returns the keyword of the step.
	 * It depends on the language of the document.
	 * For english language, it can be "Given", "When", "Then", "And" and "But".
	 * @return the keyword of the step
	 */
	public String getKeyword() {
		return keyword;
	}

	/**
	 * Sets the keyword of the step.
	 * It depends on the language of the document.
	 * For english language, it should be "Given", "When", "Then", "And" and "But".
	 * @param keyword the keyword of the step
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	/**
	 * Returns the recognized type of the test step's keyword.
	 * The "And" and "But" keywords are not returned, instead of it
	 * they are replaced with the correct GIVEN, WHEN or THEN type.
	 * If the type cannot be recognized, this method returns {@link StepKind#UNKNOWN}.
	 * @return the type of the test step's keyword
	 */
	public StepKind getKind() {
		return kind;
	}

	/**
	 * Sets the recognized type of the test step's keyword.
	 * @param kind type of the test step's keyword
	 */
	public void setKind(StepKind kind) {
		this.kind = kind;
	}

	/**
	 * Returns the text of the test step, without the keyword.
	 * @return the text of the test step
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets the text of the test step, without the keyword.
	 * @param text the text of the test step
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * Are there any parameters in the step?
	 * @return <code>true</code> if step has parameters
	 */
	public boolean hasParameter() {
		return parameters != null && !parameters.isEmpty();
	}
	
	/**
	 * Returns the parameters of the step.
	 * If the step does not have parameters, it returns an empty list.
	 * @return the parameters of the step
	 */
	public List<Parameter> getParameters() {
		if (parameters == null) {
			parameters = new ArrayList<>();
		}
		return parameters;
	}
	
	/**
	 * Sets the parameters of the step.
	 * If the method is called with <code>null</code>, the result of the {@link #getParameters()} method will be an empty list.
	 * @param parameters the parameters of the step
	 */
	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}
	
	@Override
	public String toString() {
		return String.format("%s %s", keyword, text);
	}
    
}
