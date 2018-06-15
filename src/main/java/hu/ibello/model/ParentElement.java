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

@XmlType(propOrder={"successCount", "failureCount", "errorCount", "pendingCount"})
public class ParentElement extends Element {

	private int successCount;
	private int failureCount;
	private int errorCount;
	private int pendingCount;
	
	public int getSuccessCount() {
		return successCount;
	}
	
	@XmlAttribute(name="success-count")
	public void setSuccessCount(int successCount) {
		this.successCount = successCount;
	}
	
	public int getPendingCount() {
		return pendingCount;
	}
	
	@XmlAttribute(name="pending-count")
	public void setPendingCount(int pendingCount) {
		this.pendingCount = pendingCount;
	}
	
	public int getFailureCount() {
		return failureCount;
	}
	
	@XmlAttribute(name="failure-count")
	public void setFailureCount(int failureCount) {
		this.failureCount = failureCount;
	}
	
	public int getErrorCount() {
		return errorCount;
	}
	
	@XmlAttribute(name="error-count")
	public void setErrorCount(int errorCount) {
		this.errorCount = errorCount;
	}
}
