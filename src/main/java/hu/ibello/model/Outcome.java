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

public enum Outcome {

	SUCCESS,
	FAILURE,
	ERROR,
	PENDING;
	
	public boolean isFailed() {
		return this==ERROR || this==FAILURE;
	}
	
	public boolean isStrongerThan(Outcome outcome) {
		if (outcome==null) {
			return true;
		} else {
			switch (outcome) {
			case PENDING:
				return this!=PENDING;
			case SUCCESS:
				return this.isFailed();
			case FAILURE:
				return this==Outcome.ERROR;
			case ERROR:
				return false;
			default:
				return false;
			}
		}
	}
	
	public static Outcome getStronger(Outcome outcome1, Outcome outcome2) {
		if (outcome1 == null) {
			return outcome2;
		} else if (outcome1.isStrongerThan(outcome2)) {
			return outcome1;
		} else {
			return outcome2;
		}
	}
}
