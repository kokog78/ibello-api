package hu.ibello.core;

import java.util.Collection;

public interface Values {

	public Value getValue(String name);
	
	public int size();
	
	public Collection<String> getNames();
	
}
