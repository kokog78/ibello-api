package hu.ibello.search;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import hu.ibello.pages.PageObject;

/**
 * This annotation is used on {@link PageObject} implementations to specify the <code>iframe</code> parent of all elements
 * described by the page.
 * <p>
 * In this example, the whole <code>FramedPage</code> class is marked with this annotation. Every element on this page are
 * in an <code>iframe</code> which has "my-frame" as <code>id</code>. The search for the <code>button</code> field is
 * performed inside of the <code>iframe</code> element. 
 * <pre>
 * {@literal @}Frame(using="#my-frame")
 * public void FramedPage extends PageObject {
 * 
 *     {@literal @}Find(using="#button")
 *     private WebElement button;
 * 
 * }
 * </pre>
 * @author Kornél Simon
 *
 */
@Retention(RUNTIME)
@Target(ElementType.TYPE)
public @interface Frame {

	/**
	 * Search algorithm. If not specified, search algorithm will be {@link By#CSS_SELECTOR}.
	 * @return search algorithm
	 */
	By by() default By.CSS_SELECTOR;
	
	/**
	 * Search pattern. It's meaning depends on the search algorithm - see enum constants of {@link By}.
	 * @return search pattern
	 */
	String using();
}
