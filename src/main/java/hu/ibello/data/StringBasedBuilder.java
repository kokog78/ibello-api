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
package hu.ibello.data;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public interface StringBasedBuilder extends StreamBasedBuilder {

	/**
	 * Loads the test data from the target file.
	 * For this, the UTF-8 character set is used.
	 * If the test data cannot be found then returns <code>null</code>.
	 * @return the loaded test data as {@link String}
	 */
	public String loadString();
	
	/**
	 * Opens a stream with the UTF-8 content of the test data.
	 * If the test data cannot be found then returns <code>null</code>.
	 * @return an opened stream or <code>null</code>
	 */
	public default InputStream openStream() throws IOException {
		String content = loadString();
		if (content != null) {
			return new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
		} else {
			return null;
		}
	}
}