package hu.ibello.core;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * This annotation is used to specify the browser window of a page object or step library instance.
 * <p>
 * By default, all page objects are running in the default browser window, but sometimes we want to open
 * a new window from the same java thread (session) and manage another pages in there. To do this, we need
 * to choose an identifier to that window and add this annotation (with the selected identifier) to the
 * page object field in our step library.
 * </p>
 * <pre>
 * public class MySteps extends StepLibrary {
 * 
 *     // this page object will control the default browser window
 *     private MyPage page1;
 *     
 *     // this page object will control a new browser window
 *     {@literal @}Window("new-window")
 *     private MyPage page2;
 * 
 * }
 * </pre>
 * <p>
 * It is possible to add this annotation to a step library field (in a test class). If we do this, then all
 * page object referenced by that step library will be connected to the specified browser window - except
 * if they already have a {@link Window} annotation with a different identifier.
 * </p>
 * <pre>
 * {@literal @}Specification
 * public class MyTest {
 * 
 *     // steps.page1 will control the "another-window" browser window, because it does not have an annotation
 *     // steps.page2 will control the "new-window" browser window, because of it's explicit annotation
 *     {@literal @}Window("another-window")
 *     private MySteps steps;
 * 
 * }
 * </pre>
 * <p>
 * The identifier of the controlled browser window is inherited through references. So if a page object references
 * another page objects and those objects are not annotated, then all of them will control the same window.
 * </p>
 * @author Kornél Simon
 */
@Retention(RUNTIME)
@Target(FIELD)
public @interface Window {

	String value();
}
