package hu.ibello.plugins.progress;

public interface ProgressItem {

	public ProgressItemKind getKind();
	
	public ProgressItem withKind(ProgressItemKind kind);
	
	public String getId();
	
	public String getTitle();
	
	public ProgressItem withTitle(String title);

	public <E extends Enum<?>> E getStatus();
	
	public <E extends Enum<?>> ProgressItem withStatus(E status);
	
	public ProgressItem withExtraValue(String name, String value);
	
	public ProgressItem withExtraValue(String name, int value);
	
	public ProgressItem withExtraValue(String name, double value);
	
	public ProgressItem withExtraValue(String name, boolean value);
	
	public <E extends Enum<?>> ProgressItem withExtraValue(String name, E value);

	public ProgressItem withMarkdownText(String markdownText);
	
	public ProgressItem addChild(String id);
	
}
