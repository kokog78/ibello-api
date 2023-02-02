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

import java.util.List;

public interface Regression<F extends Function> {

	/**
	 * The function of the regression.
	 * @return the function
	 */
	public F getFunction();
	
	/**
	 * Data points of the regression.
	 * @return data points
	 */
	public List<DataPoint> getData();
	
	/**
	 * Executes the regression for the function with the given data points.
	 * After it, the function will hold the parameter values which are the closest to the data points.
	 */
	public void run();
	
	/**
	 * Returns the error square of the latest run.
	 * It is the sum of the squares of the differences between the data point Y values and the function values.
	 * @return the error square
	 */
	public double getErrorSquare();
	
}
