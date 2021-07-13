package hu.ibello.table;

public interface Table {

	public void setName(String name);
	
	public TableRow getHeader();
	
	public TableRow addRow();
}
