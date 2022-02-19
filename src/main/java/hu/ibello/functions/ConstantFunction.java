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
package hu.ibello.functions;

/**
 * A constant function.
 * <pre>
 * f(x) = a
 * </pre>
 * Parameters are:
 * <ul>
 * <li>a</li>
 * </ul>
 * @author kokog
 *
 */
public class ConstantFunction implements Function {
	
	public ConstantFunction() {
	}

	public ConstantFunction(double a) {
		this.a = a;
	}

	protected double a;
	
	public double getA() {
		return a;
	}
	
	public void setA(double a) {
		this.a = a;
	}
	
	@Override
	public double value(double x) {
		return a;
	}

	@Override
	public int getParameterCount() {
		return 1;
	}

	@Override
	public double getParameter(int paramIndex) {
		if (paramIndex == 0) {
			return a;
		}
		throw new IllegalArgumentException("Unknown parameter index: " + paramIndex);
	}

	@Override
	public void setParameter(int paramIndex, double value) {
		if (paramIndex == 0) {
			a = value;
		} else {
			throw new IllegalArgumentException("Unknown parameter index: " + paramIndex);
		}
	}
	
	@Override
	public String toString() {
		return getFormattedParameter(0);
	}

}
