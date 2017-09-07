package hu.ibello.inject;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * <p>
 * Main annotation of the dependency injection system.
 * It marks an injected field in a class.
 * Example:
 * </p>
 * <pre>
 * public class MyClass {
 * 
 *    {@literal @}Inject
 *    private InjectedClass injectedField;
 * 
 * }
 * </pre>
 * <p>
 * The injector automatically discovers fields marked with {@link Inject} annotation.
 * The field can be public, protected, package private or private, it's value will be handled by the injector.
 * </p>
 * <p>
 * <i>About the <em>ibello</em> dependency injection system, see {@link hu.ibello.inject}.</i>
 * </p>
 * @author Kornél Simon
 */
@Retention(RUNTIME)
@Target(FIELD)
public @interface Inject {

}
