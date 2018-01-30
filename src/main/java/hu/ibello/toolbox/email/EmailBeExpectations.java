package hu.ibello.toolbox.email;

/**
 * Closing part of an expectation builder. With the methods of this interface we can execute the expectations.
 * @author Kornél Simon
 *
 */
public interface EmailBeExpectations {

	/**
	 * Expectation about the existence of the email.
	 */
	public void present();
	
}
