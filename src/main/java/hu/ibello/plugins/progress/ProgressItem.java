package hu.ibello.plugins.progress;

public interface ProgressItem {

	public ProgressItemKind getKind();
	
	public ProgressItem withKind(ProgressItemKind kind);
	
	public String getId();
	
	public String getTitle();
	
	public ProgressItem withTitle(String id);

	public <E extends Enum<?>> E getStatus();
	
	public <E extends Enum<?>> ProgressItem withStatus(E status);

	public ProgressItem addChild(String id);
	
}
