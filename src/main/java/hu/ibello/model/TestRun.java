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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="test-run")
@XmlType(propOrder={"modelVersion", "kind", "applicationVersion", "startTime", "endTime", "baseDirectory", "browser", "headless", "defaultTimeout", "threads", "counters", "windowSize", "responsibleParty", "logFile", "tag", "spec", "memoryUsage", "apdex"})
public class TestRun extends ParentElement implements ITestRun {

	private String modelVersion;
	private TestRunKind kind;
	private String applicationVersion;
	private Date startTime;
	private Date endTime;
	private String baseDirectory;
	private BrowserKind browser;
	private boolean headless;
	private long defaultTimeout;
	private Integer threads;
	private Counters counters;
	private WindowSize windowSize;
	private String responsibleParty;
	private List<LogFile> logFile;
	private List<String> tag;
	private List<SpecElement> spec;
	private List<MemoryUsage> memoryUsage;
	private List<ApdexValue> apdex;
	
	public String getModelVersion() {
		return modelVersion;
	}
	
	@XmlAttribute(name="model-version")
	public void setModelVersion(String modelVersion) {
		this.modelVersion = modelVersion;
	}
	
	public TestRunKind getKind() {
		return kind;
	}
	
	@XmlAttribute
	public void setKind(TestRunKind kind) {
		this.kind = kind;
	}
	
	public String getApplicationVersion() {
		return applicationVersion;
	}
	
	@XmlAttribute(name="application-version")
	public void setApplicationVersion(String applicationVersion) {
		this.applicationVersion = applicationVersion;
	}
	
	@Override
	public Date getStartTime() {
		return startTime;
	}
	
	@XmlAttribute(name="start-time")
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	public Date getEndTime() {
		return endTime;
	}
	
	@XmlAttribute(name="end-time")
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	@Override
	public String getBaseDirectory() {
		return baseDirectory;
	}
	
	@XmlElement(name="base-directory")
	public void setBaseDirectory(String baseDirectory) {
		this.baseDirectory = baseDirectory;
	}
	
	@Override
	public BrowserKind getBrowser() {
		return browser;
	}
	
	@XmlAttribute
	public void setBrowser(BrowserKind browser) {
		this.browser = browser;
	}
	
	@Override
	public boolean isHeadless() {
		return headless;
	}
	
	@XmlAttribute
	public void setHeadless(boolean headless) {
		this.headless = headless;
	}
	
	@Override
	public long getDefaultTimeout() {
		return defaultTimeout;
	}
	
	@XmlAttribute(name="default-timeout")
	public void setDefaultTimeout(long defaultTimeout) {
		this.defaultTimeout = defaultTimeout;
	}
	
	public Integer getThreads() {
		return threads;
	}
	
	@XmlAttribute
	public void setThreads(Integer threads) {
		this.threads = threads;
	}
	
	public Counters getCounters() {
		return counters;
	}
	
	public void setCounters(Counters counters) {
		this.counters = counters;
	}
	
	@Override
	public WindowSize getWindowSize() {
		return windowSize;
	}
	
	@XmlElement(name="window-size")
	public void setWindowSize(WindowSize windowSize) {
		this.windowSize = windowSize;
	}
	
	public String getResponsibleParty() {
		return responsibleParty;
	}
	
	@XmlElement(name="responsible-party")
	public void setResponsibleParty(String responsibleParty) {
		this.responsibleParty = responsibleParty;
	}
	
	public List<LogFile> getLogFile() {
		if (logFile == null) {
			logFile = new ArrayList<>();
		}
		return logFile;
	}
	
	@XmlElement(name="log-file")
	public void setLogFile(List<LogFile> logFile) {
		this.logFile = logFile;
	}
	
	@Override
	public List<String> getTag() {
		if (tag == null) {
			tag = new ArrayList<>();
		}
		return tag;
	}
	
	public void setTag(List<String> tag) {
		this.tag = tag;
	}
	
	public List<SpecElement> getSpec() {
		if (spec == null) {
			spec = new ArrayList<>();
		}
		return spec;
	}
	
	public void setSpec(List<SpecElement> spec) {
		this.spec = spec;
	}
	
	public List<MemoryUsage> getMemoryUsage() {
		if (memoryUsage == null) {
			memoryUsage = new ArrayList<>();
		}
		return memoryUsage;
	}
	
	@XmlElement(name="memory-usage")
	public void setMemoryUsage(List<MemoryUsage> memoryUsage) {
		this.memoryUsage = memoryUsage;
	}
	
	public List<ApdexValue> getApdex() {
		if (apdex == null) {
			apdex = new ArrayList<>();
		}
		return apdex;
	}
	
	@XmlElement
	public void setApdex(List<ApdexValue> apdex) {
		this.apdex = apdex;
	}
	
	@Override
	public ElementType getType() {
		return ElementType.TESTRUN;
	}
	
}
