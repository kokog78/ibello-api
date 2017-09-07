package hu.ibello.core;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * <p>
 * With this annotation we can assign visible name to a class (step library) or a method
 * (test step). If the annotation presents on a class or method, then <em>ibello</em>
 * will log the name specified by the annotation when the class/method name is going to be
 * logged.
 * </p>
 * <p>
 * The value may contain parameter substitution markers. A parameter substitution marker
 * is a 0-based index surrounded by <code>${</code> and <code>}</code>. These markers will
 * be replaced by string version of method parameters. For example, if the annotation's value
 * is <code>"${0}-${1}"</code>, then the method will replace <code>${0}</code> with the first
 * method parameter, and <code>${1}</code> with the second parameter. If method parameters are
 * <code>"a"</code> and <code>1</code>, then the result will be <code>"a-1"</code>.
 * </p>
 * @author Kornél Simon
 */
@Retention(RUNTIME)
@Target({METHOD, TYPE})
public @interface Name {

	/**
	 * Visible name of a class (representing a step library) or a method (representing a test step).
	 * @return visible name of class or method
	 */
	String value();
}
