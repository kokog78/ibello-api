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

import java.io.File;

/**
 * A tool used to handle PDF files in ibello.
 */
public interface PdfTool {

	/**
	 * With this method one can save a HTML page to PDF file.
	 * @param url the URL of the HTML page
	 * @param file the output PDF file
	 * @throws PdfException in case of any error
	 */
	public void saveToPDF(String url, File file) throws PdfException;
}
