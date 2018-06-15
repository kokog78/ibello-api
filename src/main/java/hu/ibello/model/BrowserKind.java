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

public enum BrowserKind {

	CHROME,
	FIREFOX,
	IE,
	EDGE,
	OPERA;
	
	public static BrowserKind fromString(String name) {
		return BrowserKind.valueOf(name.toUpperCase());
	}
	
	public String getFullName() {
		switch (this) {
		case CHROME:
			return "Google Chrome";
		case FIREFOX:
			return "Mozilla Firefox";
		case IE:
			return "Internet Explorer";
		case EDGE:
			return "Microsoft Edge";
		case OPERA:
			return "Opera";
		}
		return null;
	}
}
