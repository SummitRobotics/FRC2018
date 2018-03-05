package actions;

import org.usfirst.frc.team5468.robot.Hardware;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Timer;
import templates.Action;

public class MastT extends Action {
	//duration
	private double time;
	//timer
	private Timer clock;
	//default power
	private double power = .5;
	
	//constructor
	public MastT(Hardware r, double t) {
		super(r);
		time = t;
	}
	
	//constructor 2
	public MastT(Hardware r, double t, double p) {
		super(r);
		time = t;
		power = p;
	}
	
	@Override
	public void actionInit() {
		//start timer
		clock = new Timer();
		clock.start();
	}

	@Override
	public void actionPeriodic() {
		//if the mast is enabled
		if(robot.mastEnabled){
			//then set power smoothly
			robot.mast.set(ControlMode.PercentOutput, smoothPower(robot.mast.getMotorOutputPercent()));
		}
	}
	
	//this prevents a 0 to X acceleration jump in the first iteration of the action
	private double smoothPower(double point) {
		int n = 2;
		return ((point*n) + power)/(1+n);
	}

	@Override
	public boolean actionFinished() {
		//if the timer has elapsed or the mast disabled
		if(clock.get() > time || !robot.mastEnabled) {
			//then finish the action
			//set the mast to remain in position
			robot.mast.set(ControlMode.PercentOutput, 0.1);
			clock.stop();
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
		public String getAction() {
			return "Time-based Mast";
	}
}
