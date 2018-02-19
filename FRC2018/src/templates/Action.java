package templates;

import org.usfirst.frc.team5468.robot.Hardware;

public abstract class Action extends Thread{
	protected Hardware robot;
	protected boolean finished = false;
	protected boolean started = true;
	
	public Action(Hardware r) {
		robot = r;
		this.setDaemon(false);
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
}
