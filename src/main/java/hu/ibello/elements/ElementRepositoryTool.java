package hu.ibello.elements;

import hu.ibello.core.Value;
import hu.ibello.search.SearchTool;

public interface ElementRepositoryTool {
	
	void initialize(String repositoryPath, String windowId);

	WebElement element(String elementId);
	
	WebElements elements(String elementId);
	
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
	SearchTool find();

	/**
	 * Returns a configuration property as a {@link Value}. The returned value offers some public methods to
	 * transform the configuration property into different java types.
	 * This method always has a non-null result, even if the configuration value does not exist - in this case,
	 * the wrapped value will be <code>null</code>.
	 * @param name name of the configuration parameter
	 * @return value of the configuration parameter wrapped into a {@link Value} instance
	 */
	Value getConfigurationValue(String name);
	
}
