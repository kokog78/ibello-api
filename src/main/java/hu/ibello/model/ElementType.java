package hu.ibello.model;

public enum ElementType {

	TESTRUN,
	SPECIFICATION,
	TEST,
	STEP,
	ACTION,
	EXPECTATION;
	
	public boolean isLeaf() {
		switch (this) {
		case ACTION:
		case EXPECTATION:
			return true;
		default:
			return false;
		}
	}
	
}
