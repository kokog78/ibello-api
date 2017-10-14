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
 * Constants of this enum specifies the relative position of an element from an anchor element.
 * </p>
 * <p>
 * The values are used in {@link Position#type()} annotation property.
 * </p>
 * <p>About <em>ibello</em> search engine see {@link hu.ibello.search}.</p>
 * @author Kornél Simon
 * @see Position
 */
public enum PositionType {

	/**
	 * The desired element is in the same row as the anchor element.
	 * It means that the horizontal center line of the anchor should fit into the boundaries of the desired
	 * element.
	 */
	ROW,
	
	/**
	 * The desired element is in the same column as the anchor element.
	 * It means that the vertical center line of the anchor should fit into the boundaries of the desired
	 * element.
	 */
	COLUMN,
	
	/**
	 * The desired element is left from the anchor element.
	 * It means that the x coordinate of the desired element's right edge if less than or equal to the x coordinate
	 * of the anchor element's left edge.
	 */
	LEFT_FROM,
	
	/**
	 * The desired element is right from the anchor element.
	 * It means that the x coordinate of the desired element's left edge if greater than or equal to the x
	 * coordinate of the anchor element's right edge.
	 */
	RIGHT_FROM,
	
	/**
	 * The desired element is above the anchor element.
	 * It means that the bottom y coordinate of the desired element is less than or equal to the top y coordinate
	 * of the anchor element.
	 */
	ABOVE,
	
	/**
	 * The desired element is below the anchor element.
	 * It means that the top y coordinate of the desired element is greater than or equal to the bottom y coordinate
	 * of the anchor element.
	 */
	BELOW;
	
}
