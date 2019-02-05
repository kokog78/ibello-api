package hu.ibello.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="action")
public class ActionElement extends Element {

	private long timeoutMs;
	
	public long getTimeoutMs() {
		return timeoutMs;
	}
	
	@XmlAttribute(name="timeout-ms")
	public void setTimeoutMs(long timeoutMs) {
		this.timeoutMs = timeoutMs;
	}
}
