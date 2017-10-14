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

import hu.ibello.pages.PageObject;

/**
 * <p>
 * This annotation modifies the element search in a page object (see {@link PageObject}).
 * The modification means that the desired element should have a specific relative position
 * from an anchor element. The position can be specified with the {@link Position#type()} property.
 * The anchor element can be specified with the {@link Position#by()} and {@link Position#using()}
 * properties.
 * </p>
 * <p>
 * It is possible to add more than one Position annotations to a field in a page object.
 * In this case, all modifiers will be applied on the field during the search.
 * </p>
 * <p>About <em>ibello</em> search engine see {@link hu.ibello.search}.</p>
 * @author Kornél Simon
 * @see Find
 * @see PageObject
 */
@Retention(RUNTIME)
@Target(FIELD)
@Repeatable(Positions.class)
public @interface Position {

	/**
	 * Relative position of the desired element.
	 * Optional, it's default value is {@link PositionType#ROW}.
	 * @return relative position
	 * @see PositionType
	 */
	PositionType type() default PositionType.ROW;
	
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
