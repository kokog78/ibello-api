package hu.ibello.inject;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * <p>
 * Marks an injectable class and specifies the <em>scope</em> of the injection.
 * Example:
 * </p>
 * <pre>
 * {@literal @}Injectable(Scope.TEST)
 * public class InjectedClass {
 * }
 * </pre>
 * The value is one of the enum constants from {@link Scope} type.
 * <p>
 * <i>About the <em>ibello</em> dependency injection system, see {@link hu.ibello.inject}.</i>
 * </p>
 * @author Kornél Simon
 */
@Retention(RUNTIME)
@Target(TYPE)
@Inherited
public @interface Injectable {

	Scope value() default Scope.SESSION;
	
}
