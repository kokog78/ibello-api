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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 
 * @see TestDataBuilder#fromBinary(String)
 * @author Kornél Simon
 *
 */
public interface BinaryTestDataBuilder extends StreamBasedBuilder {

	public File getFile();
	
	public default InputStream openStream() throws IOException {
		File file = getFile();
		if (file != null) {
			return new FileInputStream(file);
		} else {
			return null;
		}
	}
}
