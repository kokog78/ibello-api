package hu.ibello.toolbox.email;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import hu.ibello.inject.Inject;

/**
 * This interface is designed for checking email communication during "ibello" tests.
 * Some programs are sending emails to recipients, and in some cases we want to verify that an email with some attributes was sent.
 * To use this tool you need to prepare your tests a little bit.
 * <ol>
 * <li>
 * <p>Firstly, you need to change you system under test and set the SMTP host and port for it. With this setting, all email communications
 * originated by the system will go to the "fake" email server. (So those emails will be not sent to the real recipients, but you will be able to
 * check them.)</p>
 * <p>In many cases it is enough to set the <code>mail.smtp.host</code> system poperty to <code>localhost</code>, and set
 * the <code>mail.smtp.port</code> property to the port where we want to run our fake server. But the correct setup depends on
 * the tested system - contact your developers if you are unsure about it.</p>
 * </li>
 * <li>
 * Secondly, you need to add a {@link FakeEmailServer} instance to your step libraries, with the {@link Inject} annotation:
 * <pre>
 * {@literal @}Inject
 * private FakeEmailServer emailServer;
 * </pre>
 * The server is singleton, you can have only one in a test execution. It means that if you injects it into multiple step libraries, they will receive
 * the same instance.
 * </li>
 * <li>
 * Before you want to use the email server, you need to start it. Call the {@link #start(int)} method with your selected port number.
 * Typically you can do this in a {@link Before} or {@link BeforeClass} block.
 * </li>
 * <li>
 * At the end, you need to stop the server with the {@link #stop()} method. A good place for that is an {@link After} or {@link AfterClass} block.
 * </li>
 * </ol>
 * The expectations in this class are working similarly to other "ibello" expectations. They are also logged out and inserted into the result report.
 * You can put them into complex expectations as well.
 * @author Kornél Simon
 *
 */
public interface FakeEmailServer {

	/**
	 * Starts the email server on the given port.
	 * @param port the port where the server should be started
	 * @return the server instance
	 */
	public FakeEmailServer start(int port);
	
	/**
	 * Stops the email server.
	 * @return the server instance
	 */
	public FakeEmailServer stop();
	
	/**
	 * Resets the email server and clears all emails from it.
	 * Useful if we want to start with a clean state.
	 * @return the server instance
	 */
	public FakeEmailServer reset();
	
	/**
	 * Starts a hard expectation about the last email.
	 * @return the builder object to build the expectation
	 */
	public EmailExpectationBuilder expectLastEmail();
	
	/**
	 * Starts a soft expectation about the last email.
	 * @return the builder object to build the expectation
	 */
	public EmailExpectationBuilder assumeLastEmail();
	
}
