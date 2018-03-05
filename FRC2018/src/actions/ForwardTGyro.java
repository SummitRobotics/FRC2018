package actions;

import org.usfirst.frc.team5468.robot.Hardware;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Timer;
import templates.Action;

public class ForwardTGyro extends Action {
	
	//turning mechanics
	private double angle;
	private double angleSensitivity = .1;	
	
	//for power guessing mechanics
	//what programmer could guess forward by power wrong?
	private double time;
	private double lateralPower;
	
	//the timer
	private Timer clock;
	
	//constructor
	public ForwardTGyro(Hardware r, double t, double p) {
		super(r);
		time = t;
		lateralPower = p;
		angle = robot.getRelativeYaw();
	}

	@Override
	public void actionInit() {
		//start timer
		clock = new Timer();
		clock.start();
	}

	@Override
	public void actionPeriodic() {
		//if the drive and gyro are both connected
		if(robot.driveEnabled && robot.gyroEnabled) {
			//then set power compensating for initial angle variation
			robot.leftDrive.set(ControlMode.PercentOutput, capPower(lateralPower + getCorrection()));
			robot.rightDrive.set(ControlMode.PercentOutput, capPower(lateralPower - getCorrection()));
		}
	}
	
	//get the difference from the first angle
	private double getCorrection() {
		double diff = angle - robot.getAbsoluteYaw();
		if(diff > 0) {
			return Math.min(1, diff*angleSensitivity);
		}
		else {
			return Math.max(-1, diff*angleSensitivity);
		}
	}
	
	//prevent power from being greater than <-1,1>
	private double capPower(double x) {
		if(x < -1) {
			return -1;
		}
		else if(x > 1) {
			return 1;
		}
		else {
			return x;
		}
	}

	@Override
	public boolean actionFinished() {
		//if the timer is finished or either component is disabled
		if(clock.get() > time || !robot.driveEnabled || !robot.gyroEnabled)  {
			//then stop the timer and drive
			clock.stop();
			disablePower();
			return true;
		}
		else {
			return false;
		}
	}
	
	//turn off the drive train
	private void disablePower() {
		if(robot.driveEnabled) {
			robot.leftDrive.set(ControlMode.PercentOutput, 0);
			robot.rightDrive.set(ControlMode.PercentOutput, 0);
		}
	}
	
	@Override
	public String getAction() {
		return "Forward by Time and Gyro";
	}
}
