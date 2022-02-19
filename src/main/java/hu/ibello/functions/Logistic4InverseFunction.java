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
 * Inverse of the 4-parameter logistic function.
 * 
 * @author kokog
 *
 */
public class Logistic4InverseFunction extends Logistic4Function {

	@Override
	public double value(double x) {
		double ratio = (y1 - y0) / (x - y0);
		return c * Math.pow(ratio - 1, 1/b);
	}

	@Override
	public String toString() {
		String _y0 = getFormattedParameter(0);
		String _y1 = getFormattedParameter(1);
		String _b = getFormattedParameter(2);
		String _c = getFormattedParameter(3);
		return String.format("%s * ((%s - %s) / (x - %s) - 1) ^(1/%s)", _c, _y1, _y0, _y0, _b);
	}
}
