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
package hu.ibello.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class ExceptionInfo {

	private String title;
	private String stackTrace;
	
	public String getTitle() {
		return title;
	}
	
	@XmlAttribute
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getStackTrace() {
		return stackTrace;
	}
	
	@XmlValue
	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}
}
