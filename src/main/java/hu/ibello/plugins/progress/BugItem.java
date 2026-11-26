package hu.ibello.plugins.progress;

public interface BugItem {

	public String getId();
	
	public String getTitle();
	
	public BugItem withTitle(String title);

	public <E extends Enum<?>> E getStatus();
	
	public <E extends Enum<?>> BugItem withStatus(E status);
	
	public <E extends Enum<?>> E getPriority();
	
	public <E extends Enum<?>> BugItem withPriority(E priority);
	
	public <E extends Enum<?>> E getSeverity();
	
	public <E extends Enum<?>> BugItem withSeverity(E severity);
	
	public BugItem withExtraValue(String value);
	
	public BugItem withExtraValue(int value);
	
	public BugItem withExtraValue(double value);
	
	public BugItem withExtraValue(boolean value);
	
	public <E extends Enum<?>> BugItem withExtraValue(E value);
}
