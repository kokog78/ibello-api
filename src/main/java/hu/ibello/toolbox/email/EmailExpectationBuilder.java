package hu.ibello.toolbox.email;

/**
 * This interface is an expectation builder for checking email attributes.
 * <p>
 * By default, it uses the "<code>ibello.timeouts.toolbox.email</code>" timeout value. (If it is not specified in the configuration,
 * then the default timeout is used.) This can be changed with the {@link #withTimeout(String)} or {@link #withTimeout(Enum)} method.
 * </p>
 * <p>
 * You can start to build the expectation with the {@link #toHave()} or {@link #toNotHave()} method.
 * </p>
 * @author Kornél Simon
 *
 */
public interface EmailExpectationBuilder {

	/**
	 * Expectation builder method. It returns a new interface which has another methods
	 * to finish and execute the expectation. The result will the opposite of {@link #toNotHave()}.
	 * @return {@link EmailHaveExpectations} instance to finish and execute the expectation
	 */
	public EmailHaveExpectations toHave();
	
	/**
	 * Expectation builder method. It returns a new interface which has another methods
	 * to finish and execute the expectation. The result will the opposite of {@link #toHave()}.
	 * @return {@link EmailHaveExpectations} instance to finish and execute the expectation
	 */
	public EmailHaveExpectations toNotHave();
	
	/**
	 * Expectation builder method. It returns a new interface which has another methods
	 * to finish and execute the expectation. The result will the opposite of {@link #toNotBe()}.
	 * @return {@link EmailBeExpectations} instance to finish and execute the expectation
	 */
	public EmailBeExpectations toBe();
	
	/**
	 * Expectation builder method. It returns a new interface which has another methods
	 * to finish and execute the expectation. The result will the opposite of {@link #toBe()}.
	 * @return {@link EmailBeExpectations} instance to finish and execute the expectation
	 */
	public EmailBeExpectations toNotBe();
	
	/**
	 * Sets the timeout of this expectation.
	 * @param key key of the timeout
	 * @return the same builder object
	 */
	public EmailExpectationBuilder withTimeout(String key);
	
	/**
	 * Sets the timeout of this expectation.
	 * @param key key of	 the timeout
	 * @return the same builder object
	 */
	public EmailExpectationBuilder withTimeout(Enum<?> key);
	
}
