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

import java.io.File;
import java.io.OutputStream;
import java.io.Reader;
import java.util.List;

/**
 * Utility class which transforms CSV into a list of java objects and back.
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
	 * @throws TransformerException if csv is not a valid CSV data
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
	 * @throws TransformerException if csv is not a valid CSV data
	 */
	public <T> List<T> fromCsv(Reader csv, Class<T> typeOfT) throws TransformerException;
	
	/**
	 * This method reads the given CSV data from a file and loads it into a list.
	 * The first line in the CSV should be the header.
	 * Every other lines will be considered as new object in the list, with the given type.
	 * @param <T> type of the result objects
	 * @param file the file which contains the CSV data
	 * @param typeOfT type of the objects
	 * @return list of objects
	 * @throws TransformerException if csv is not a valid CSV file
	 */
	public <T> List<T> fromCsv(File file, Class<T> typeOfT) throws TransformerException;
	
	/**
	 * Transforms the given object list into a CSV data and returns it.
	 * The header will be automatically extracted from the given class.
	 * Each fields of this class and it's superclasses will be considered as CSV columns, except:
	 * <ul>
	 * <li>transient fields,</li>
	 * <li>fields from {@link Object} class.</li>
	 * </ul>
	 * The cell separator will be the <code>,</code> (comma) character.
	 * @param <T> type of the objects
	 * @param rows the list of objects
	 * @param typeOfT the class which will be used to construct the header
	 * @return the CSV data
	 * @throws TransformerException if one of the fields cannot be transformed to CSV value
	 */
	public <T> String toCsv(List<? extends T> rows, Class<T> typeOfT) throws TransformerException;
	
	/**
	 * Transforms the given object list into a CSV data and write it into the given stream.
	 * The header will be automatically extracted from the given class.
	 * Each fields of this class and it's superclasses will be considered as CSV columns, except:
	 * <ul>
	 * <li>transient fields,</li>
	 * <li>fields from {@link Object} class.</li>
	 * </ul>
	 * The cell separator will be the <code>,</code> (comma) character.
	 * The charset of the data will be UTF-8.
	 * @param <T> type of the objects
	 * @param stream the stream which will receive the CSV data
	 * @param rows the list of objects
	 * @param typeOfT the class which will be used to construct the header
	 * @throws TransformerException if one of the fields cannot be transformed to CSV value of there was an error writing into the stream
	 */
	public <T> void toCsv(OutputStream stream, List<? extends T> rows, Class<T> typeOfT) throws TransformerException;
	
	/**
	 * Transforms the given object list into a CSV data and write it into the given file.
	 * The header will be automatically extracted from the given class.
	 * Each fields of this class and it's superclasses will be considered as CSV columns, except:
	 * <ul>
	 * <li>transient fields,</li>
	 * <li>fields from {@link Object} class.</li>
	 * </ul>
	 * The cell separator will be the <code>,</code> (comma) character.
	 * The charset of the data will be UTF-8.
	 * @param <T> type of the objects
	 * @param file the CSV file
	 * @param rows the list of objects
	 * @param typeOfT the class which will be used to construct the header
	 * @throws TransformerException if one of the fields cannot be transformed to CSV value of there was an error writing into the file
	 */
	public <T> void toCsv(File file, List<? extends T> rows, Class<T> typeOfT) throws TransformerException;
}
