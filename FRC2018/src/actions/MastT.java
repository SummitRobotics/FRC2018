package actions;

import java.time.Clock;

import org.usfirst.frc.team5468.robot.Hardware;
import com.ctre.phoenix.motorcontrol.ControlMode;
import templates.Action;

public class MastT extends Action {

	//time in seconds
	private Long time;
	private Clock timer;
	
	//default power - for safe testing purposes
	private double power = .8;
	
	//forward for t seconds with default power
	public MastT(Hardware r, double t) {
		super(r);
		time = (long) t*1000;
	}

	@Override
	//go forward for a finite period of time
	public void run() {	
		if(!started) {
			time += timer.millis();
		}
		started = true;
		if(!this.isInterrupted()) {
			setPower(power);
		}
		update();
	}

	@Override
	public void update() {
		if(time < timer.millis() || this.isInterrupted()) {
			setPower(0);
			finished = true;
		}
	}
	
	private void setPower(double x) {
		if(robot.mastEnabled) {
			robot.mast.set(ControlMode.PercentOutput, x);
		}
	}
	
	@Override
	public String getAction() {
		return "Time-based Mast";
	}

}
