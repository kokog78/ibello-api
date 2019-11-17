package hu.ibello.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="memory-usage")
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
