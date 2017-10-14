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
 * <p>
 * Constants of this enum specifies the relation of an element and an anchor element.
 * </p>
 * <p>
 * The values are used in {@link Relation#type()} annotation property.
 * </p>
 * <p>About <em>ibello</em> search engine see {@link hu.ibello.search}.</p>
 * @author Kornél Simon
 * @see Relation
 */
public enum RelationType {

	/**
	 * The desired element is the descendant of the anchor element.
	 */
	DESCENDANT_OF,
	
	/**
	 * The desired element is the ancestor of the anchor element.
	 */
	ANCESTOR_OF;
	
}
