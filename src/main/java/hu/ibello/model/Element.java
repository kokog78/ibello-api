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

@XmlType(propOrder={"name", "outcome", "durationMs", "latencyMs", "exception", "screenshot"})
public class Element {

	private String name;
	private Outcome outcome;
	private Long durationMs;
	private Long latencyMs;
	private List<ExceptionInfo> exception;
	private List<Screenshot> screenshot;
	
	public String getName() {
		return name;
	}
	
	@XmlAttribute
	public void setName(String name) {
		this.name = name;
	}
	
	public Outcome getOutcome() {
		return outcome;
	}
	
	@XmlAttribute
	public void setOutcome(Outcome outcome) {
		this.outcome = outcome;
	}
	
	public Long getDurationMs() {
		return durationMs;
	}
	
	@XmlAttribute(name="duration-ms")
	public void setDurationMs(Long durationMs) {
		this.durationMs = durationMs;
	}
	
	public Long getLatencyMs() {
		return latencyMs;
	}
	
	@XmlAttribute(name="latency-ms")
	public void setLatencyMs(Long latencyMs) {
		this.latencyMs = latencyMs;
	}
	
	public List<ExceptionInfo> getException() {
		if (exception == null) {
			exception = new ArrayList<>();
		}
		return exception;
	}
	
	public void setException(List<ExceptionInfo> exception) {
		this.exception = exception;
	}
	
	public List<Screenshot> getScreenshot() {
		if (screenshot == null) {
			screenshot = new ArrayList<>();
		}
		return screenshot;
	}
	
	public void setScreenshot(List<Screenshot> screenshot) {
		this.screenshot = screenshot;
	}
	
	public ElementType getType() {
		return null;
	}
	
}
