package sequencing;

import java.util.ArrayList;
import templates.Action;

public class Subsequence {
	//compiled actions
	private ArrayList<Action> actions;
	private int[] prefix = new int[2];
	private int suffix;
	private int iteration = 0;
	
	//constructor for autonomous
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
	//note that this method is flag agnostic
	//this means that teleop can run it, but auto has to handle flags
	//this is done in Sequence.java
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
	
	//return the flags necessary for the branch to start
	public int[] requiredFlags() {
		return prefix;
	}
	
	//return the ending flag
	public int appendingFlag() {
		return suffix;
	}

}