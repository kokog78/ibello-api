package hu.ibello.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="action")
public class ActionElement extends Element {

	private long timeoutMs;
	private TextFormatKind nameFormat;
	
	public long getTimeoutMs() {
		return timeoutMs;
	}
	
	@XmlAttribute(name="timeout-ms")
	public void setTimeoutMs(long timeoutMs) {
		this.timeoutMs = timeoutMs;
	}
	
	public TextFormatKind getNameFormat() {
		return nameFormat;
	}
	
	@XmlAttribute(name="name-format")
	public void setNameFormat(TextFormatKind nameFormat) {
		this.nameFormat = nameFormat;
	}
}
