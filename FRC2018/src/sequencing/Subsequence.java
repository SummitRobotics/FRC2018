package sequencing;

import java.util.ArrayList;
import templates.Action;

public class Subsequence {
	//compiled actions
	private ArrayList<Action> actions;
	private int[] prefix = new int[2];
	private int suffix;
	private int iteration = 0;
	
	//constructor
	public Subsequence(ArrayList<Action> a, int[] p, int s) {
		actions = a;
		prefix = p;
		suffix = s;
	}
	
	//constructor for teleop
	public Subsequence(ArrayList<Action> a) {
		actions = a;
	}
	
	//iterate through subsequence
	public void run() {
		if(!isFinished()) {
			if(!actions.get(iteration).finished()) {
				actions.get(iteration).run();
			}
			else {
				++iteration;
			}
		}
	}
	
	//find whether the subsequence has finished
	public boolean isFinished() {
		if(iteration < actions.size()) {
			return false;
		}
		return true;
	}
	
	//force finish
	public void forceStop() {
		iteration = actions.size();
	}
	
	public int[] requiredFlags() {
		return prefix;
	}
	
	//return the ending flag
	public int appendingFlag() {
		return suffix;
	}

}