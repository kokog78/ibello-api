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
package hu.ibello.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder={"specifications", "tests", "steps", "expectations", "actions"})
public class Counters {

	private long specifications;
	private long tests;
	private long steps;
	private long expectations;
	private long actions;
	
	public long getSpecifications() {
		return specifications;
	}
	
	@XmlAttribute
	public void setSpecifications(long specification) {
		this.specifications = specification;
	}
	
	public long getTests() {
		return tests;
	}
	
	@XmlAttribute
	public void setTests(long tests) {
		this.tests = tests;
	}
	
	public long getSteps() {
		return steps;
	}
	
	@XmlAttribute
	public void setSteps(long steps) {
		this.steps = steps;
	}
	
	public long getExpectations() {
		return expectations;
	}
	
	@XmlAttribute
	public void setExpectations(long expectations) {
		this.expectations = expectations;
	}
	
	public long getActions() {
		return actions;
	}
	
	@XmlAttribute
	public void setActions(long action) {
		this.actions = action;
	}
	
}
