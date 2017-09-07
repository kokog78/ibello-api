package hu.ibello.search;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Internal helper annotation for {@link Relation}.
 * There is no need to use it directly.
 * <p>About <em>ibello</em> search engine see {@link hu.ibello.search}.</p>
 * @author Kornél Simon
 *
 */
@Retention(RUNTIME)
@Target(FIELD)
public @interface Relations {

	/**
	 * Array of Relation annotations.
	 * @return array of positions
	 */
	Relation[] value();
}
