package actions;

import org.usfirst.frc.team5468.robot.Hardware;
import templates.Action;

public class Delay extends Action{
	private long time = 1000;
	
	public Delay(Hardware r) {
		super(r);
	}
	
	public Delay(Hardware r, double x) {
		super(r);
		time = (long) x * 1000;
	}

	@Override
	public void run() {
		started = true;
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finished = true;
	}

	@Override
	public void update() {
		finished = true;
	}

	@Override
	public String getAction() {
		return "Delaying";
	}
}
