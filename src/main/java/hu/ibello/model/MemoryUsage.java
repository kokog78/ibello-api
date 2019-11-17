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
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder={"time", "elementId", "java", "browser"})
public class MemoryUsage {

	private Date time;
	private String elementId;
	private JavaMemoryUsage java;
	private List<BrowserMemoryUsage> browser;
	
	public Date getTime() {
		return time;
	}
	
	@XmlAttribute
	public void setTime(Date time) {
		this.time = time;
	}
	
	public String getElementId() {
		return elementId;
	}
	
	@XmlAttribute(name="element-id")
	public void setElementId(String elementId) {
		this.elementId = elementId;
	}
	
	public JavaMemoryUsage getJava() {
		return java;
	}
	
	public void setJava(JavaMemoryUsage java) {
		this.java = java;
	}
	
	public List<BrowserMemoryUsage> getBrowser() {
		if (browser == null) {
			browser = new ArrayList<>();
		}
		return browser;
	}
	
	public void setBrowser(List<BrowserMemoryUsage> browser) {
		this.browser = browser;
	}
	
}
