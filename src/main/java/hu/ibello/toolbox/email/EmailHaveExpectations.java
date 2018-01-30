package hu.ibello.toolbox.email;

import java.util.regex.Pattern;

/**
 * Closing part of an expectation builder. With the methods of this interface we can execute the expectations.
 * @author Kornél Simon
 *
 */
public interface EmailHaveExpectations {

	/**
	 * Expectations about the sender of an email.
	 * @param value the expected sender
	 */
	public void sender(String value);
	
	/**
	 * Expectations about the sender of an email.
	 * @param pattern the expected sender as a regular expression
	 */
	public void sender(Pattern pattern);
	
	/**
	 * Expectations about the recipient of an email.
	 * @param value the expected recipient
	 */
	public void recipient(String value);
	
	/**
	 * Expectations about the recipient of an email.
	 * @param pattern the expected recipient as a regular expression
	 */
	public void recipient(Pattern pattern);
	
	/**
	 * Expectations about the subject of an email.
	 * @param value the expected subject
	 */
	public void subject(String value);
	
	/**
	 * Expectations about the subject of an email.
	 * @param pattern the expected subject as a regular expression
	 */
	public void subject(Pattern pattern);
	
	/**
	 * Expectations about the text of an email.
	 * @param value the expected text
	 */
	public void text(String value);
	
	/**
	 * Expectations about the text of an email.
	 * @param pattern the expected text as a regular expression
	 */
	public void text(Pattern pattern);
}
