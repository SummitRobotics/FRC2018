package actions;

import org.usfirst.frc.team5468.robot.Hardware;
import edu.wpi.first.wpilibj.Timer;
import templates.Action;

public class Delay extends Action{
	//duration in seconds
	private double time;
	//timer
	private Timer clock;
	
	//constructor
	public Delay(Hardware r) {
		super(r);
		time = 1;
	}
	
	//constructor 2
	public Delay(Hardware r, double t) {
		super(r);
		time = t;
	}

	@Override
	public void actionInit() {
		//start timer
		clock = new Timer();
		clock.start();
	}

	@Override
	public void actionPeriodic() {
		//Do nothing.
	}

	@Override
	public boolean actionFinished() {
		//if duration is completed
		if(clock.get() > time) {
			//then cease delaying
			clock.stop();
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public String getAction() {
		return "Delaying";
	}
}
