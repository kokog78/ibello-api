package hu.ibello.search;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
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
 * <p>
 * It is possible to add multiple <code>Frame</code> annotations to a class. This is useful when we have frames including frames
 * and we want to access the content of the inner frame. In this case we have to list all frames ending with target frame, each frame should
 * have it's own annotation. For example, if our HTML document contains a frame with "parent-frame" id, and we load a HTML page into this
 * frame which also contains a frame, with "child-frame" id, then we can access the content of the second frame with these annotations:
 * </p>
 * <pre>
 * {@literal @}Frame(using="#parent-frame")
 * {@literal @}Frame(using="#child-frame")
 * public void ChildFramedPage extends PageObject {
 *     ...
 * }
 * </pre>
 * @author Kornél Simon
 *
 */
@Retention(RUNTIME)
@Target(ElementType.TYPE)
@Repeatable(Frames.class)
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
