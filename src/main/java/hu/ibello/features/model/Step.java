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
package hu.ibello.features.model;

import java.util.ArrayList;
import java.util.List;

public class Step {

	private String keyword;
	
	private StepKind kind;
	
    private String text;
    
    private List<Step> steps;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public StepKind getKind() {
		return kind;
	}

	public void setKind(StepKind kind) {
		this.kind = kind;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
    
	public boolean hasStep() {
		return steps != null && !steps.isEmpty();
	}
	
    public List<Step> getSteps() {
    	if (steps == null) {
    		steps = new ArrayList<>();
    	}
		return steps;
	}
}
