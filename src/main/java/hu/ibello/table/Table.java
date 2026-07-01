package hu.ibello.table;

/**
 * Represents a table with rows and columns.
 * Ibello can print the table on the screen or can save into file.
 * 
 * @author Kornél Simon
 */
public interface Table {

	/**
	 * Name of the table.
	 * @param name table name
	 */
	public void setName(String name);
	
	/**
	 * Name of the table.
	 * @return table name
	 */
	public String getName();
	
	/**
	 * The file name for the table.
	 * It is used when ibello saves the table into a file.
	 * It is calculated from the name of the table.
	 * @return file name for the table
	 */
	public String getFileName();
	
	/**
	 * The header data of the table
	 * @return table header
	 */
	public TableRow getHeader();
	
	/**
	 * Adds a new row to the end of the table.
	 * Returns an object which can be used to add cells to this row.
	 * @return the new row object
	 */
	public TableRow addRow();
}
