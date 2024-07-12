package hu.ibello.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class Comment {

	private String title;
	
	private String text;

	public String getTitle() {
		return title;
	}

	@XmlAttribute
	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	@XmlValue
	public void setText(String text) {
		this.text = text;
	}
	
}
