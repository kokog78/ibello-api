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
package hu.ibello.bdd;

/**
 * This exception class represents an error which was occured during a BDD-related operation.
 * @author Kornél Simon
 */
public class BDDException extends Exception {

	private static final long serialVersionUID = 93634714927473015L;

	public BDDException(String message, Throwable cause) {
		super(message, cause);
	}

	public BDDException(String message) {
		super(message);
	}

	
}
