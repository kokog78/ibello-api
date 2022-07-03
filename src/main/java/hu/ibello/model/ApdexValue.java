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

@XmlType(propOrder={"id", "value", "satisfiedThresholdMs", "toleratedThresholdMs"})
public class ApdexValue {

	private String id;
	
	private double value;
	
	private long satisfiedThresholdMs;
	
	private long toleratedThresholdMs;

	public String getId() {
		return id;
	}

	@XmlAttribute
	public void setId(String id) {
		this.id = id;
	}

	public double getValue() {
		return value;
	}

	@XmlAttribute
	public void setValue(double value) {
		this.value = value;
	}
	
	public long getSatisfiedThresholdMs() {
		return satisfiedThresholdMs;
	}
	
	@XmlAttribute(name="satisfied-threshold-ms")
	public void setSatisfiedThresholdMs(long satisfiedThresholdMs) {
		this.satisfiedThresholdMs = satisfiedThresholdMs;
	}
	
	public long getToleratedThresholdMs() {
		return toleratedThresholdMs;
	}
	
	@XmlAttribute(name="tolerated-threshold-ms")
	public void setToleratedThresholdMs(long toleratedThresholdMs) {
		this.toleratedThresholdMs = toleratedThresholdMs;
	}
	
}
