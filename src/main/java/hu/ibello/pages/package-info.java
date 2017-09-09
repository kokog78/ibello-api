/**
 * The <em>ibello</em> page object API.
 * <p>
 * In <em>ibello</em>, technical functions of a page are specified in a <em>page object</em>.
 * The page object is a class which extends {@link hu.ibello.pages.PageObject}.
 * </p>
 * <p>
 * All page objects are discovered and instantiated automatically by the injector.
 * It is enough to add a page object field to a step library class, it will be automatically
 * instantiated and initialized before any test method. The field can be private.
 * </p>
 * @author Kornél Simon
 */
package hu.ibello.pages;