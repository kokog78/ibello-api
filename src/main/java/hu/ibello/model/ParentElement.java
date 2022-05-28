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

@XmlType(propOrder={"id", "successCount", "failureCount", "errorCount", "pendingCount"})
public class ParentElement extends Element {

	private String id;
	private Integer successCount;
	private Integer failureCount;
	private Integer errorCount;
	private Integer pendingCount;
	
	public String getId() {
		return id;
	}
	
	@XmlAttribute
	public void setId(String id) {
		this.id = id;
	}
	
	public Integer getSuccessCount() {
		return successCount;
	}
	
	@XmlAttribute(name="success-count")
	public void setSuccessCount(Integer successCount) {
		this.successCount = successCount;
	}
	
	public void addSuccessCount(int value) {
		if (value != 0) {
			if (successCount == null) {
				successCount = value;
			} else {
				successCount += value;
			}
		}
	}
	
	public Integer getPendingCount() {
		return pendingCount;
	}
	
	@XmlAttribute(name="pending-count")
	public void setPendingCount(Integer pendingCount) {
		this.pendingCount = pendingCount;
	}
	
	public void addPendingCount(int value) {
		if (value != 0) {
			if (pendingCount == null) {
				pendingCount = value;
			} else {
				pendingCount += value;
			}
		}
	}
	
	public Integer getFailureCount() {
		return failureCount;
	}
	
	@XmlAttribute(name="failure-count")
	public void setFailureCount(Integer failureCount) {
		this.failureCount = failureCount;
	}
	
	public void addFailureCount(int value) {
		if (value != 0) {
			if (failureCount == null) {
				failureCount = value;
			} else {
				failureCount += value;
			}
		}
	}
	
	public Integer getErrorCount() {
		return errorCount;
	}
	
	@XmlAttribute(name="error-count")
	public void setErrorCount(Integer errorCount) {
		this.errorCount = errorCount;
	}

	public void addErrorCount(int value) {
		if (value != 0) {
			if (errorCount == null) {
				errorCount = value;
			} else {
				errorCount += value;
			}
		}
	}
	
}
