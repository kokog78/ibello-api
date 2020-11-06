package hu.ibello.elements;

import hu.ibello.core.WindowRelated;
import hu.ibello.inject.Inject;

/**
 * Abstract class for a web element repository.
 * @author Kornél Simon
 *
 */
public abstract class ElementRepository extends WindowRelated {

	@Inject
	private ElementRepositoryTool tool;
	
	/**
	 * Should return the path of the file which contains the data of the repository.
	 * The path should be relative to the ibello page object directory.
	 * @return the relative path of the repository file
	 */
	public abstract String getPath();
	
	/**
	 * Reads the named element configuration data from the repository and searches the element.
	 * @param name the name of the element configuration - as included in the repository file
	 * @return the web element
	 */
	protected WebElement element(String name) {
		tool.initialize(getPath(), getWindowId());
		return tool.element(name);
	}
	
	/**
	 * Reads the named element configuration data from the repository and searches the elements.
	 * @param name the name of the element configuration - as included in the repository file
	 * @return the web elements
	 */
	protected WebElements elements(String name) {
		tool.initialize(getPath(), getWindowId());
		return tool.elements(name);
	}

}
