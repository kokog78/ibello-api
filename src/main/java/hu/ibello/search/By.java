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

/**
 * This enum symbolizes an element search algorithm.
 * During element search on web-pages, a search algorithm and a search pattern is used
 * to find the desired elements. The meaning of the pattern is specified by the search algorithm
 * (an enum constant).
 * <p>About <em>ibello</em> search engine see {@link hu.ibello.search}.</p>
 * @author Kornél Simon
 *
 */
public enum By {

	/**
	 * Search by CSS selector. The search pattern can be any valid CSS selector.
	 */
	CSS_SELECTOR,
	
	/**
	 * Search by ID. The search pattern is an ID of an element.
	 */
	ID,
	
	/**
	 * Search by name. The search pattern if the value of the <code>name</code> attribute
	 * of the desired element(s).
	 */
	NAME,
	
	/**
	 * Search by tag name. The search pattern is the tag name of the desired element(s).
	 */
	TAG_NAME,
	
	/**
	 * Search by CSS class name. The search pattern is a CSS cass name which is assigned to
	 * the desired element(s).
	 */
	CLASS_NAME,
	
	/**
	 * Search by text of a button. The search pattern is the case-sensitive text of the desired element.
	 * The element itself can be:
	 * <ul>
	 * <li>a <code>button</code> with the given text,</li>
	 * <li>an <code>input</code> field for which the <code>value</code> attribute is the given text,
	 * and the <code>type</code> attribute is <code>button</code>, <code>submit</code> or <code>reset</code>,</li>
	 * <li>an <code>input</code> field for which the <code>alt</code> attribute is the given text,
	 * and the <code>type</code> attribute is <code>image</code></li>
	 * <li>an <code>a</code> (link) element with the given text,</li>
	 * <li>an element with <code>role=button</code> attribute.</li>
	 * </ul>
	 * During the search, the first two categories are preferred. It means that if the search results a positive
	 * outcome for the first category, then the other categories are not searched.
	 */
	BUTTON_TEXT,
	
	/**
	 * Search by the text of an element. The search pattern is the case-sensitive content of the desired element.
	 */
	TEXT,
	
	/**
	 * Search by label. The search pattern is the case-sensitive content of a <code>label</code> element.
	 * The <code>label</code> should belong to the desired element. It means that:
	 * <ul>
	 * <li>
	 * The label has a <code>for</code> attribute, and the value of this attribute matches the
	 * <code>id</code> attribute of the desired element.
	 * </li>
	 * <li>
	 * The label does not have <code>for</code> attribute, but there is a single <code>input</code>,
	 * <code>textarea</code> or <code>select</code> element inside of it's parent or it's grandparent -
	 * that single element will be the desired one.
	 * </li>
	 * </ul>
	 */
	LABEL,
	
	/**
	 * Search by partial text of a button. The search pattern is part of the desired element's text, case-sensitively.
	 * The element itself can be:
	 * <ul>
	 * <li>a <code>button</code> with the given text,</li>
	 * <li>an <code>input</code> field for which the <code>value</code> attribute is the given text,
	 * and the <code>type</code> attribute is <code>button</code>, <code>submit</code> or <code>reset</code>,</li>
	 * <li>an <code>input</code> field for which the <code>alt</code> attribute is the given text,
	 * and the <code>type</code> attribute is <code>image</code></li>
	 * <li>an <code>a</code> (link) element with the given text,</li>
	 * <li>an element with <code>role=button</code> attribute.</li>
	 * </ul>
	 * During the search, the first two categories are preferred. It means that if the search results a positive
	 * outcome for the first category, then the other categories are not searched.
	 */
	PARTIAL_BUTTON_TEXT,
	
	/**
	 * Search by the partial text of an element. The search pattern is part of the desired element's text, case-sensitively.
	 */
	PARTIAL_TEXT,
	
	/**
	 * Search by partial label. The search pattern is the part of a <code>label</code> element's content.
	 * The <code>label</code> should belong to the desired element. It means that:
	 * <ul>
	 * <li>
	 * The label has a <code>for</code> attribute, and the value of this attribute matches the
	 * <code>id</code> attribute of the desired element.
	 * </li>
	 * <li>
	 * The label does not have <code>for</code> attribute, but there is a single <code>input</code>,
	 * <code>textarea</code> or <code>select</code> element inside of it's parent or it's grandparent -
	 * that single element will be the desired one.
	 * </li>
	 * </ul>
	 */
	PARTIAL_LABEL,
	
	/**
	 * Search by the CSS class of a button. The search pattern is a CSS class name.
	 * The class may belong to the button itself or to one of it's descendants.
	 * The element itself can be:
	 * <ul>
	 * <li>a <code>button</code>,</li>
	 * <li>an <code>input</code> field for which the <code>type</code> attribute is <code>button</code>,
	 * <code>submit</code>, <code>reset</code> or <code>image</code>,</li>
	 * <li>an <code>a</code> (link) element,</li>
	 * <li>an element with <code>role=button</code> attribute.</li>
	 * </ul>
	 */
	BUTTON_CLASS;
}
