package hu.ibello.steps;

import hu.ibello.core.Value;

/**
 * Helper interface for step libraries.
 * <p>
 * There is no need to use this interface directly.
 * The <em>ibello</em> dependency injection system automatically instantiates and injects
 * an instance into step libraries.
 * </p>
 * @see StepLibrary
 * @author Kornél Simon
 */
public interface StepLibraryTool {

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
