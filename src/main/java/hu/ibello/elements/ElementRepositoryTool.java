package hu.ibello.elements;

public interface ElementRepositoryTool {
	
	void initialize(String path, String windowId);

	WebElement element(String elementId);
	
	WebElements elements(String elementId);
	
}
