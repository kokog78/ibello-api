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
package hu.ibello.transform;

/**
 * An exception which indicates transformation error.
 * 
 * @author Kornél Simon
 *
 */
public class TransformerException extends Exception {

	private static final long serialVersionUID = -1563054737233443945L;

	public TransformerException(String message, Throwable cause) {
		super(message, cause);
	}

	public TransformerException(String message) {
		super(message);
	}

	
}
