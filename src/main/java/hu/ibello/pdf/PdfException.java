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
package hu.ibello.pdf;

/**
 * An exception which can be raised when an error occurs during a PDF-related task.
 * @author Kornél Simon
 *
 */
public class PdfException extends Exception {

	private static final long serialVersionUID = -1409639500124480308L;

	public PdfException(String message, Throwable cause) {
		super(message, cause);
	}

	public PdfException(String message) {
		super(message);
	}
}
