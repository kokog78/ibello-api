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
package hu.ibello.bdd.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract superclass of a Cucumber document.
 * Descendants are {@link Feature} and {@link Examples}.
 * @author Kornél Simon
 */
public abstract class BDDDocument {

	private String fullPath;
	
	private String relativePath;
	
	private List<String> comments;
	
	private String language;
	
	private String keyword;
	
	private String name;
	
	private String description;
	
	/**
	 * Getter for the full path of the file containing the document.
	 * The file separators of the path are the operating system default separators.
	 * @return full path of the file containing the document
	 */
	public String getFullPath() {
		return fullPath;
	}

	/**
	 * Setter for the full path of the file containing the document.
	 * The file separators of the path should be the operating system default separators.
	 * @param fullPath full path of the file containing the document
	 */
	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}

	/**
	 * Getter for the relative path of the file containing the document.
	 * The base directory depends on the concrete implementation.
	 * For feature file, it is the "features" directory of the project.
	 * For example file, it is the "examples" directory of the project.
	 * The file separators of the path are the operating system default separators.
	 * @return relative path of the file containing the document
	 */
	public String getRelativePath() {
		return relativePath;
	}

	/**
	 * Setter for the relative path of the file containing the document.
	 * The base directory depends on the concrete implementation.
	 * For feature file, it is the "features" directory of the project.
	 * For example file, it is the "examples" directory of the project.
	 * The file separators of the path should be the operating system default separators.
	 * @param relativePath relative path of the file containing the document
	 */
	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}

	/**
	 * Are there any comments in the document?
	 * @return <code>true</code> if document has comments
	 */
	public boolean hasComment() {
		return comments != null && !comments.isEmpty();
	}
	
	/**
	 * Returns the comments of the document.
	 * If the document does not have comments, it returns an empty list.
	 * @return the comments of the document
	 */
	public List<String> getComments() {
		if (comments == null) {
			comments = new ArrayList<>();
		}
		return comments;
	}

	/**
	 * Returns the language code of the document.
	 * It is 2 characters long.
	 * @return the language code of the document
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * Sets the language code of the document.
	 * It is 2 characters long.
	 * @param language the language code of the document
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * Returns the root keyword of the document.
	 * It depends on the language of the document.
	 * For english language, it is "Feature".
	 * @return the root keyword of the documents
	 */
	public String getKeyword() {
		return keyword;
	}

	/**
	 * Sets the root keyword of the document.
	 * It depends on the language of the document.
	 * For english language, it should be "Feature".
	 * @param keyword the root keyword of the documents
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	/**
	 * Returns the name of the document.
	 * @return the name of the document
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the document.
	 * @param name the name of the document
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the description of the document.
	 * @return the description of the document
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the document.
	 * @param description the description of the document
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return String.format("%s: %s", keyword, name);
	}

}
