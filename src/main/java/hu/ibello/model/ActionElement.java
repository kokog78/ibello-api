package hu.ibello.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="action")
public class ActionElement extends Element {

	private Long timeoutMs;
	private TextFormatKind nameFormat;
	
	public Long getTimeoutMs() {
		return timeoutMs;
	}
	
	@XmlAttribute(name="timeout-ms")
	public void setTimeoutMs(Long timeoutMs) {
		this.timeoutMs = timeoutMs;
	}
	
	public TextFormatKind getNameFormat() {
		return nameFormat;
	}
	
	@XmlAttribute(name="name-format")
	public void setNameFormat(TextFormatKind nameFormat) {
		this.nameFormat = nameFormat;
	}
	
	@Override
	public ElementType getType() {
		return ElementType.ACTION;
	}
}
