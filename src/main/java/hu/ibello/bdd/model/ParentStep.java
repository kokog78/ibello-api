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
 * Describes a test step which may contain child steps.
 * @author Kornél Simon
 */
public class ParentStep extends Step {

    private List<ParentStep> steps;

    /**
	 * Are there any child steps in the step?
	 * @return <code>true</code> if step has child steps
	 */
	public boolean hasStep() {
		return steps != null && !steps.isEmpty();
	}
	
	/**
	 * Returns the child steps of the step.
	 * If the step does not have child steps, it returns an empty list.
	 * @return the child steps of the step
	 */
    public List<ParentStep> getSteps() {
    	if (steps == null) {
    		steps = new ArrayList<>();
    	}
		return steps;
	}
    
    /**
	 * Sets the child steps of the step.
	 * If the method is called with <code>null</code>, the result of the {@link #getSteps()} method will be an empty list.
	 * @param steps the child steps of the step
	 */
    public void setSteps(List<ParentStep> steps) {
		this.steps = steps;
	}

}
