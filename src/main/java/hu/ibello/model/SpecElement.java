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

@XmlType(propOrder={"version", "test"})
public class SpecElement extends ParentElement {

	private String version;
	private List<TestElement> test;
	
	public String getVersion() {
		return version;
	}
	
	@XmlAttribute
	public void setVersion(String version) {
		this.version = version;
	}
	
	public List<TestElement> getTest() {
		if (test == null) {
			test = new ArrayList<>();
		}
		return test;
	}
	
	public void setTest(List<TestElement> test) {
		this.test = test;
	}
}
