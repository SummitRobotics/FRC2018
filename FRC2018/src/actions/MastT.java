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
	private double power = .75;
	
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
			robot.mast.set(ControlMode.PercentOutput, smoothPower(robot.mast.getMotorOutputPercent()));
		}
	}
	
	//for use in teleop
	public void setPower(double x) {
		if((x > 0) && (x < robot.variables.getMinimumMastPower())){
			power = robot.variables.getMinimumMastPower();
		}
		power = x;
	}
	
	//this prevents a 0 to X acceleration jump in the first iteration of the action
	private double smoothPower(double point) {
		int n = 2;
		return ((point*n) + power)/(1+n);
	}

	@Override
	public boolean actionFinished() {
		//if the timer has elapsed or the mast disabled or a limit switch is hit
		if(clock.get() > time || !robot.mastEnabled) {
			if(!this.finished){
				robot.mast.set(ControlMode.PercentOutput, robot.variables.getMinimumMastPower());
			}
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
	
	private boolean forceStop() {
		//if any of the switches have incremented
		if(robot.lowerMastSwitch.get() || robot.higherMastSwitch.get()) {
			//if going down and top hit
			if(robot.higherMastSwitch.get() && robot.mast.getMotorOutputPercent() <= robot.variables.getMinimumMastPower()) {
				return false;
			}
			//if going up and bottom hit
			else if(robot.lowerMastSwitch.get() && robot.mast.getMotorOutputPercent() >= robot.variables.getMinimumMastPower()){
				return false;
			}
			else {
				return true;
			}
		}
		return false;
	}

}
