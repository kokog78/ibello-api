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
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="step")
@XmlType(propOrder={"performanceGroup", "children"})
public class StepElement extends ParentElement {

	private String performanceGroup;
	private List<Element> children;
	
	public String getPerformanceGroup() {
		return performanceGroup;
	}
	
	@XmlAttribute(name="performance-group")
	public void setPerformanceGroup(String performanceGroup) {
		this.performanceGroup = performanceGroup;
	}
	
	public List<Element> getChildren() {
		if (children == null) {
			children = new ArrayList<>();
		}
		return children;
	}
	
	@XmlElementRefs({
		@XmlElementRef(name="step", type=StepElement.class),
		@XmlElementRef(name="action", type=ActionElement.class),
		@XmlElementRef(name="expectation", type=ExpectationElement.class)
	})
	public void setChildren(List<Element> children) {
		this.children = children;
	}
	
	@Override
	public ElementType getType() {
		return ElementType.STEP;
	}
	
}
