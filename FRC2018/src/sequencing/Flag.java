package sequencing;

//**************//
//
//	A variable type making subsequence handling
//	significantly less overbearing and arduous to read
//
//**************//
public class Flag {
	//the index is the name of the flag
	private final int index;
	//the state of the flag
	private boolean thrown = false;
	
	//constructor
	//custom thrown value is for master flag creation only
	//all other flags should be thrown as default: false
	public Flag(int i, boolean t) {
		index = i;
		thrown = t;
	}
	
	//get name of flag
	public int getIndex() {
		return index;
	}
	
	//find whether the flag has been thrown
	public boolean isThrown() {
		return thrown;
	}
	
	//throw the flag
	public void throwFlag() {
		thrown = true;
	}
}
