/**
 * The <em>ibello</em> step library API.
 * <p>
 * In <em>ibello</em>, business-level test steps are specified in <em>step libraries</em>.
 * The step library is a class which extends {@link hu.ibello.steps.StepLibrary}.
 * </p>
 * <p>
 * All step libraries are discovered and instantiated automatically by the injector.
 * It is enough to add a step library field to a test class, it will be automatically
 * instantiated and initialized before any test method. The field can be private.
 * </p>
 * @author Kornél Simon
 */
package hu.ibello.steps;