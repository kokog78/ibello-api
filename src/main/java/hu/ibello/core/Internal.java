package hu.ibello.core;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * <p>
 * If a test method is annotated with this annotation, then it will be counted as "internal" test step.
 * It means that ibello will not list it as an available step, but in the logs and reports it will be
 * displayed as a real step.
 * </p>
 * 
 * @author Kornél Simon
 */
@Retention(RUNTIME)
@Target({METHOD, TYPE})
public @interface Internal {

}
