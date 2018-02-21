package templates;

import org.usfirst.frc.team5468.robot.Hardware;

public abstract class Action{
	protected Hardware robot;
	protected boolean finished = false;
	protected boolean started = false;
	
	public Action(Hardware r) {
		robot = r;
	}
	
	public abstract void run();
	public abstract String getAction();
	public abstract void update();
	
	public boolean finished() {
		return finished;
	}
	
	public boolean started() {
		return started;
	}
	
	public void restart() {
		started = false;
	}

}
