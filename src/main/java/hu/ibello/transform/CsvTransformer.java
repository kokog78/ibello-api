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
	 * @param csv the CSV content
	 * @param typeOfT type of the objects
	 * @return list of objects
	 * @throws TransformerException if csv is not a valid CSV file
	 */
	public <T> List<T> fromCsv(String csv, Class<T> typeOfT) throws TransformerException;
	
	/**
	 * This method reads the given CSV data and loads it into a list.
	 * The first line in the CSV should be the header.
	 * Every other lines will be considered as new object in the list, with the given type.
	 * @param csv the Reader containing CSV data
	 * @param typeOfT type of the objects
	 * @return list of objects
	 * @throws TransformerException if csv is not a valid CSV file
	 */
	public <T> List<T> fromCsv(Reader csv, Class<T> typeOfT) throws TransformerException;
}
