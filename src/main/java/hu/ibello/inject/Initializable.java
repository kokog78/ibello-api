package hu.ibello.inject;

/**
 * <p>
 * This interface marks an initializable object. The {@link Initializable#initialize()} method will be called
 * automatically when the injector cerates a new instance for dependency injection.
 * </p>
 * <p>
 * <i>About the <em>ibello</em> dependency injection system, see {@link hu.ibello.inject}.</i>
 * </p>
 * 
 * @author Kornél Simon
 *
 */
public interface Initializable {

	/**
	 * Intializes the injected instance.
	 * It is called after every fields in this instance are initialized by the injector.
	 */
	void initialize();
}
