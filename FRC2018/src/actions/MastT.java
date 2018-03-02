package actions;

import java.time.Clock;

import org.usfirst.frc.team5468.robot.Hardware;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Timer;
import templates.Action;

public class MastT extends Action {

	//time in seconds
	private double time;
	private Timer clock;
	
	//default power - for safe testing purposes
	private double power = .8;
	
	//forward for t seconds with default power
	public MastT(Hardware r, double t) {
		super(r);
		time = t;
	}
	
	public MastT(Hardware r, double t, double p) {
		super(r);
		time = t;
		power = p;
	}

	@Override
	//go forward for a finite period of time
	public void run() {	
		if(!started) {
			clock = new Timer();
			clock.start();
		}
		started = true;
		setPower(power);
		update();
	}

	@Override
	public void update() {
		if(time < clock.get()) {
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
