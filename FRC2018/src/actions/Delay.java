package actions;

import org.usfirst.frc.team5468.robot.Hardware;
import edu.wpi.first.wpilibj.Timer;
import templates.Action;

public class Delay extends Action{
	private double time;
	private Timer clock;
	
	public Delay(Hardware r) {
		super(r);
	}
	
	public Delay(Hardware r, double t) {
		super(r);
		time = t;
	}

	@Override
	public void run() {
		if(!started) {
			clock.start();
		}
		started = true;
		update();
	}

	@Override
	public void update() {
		if(clock.get() > time) {
			finished = true;
			clock.stop();
		}
	}

	@Override
	public String getAction() {
		return "Delaying";
	}
}
