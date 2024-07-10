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
	PENDING,
	BLOCKED,
	SKIPPED;
	
	public boolean isFailed() {
		return this == ERROR || this == FAILURE;
	}
	
	public boolean isSuccess() {
		return this == SUCCESS;
	}
	
	public boolean isExecuted() {
		return this != PENDING && this != BLOCKED && this != SKIPPED;
	}
	
	public boolean isPending() {
		return this == PENDING;
	}
	
	public boolean isStrongerThan(Outcome outcome) {
		if (outcome==null) {
			return true;
		} else {
			int diff = getPriority() - outcome.getPriority();
			return diff < 0;
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
	
	private int getPriority() {
		switch (this) {
		case ERROR:
			return 1;
		case FAILURE:
			return 2;
		case BLOCKED:
			return 3;
		case PENDING:
			return 4;
		case SUCCESS:
			return 5;
		case SKIPPED:
			return 6;
		}
		// not possible
		return 100;
	}
}
