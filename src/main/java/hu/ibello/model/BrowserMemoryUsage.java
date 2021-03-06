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

@XmlType(propOrder={"windowId", "total", "used"})
public class BrowserMemoryUsage {

	private String windowId;
	private Long total;
	private Long used;
	
	public String getWindowId() {
		return windowId;
	}
	
	@XmlAttribute(name="window-id")
	public void setWindowId(String windowId) {
		this.windowId = windowId;
	}
	
	public Long getTotal() {
		return total;
	}
	
	@XmlAttribute
	public void setTotal(Long total) {
		this.total = total;
	}
	
	public Long getUsed() {
		return used;
	}
	
	@XmlAttribute
	public void setUsed(Long used) {
		this.used = used;
	}

}
