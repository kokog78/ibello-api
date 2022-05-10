package hu.ibello.model;

public enum TextFormatKind {

	EMPTY,
	NUMBER,
	BOOLEAN,
	JSON,
	XML,
	HTML;
	
	public boolean isDocument() {
		return this == JSON || this == XML || this == HTML;
	}
}
