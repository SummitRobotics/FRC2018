package templates;

import org.usfirst.frc.team5468.robot.Hardware;

public abstract class Action{
	protected Hardware robot;
	//has this specific action been concluded?
	protected boolean finished = false;
	//has this specific action been called before?
	private boolean started = false;
	
	//constructor
	public Action(Hardware r) {
		robot = r;
	}
	
	//called by Sequence.java to run the action
	public final void run() {
		if(!started) {
			actionInit();
			started = true;
		}
		actionPeriodic();
		finished = actionFinished();
	}
	
	//runs when first called
	public abstract void actionInit();
	//runs in an interative fashion
	public abstract void actionPeriodic();
	//conclude iteration or continue
	public abstract boolean actionFinished();
	
	public final boolean finished() {
		return finished;
	}
	
	public final boolean started() {
		return started;
	}
	
	public abstract String getAction();
}
