package hu.ibello.search;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import hu.ibello.pages.PageObject;

/**
 * <p>
 * This annotation modifies the element search in a page object (see {@link PageObject}).
 * The modification means that the desired element is the descendant or the ancestor
 * of an anchor element. (This can be specified with the {@link Relation#type()}.)
 * The anchor element can be specified with the {@link Relation#by()} and {@link Relation#using()}
 * properties.
 * </p>
 * <p>
 * It is possible to add more than one Relation annotations to a field in a page object.
 * In this case, all modifiers will be applied on the field during the search.
 * </p>
 * <p>About <em>ibello</em> search engine see {@link hu.ibello.search}.</p>
 * @author Kornél Simon
 * @see Find
 * @see PageObject
 */
@Retention(RUNTIME)
@Target(FIELD)
@Repeatable(Relations.class)
public @interface Relation {

	/**
	 * Type of the relation between the desired and the anchor element.
	 * Optional, default is {@link RelationType#DESCENDANT_OF}
	 * @return relation type
	 * @see RelationType
	 */
	RelationType type() default RelationType.DESCENDANT_OF;
	
	/**
	 * Search algorithm of the anchor element.
	 * Optional, it's default value is {@link By#CSS_SELECTOR}.
	 * @return search algorithm
	 * @see By
	 */
	By by() default By.CSS_SELECTOR;
	
	/**
	 * Search pattern of the anchor element.
	 * It's value depends on the search algorithm - see {@link By}.
	 * @return search pattern
	 */
	String using();
}
