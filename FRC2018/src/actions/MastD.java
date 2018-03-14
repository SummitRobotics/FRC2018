package actions;

import org.usfirst.frc.team5468.robot.Hardware;
import com.ctre.phoenix.motorcontrol.ControlMode;
import templates.Action;

public class MastD extends Action {
	//distances
	private double target;
	
	private double offset = 0;
	private double power = .5;
	private double sign = 1;
	
	//constructor
	public MastD(Hardware r, double t) {
		super(r);
		target = t;
	}

	@Override
	public void actionInit() {
		//nothing to initialize
	}
	
	@Override
	public void actionPeriodic() {
		if(robot.mastEnabled && robot.hallPresent) {
			if(!forceStop()) {
				robot.mast.set(ControlMode.PercentOutput, smoothPower(robot.mast.getMotorOutputPercent()));
			}
			else {
				robot.mast.set(ControlMode.PercentOutput, robot.variables.getMinimumMastPower());
			}
		}
	}

	@Override
	public boolean actionFinished() {		
		//if the target has been reached or some technical problems discovered
		if(Math.abs(target - robot.mastEncoder.getDistance()) < offset || !robot.mastEnabled || !robot.hallPresent || forceStop()) {
			if(this.finished == false) {
				robot.mast.set(ControlMode.PercentOutput, robot.variables.getMinimumMastPower());
			}
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public String getAction() {
		return "Mast Distance by Encoder";
	}
	
	private boolean forceStop() {
		//if any of the switches have incremented
		if(robot.higherMastSwitch.get() || robot.lowerMastSwitch.get()) {
			if(robot.higherMastSwitch.get() && (robot.mast.getMotorOutputPercent() <= robot.variables.getMinimumMastPower())) {
				return false;
			}
			else if(robot.lowerMastSwitch.get() && (robot.mast.getMotorOutputPercent() >= robot.variables.getMinimumMastPower())){
				return false;
			}
			return true;
		}
		return false;
	}
	
	private double smoothPower(double point) {
		int n = 1;
		return ((point*n) + getPower())/(1+n);
	}
	
	//for use in teleop
	public void setTarget(double x) {
		target += Math.abs(x);
		sign = Math.signum(x);
	}
	
	public double getPower() {
		if(robot.mastEncoder.getDistance() < target - offset) {
			return power * sign;
		}
		return robot.variables.getMinimumMastPower();
	}

}
