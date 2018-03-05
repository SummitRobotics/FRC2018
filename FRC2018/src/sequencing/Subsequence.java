package sequencing;

import java.util.ArrayList;
import templates.Action;

public class Subsequence {
	//compiled actions
	private ArrayList<Action> actions;
	private Flag[] flags = new Flag[2];
	
	//constructor
	public Subsequence(ArrayList<Action> a, int[] prefix, int suffix) {
		actions = a;
		//flag(s) required to run
		flags[0] = new Flag(prefix);
		//flag thrown at the end of sequence
		flags[1] = new Flag(suffix);
	}
	
	public boolean updateFlags(ArrayList<Flag> thrownFlags) {
		//add the thrown indexes into an arraylist
		ArrayList<Integer> thrownIndices = new ArrayList<Integer>();
		for(int a = 0; a < thrownFlags.size(); ++a) {
			for(int b = 0; b < thrownFlags.get(a).getFlag().length; ++b) {
				thrownIndices.add(thrownFlags.get(a).getFlag()[b]);
			}
		}
		
		//add the required indexes into an arraylist
		ArrayList<Integer> targetIndices = new ArrayList<Integer>();
		for(int a = 0; a < flags[0].getFlag().length; ++a) {
			targetIndices.add(flags[0].getFlag()[a]);
		}
		
		//check whether all required flags are present
		for(int a = 0; a < targetIndices.size(); ++a) {
			//if a required number is not found
			if(!isNumberPresent(targetIndices.get(a), thrownIndices)) {
				//do not run the subsequence
				return false;
			}
		}
		return true;
	}
	
	//find whether number is present within array
	private boolean isNumberPresent(int x, ArrayList<Integer> num) {
		for(int a = 0; a < num.size(); ++a) {
			if(x == num.get(a)) {
				return true;
			}
		}
		return false;
	}
	
	//iterate through subsequence
	public void run() {
		//if the required flags are thrown
		if(flags[0].isThrown()) {
			//then iterate and run actions linearly
			for(int a = 0; a < actions.size(); ++a) {
				if(!actions.get(a).finished()) {
					actions.get(a).run();
					return;
				}
			}
		}
	}
	
	//find whether the subsequence has finished
	public boolean isFinished() {
		for(int a = 0; a < actions.size(); ++a) {
			if(!actions.get(a).finished()) {
				return false;
			}
		}
		return true;
	}
	
	//return the ending flag
	public Flag appendingFlag() {
		flags[1].throwFlag();
		return flags[1];
	}

}
