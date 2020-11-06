package hu.ibello.elements;

import hu.ibello.core.Value;
import hu.ibello.core.WindowRelated;
import hu.ibello.inject.Inject;
import hu.ibello.inject.Injectable;
import hu.ibello.inject.Scope;
import hu.ibello.search.SearchTool;

/**
 * Abstract class for a web element repository.
 * @author Kornél Simon
 *
 */
@Injectable(Scope.ELEMENTS)
public abstract class ElementRepository extends WindowRelated {

	@Inject
	private ElementRepositoryTool tool;
	
	private String repositoryPath;
	
	/**
	 * Sets the path of the file which contains the data of the repository.
	 * The path should be relative to the ibello page object directory.
	 * @param path the relative path of the repository file
	 */
	protected void setRepositoryPath(String repositoryPath) {
		this.repositoryPath = repositoryPath;
	}
	
	/**
	 * Reads the named element configuration data from the repository and searches the element.
	 * @param name the name of the element configuration - as included in the repository file
	 * @return the web element
	 */
	protected WebElement element(String name) {
		tool.initialize(repositoryPath, getWindowId());
		return tool.element(name);
	}
	
	/**
	 * Reads the named element configuration data from the repository and searches the elements.
	 * @param name the name of the element configuration - as included in the repository file
	 * @return the web elements
	 */
	protected WebElements elements(String name) {
		tool.initialize(repositoryPath, getWindowId());
		return tool.elements(name);
	}

	/**
	 * <p>
	 * Returns a {@link SearchTool} instance which is used to search elements on the current page.
	 * </p>
	 * <p>
	 * The returned instance offers a fluent interface for element search. Example:
	 * </p>
	 * <pre>
	 * WebElement image = ...;
	 * WebElement child = find().using(By.TAG_NAME, "span").leftFrom(image).first();
	 * </pre>
	 * @return an object used for element search on the page
	 */
	protected SearchTool find() {
		tool.initialize(repositoryPath, getWindowId());
		return tool.find();
	}

	/**
	 * Returns a configuration property as a {@link Value}. The returned value offers some public methods to
	 * transform the configuration property into different java types.
	 * This method always has a non-null result, even if the configuration value does not exist - in this case,
	 * the wrapped value will be <code>null</code>.
	 * @param name name of the configuration parameter
	 * @return value of the configuration parameter wrapped into a {@link Value} instance
	 */
	protected Value getConfigurationValue(String name) {
		return tool.getConfigurationValue(name);
	}
}
