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
 * A 5-parameter logistic function (5PL or asymmetrical sigmodial).
 * <pre>
 * f(x) = y0 + (y1 - y0) / (1 + (x / c)^b)^m
 * </pre>
 * Parameters are:
 * <ul>
 * <li>y0</li>
 * <li>y1</li>
 * <li>b</li>
 * <li>c</li>
 * <li>m</li>
 * </ul>
 * @author kokog
 *
 */
public class Logistic5Function implements Function {

	protected double y0;
	protected double y1;
	protected double b;
	protected double c;
	protected double m;
	
	public double getY0() {
		return y0;
	}
	
	public void setY0(double d) {
		this.y0 = d;
	}
	
	public double getY1() {
		return y1;
	}
	
	public void setY1(double a) {
		this.y1 = a;
	}
	
	public double getB() {
		return b;
	}
	
	public void setB(double b) {
		this.b = b;
	}
	
	public double getC() {
		return c;
	}
	
	public void setC(double c) {
		this.c = c;
	}
	
	public double getM() {
		return m;
	}
	
	public void setM(double m) {
		this.m = m;
	}
	
	@Override
	public int getParameterCount() {
		return 5;
	}
	
	@Override
	public double value(double x) {
		return y0 + (y1-y0) / Math.pow(1 + Math.pow(x / c, b), m);
	}

	@Override
	public double getParameter(int paramIndex) {
		switch (paramIndex) {
		case 0:
			return y0;
		case 1:
			return y1;
		case 2:
			return b;
		case 3:
			return c;
		case 4:
			return m;
		}
		throw new IllegalArgumentException("Unknown parameter index: " + paramIndex);
	}

	@Override
	public void setParameter(int paramIndex, double value) {
		switch (paramIndex) {
		case 0:
			y0 = value;
			break;
		case 1:
			y1 = value;
			break;
		case 2:
			b = value;
			break;
		case 3:
			c = value;
			break;
		case 4:
			m = value;
			break;
		default:
			throw new IllegalArgumentException("Unknown parameter index: " + paramIndex);
		}
	}
	
	@Override
	public String toString() {
		String _y0 = getFormattedParameter(0);
		String _y1 = getFormattedParameter(1);
		String _b = getFormattedParameter(2);
		String _c = getFormattedParameter(3);
		String _m = getFormattedParameter(4);
		return String.format("%s + (%s - %s) / ((1 + (x / %s) ^%s) ^%s)",
				_y0, _y1, _y0, _c, _b, _m);
	}
}
