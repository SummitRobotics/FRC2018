package actions;

import org.usfirst.frc.team5468.robot.Hardware;

import com.ctre.phoenix.motorcontrol.ControlMode;

import templates.Action;

public class RotateGyro extends Action{
	//degrees
	private double angle = 0;
	//native units
	private double error = 5;
	//percent
	private double maxPower = .3;
	
	//constructor
	public RotateGyro(Hardware r, double theta) {
		super(r);
		angle = theta;
	}
	
	//constructor
	public RotateGyro(Hardware r, double theta, double power) {
		super(r);
		angle = theta;
		maxPower = power;
	}

	@Override
	public void run() {
		if(!started) {
			angle += robot.getAbsoluteYaw();
		}
		started = true;
		if(!this.isInterrupted() && robot.gyroEnabled) {
			if(clockwise()) {
				robot.leftDrive.set(ControlMode.PercentOutput, -maxPower);
				robot.rightDrive.set(ControlMode.PercentOutput, maxPower);
			}
			else {
				robot.leftDrive.set(ControlMode.PercentOutput, maxPower);
				robot.rightDrive.set(ControlMode.PercentOutput, -maxPower);
			}
		}
		update();
	}

	
	private boolean clockwise() {
		double diff = angle - robot.getAbsoluteYaw();
		if(diff > 0){
			return false;
		}
		else if(diff < 0) {
			return true;
		}
		else {
			finished = true;
			return true;
		}
	}
	
	
	@Override
	public void update() {
		if((Math.abs(((robot.getAbsoluteYaw()%360) - (angle%360))) < error) || this.isInterrupted() || !robot.gyroEnabled) {
			robot.leftDrive.set(ControlMode.PercentOutput, 0);
			robot.rightDrive.set(ControlMode.PercentOutput, 0);
			finished = true;
		}
	}

	@Override
	public String getAction() {
		return "Gyro Rotation";
	}

}
