package sequencing;

public class Flag {
	//indexes and state of flag
	private final int[] index;
	private boolean thrown = false;
	
	//constructor
	public Flag(int i) {
		index = new int[1];
		index[0] = i;
	}
	
	//constructor 2
	public Flag(int[] i) {
		index = i;
	}
	
	//constructor 4
	public Flag(int i, boolean state) {
		index = new int[1];
		index[0] = i;
		thrown = state;
	}
	
	//return index
	public int[] getFlag() {
		return index;
	}
	
	//set Flag to active
	public void throwFlag() {
		thrown = true;
	}

	//find flag state
	public boolean isThrown() {
		return thrown;
	}
}
