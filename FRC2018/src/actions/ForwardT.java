package actions;

import org.usfirst.frc.team5468.robot.Hardware;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.Timer;
import templates.Action;

public class ForwardT extends Action{
	//forward by time
	private double time;
	private double power = .5;
	
	//timer
	private Timer clock;
	
	//constructor
	public ForwardT(Hardware r, double t) {
		super(r);
		time = t;
	}
	
	public ForwardT(Hardware r, double t, double p) {
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
		//if the drive train is functioning
		if(robot.driveEnabled) {
			//then set power in a smooth fashion
			robot.leftDrive.set(ControlMode.PercentOutput, smoothPower(robot.leftDrive.getMotorOutputPercent()));
			robot.rightDrive.set(ControlMode.PercentOutput, smoothPower(robot.rightDrive.getMotorOutputPercent()));
		}
	}
	
	//this prevents a 0 to X acceleration jump in the first iteration of the action
	private double smoothPower(double point) {
		int n = 2;
		return ((point*n) + power)/(1+n);
	}
	
	@Override
	public boolean actionFinished() {
		//if the timer is done, or drive train breaks
		if(clock.get() > time || !robot.driveEnabled) {
			//then stop iterating
			clock.stop();
			disablePower();
			return true;
		}
		else {
			return false;
		}
	}

	//disable motor output
	private void disablePower() {
		if(robot.driveEnabled) {
			robot.leftDrive.set(ControlMode.PercentOutput, 0);
			robot.rightDrive.set(ControlMode.PercentOutput, 0);
		}
	}

	@Override
	public String getAction() {
		return "Forward by time";
	}
	
}