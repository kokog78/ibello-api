/*
 * Ark-Sys Kft. licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package hu.ibello.search;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import hu.ibello.elements.WebElement;
import hu.ibello.elements.WebElements;
import hu.ibello.model.BrowserKind;
import hu.ibello.pages.PageObject;

/**
 * Basic search annotation. All fields in a page object marked with this annotation
 * will be created automatically by the injector. (See {@link PageObject}.)
 * <p>About <em>ibello</em> search engine see {@link hu.ibello.search}.</p>
 * @author Kornél Simon
 *
 */
@Retention(RUNTIME)
@Target(FIELD)
@Repeatable(Finds.class)
public @interface Find {
	
	/**
	 * The browsers where this search rule is applied.
	 * If not specified, then ibello will use the same search rule in all browsers.
	 * If multiple search rules can be selected for a browser, then the most specific one will be used.
	 * (Most specific rule: where the number of the allowed browsers is the smallest.)
	 * If ibello can't decide which search rule should be used then it will log the problem and will not initialize the webelement.
	 * @return the browser where the search should be occurred
	 */
	BrowserKind[] in() default {};

	/**
	 * Search algorithm. If not specified, search algorithm will be {@link By#CSS_SELECTOR}.
	 * @return search algorithm
	 */
	By by() default By.CSS_SELECTOR;
	
	/**
	 * Search pattern. It's meaning depends on the search algorithm - see enum constants of {@link By}.
	 * It may contain parameters which can be specified later
	 * (see {@link WebElement#applyParameters(Object...)} and {@link WebElements#applyParameters(Object...)}).
	 * @return search pattern
	 */
	String using();
}
