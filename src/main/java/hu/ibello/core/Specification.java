package hu.ibello.core;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * This annotation marks test classes (<em>specifications</em>).
 * Only classes marked with this annotation will be executed as tests.
 * @author Kornél Simon
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface Specification {

}
