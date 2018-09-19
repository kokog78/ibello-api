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

import java.io.File;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlType(propOrder={"label", "url", "path", "window"})
public class Screenshot {

	private String url;
	private String path;
	private String label;
	private String window;
	private File file;
	
	public Screenshot() {
	}
	
	public Screenshot(String url, String path, String label) {
		this.url = url;
		this.path = path;
		this.label = label;
	}
	
	public String getUrl() {
		return url;
	}
	
	@XmlAttribute
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getPath() {
		return path;
	}
	
	@XmlValue
	public void setPath(String path) {
		this.path = path;
	}
	
	public String getLabel() {
		return label;
	}
	
	@XmlAttribute
	public void setLabel(String label) {
		this.label = label;
	}

	public File getFile() {
		return file;
	}
	
	@XmlTransient
	public void setFile(File file) {
		this.file = file;
	}
	
	public String getWindow() {
		return window;
	}
	
	@XmlAttribute
	public void setWindow(String window) {
		this.window = window;
	}
}
