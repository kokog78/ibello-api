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

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder={"performanceGroup", "tag", "step"})
public class TestElement extends ParentElement {

	private String performanceGroup;
	private List<String> tag;
	private List<StepElement> step;
	
	public String getPerformanceGroup() {
		return performanceGroup;
	}
	
	@XmlAttribute(name="performance-group")
	public void setPerformanceGroup(String performanceGroup) {
		this.performanceGroup = performanceGroup;
	}
	
	public List<String> getTag() {
		if (tag == null) {
			tag = new ArrayList<>();
		}
		return tag;
	}
	
	public void setTag(List<String> tag) {
		this.tag = tag;
	}
	
	public List<StepElement> getStep() {
		if (step == null) {
			step = new ArrayList<>();
		}
		return step;
	}
	
	public void setStep(List<StepElement> step) {
		this.step = step;
	}
	
	@Override
	public ElementType getType() {
		return ElementType.TEST;
	}
	
}
