package hu.ibello.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="java-memory")
public class JavaMemoryUsage {

	private long total;
	private long used;
	
	public long getTotal() {
		return total;
	}
	
	@XmlAttribute
	public void setTotal(long total) {
		this.total = total;
	}
	
	public long getUsed() {
		return used;
	}
	
	@XmlAttribute
	public void setUsed(long used) {
		this.used = used;
	}
}
