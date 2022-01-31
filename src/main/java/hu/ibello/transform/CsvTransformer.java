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
package hu.ibello.transform;

import java.io.Reader;
import java.util.List;

/**
 * Utility class which transforms CSV into a list of java objects.
 * 
 * @author Kornél Simon
 *
 */
public interface CsvTransformer {

	/**
	 * This method reads the given CSV data and loads it into a list.
	 * The first line in the CSV should be the header.
	 * Every other lines will be considered as new object in the list, with the given type.
	 * @param <T> type of the result objects
	 * @param csv the CSV content
	 * @param typeOfT type of the result objects
	 * @return list of objects
	 * @throws TransformerException if csv is not a valid CSV file
	 */
	public <T> List<T> fromCsv(String csv, Class<T> typeOfT) throws TransformerException;
	
	/**
	 * This method reads the given CSV data and loads it into a list.
	 * The first line in the CSV should be the header.
	 * Every other lines will be considered as new object in the list, with the given type.
	 * @param <T> type of the result objects
	 * @param csv the Reader containing CSV data
	 * @param typeOfT type of the objects
	 * @return list of objects
	 * @throws TransformerException if csv is not a valid CSV file
	 */
	public <T> List<T> fromCsv(Reader csv, Class<T> typeOfT) throws TransformerException;
}
