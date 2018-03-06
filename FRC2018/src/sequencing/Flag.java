package sequencing;

public class Flag {
	//indexes and state of flag
	private final int index;
	private boolean thrown = false;
	
	public Flag(int i, boolean t) {
		index = i;
		thrown = t;
	}
	
	public int getIndex() {
		return index;
	}
	
	public boolean isThrown() {
		return thrown;
	}
	
	public void throwFlag() {
		thrown = true;
	}
}
