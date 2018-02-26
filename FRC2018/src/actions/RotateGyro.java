package actions;

import org.usfirst.frc.team5468.robot.Hardware;

import com.ctre.phoenix.motorcontrol.ControlMode;

import templates.Action;

public class RotateGyro extends Action{
	//degrees
	private double angle = 0;
	private double initialDiff = 0;
	//native units
	private double error = 3;
	//percent
	private double minPower = .2;
	private double maxPower = .6;
	private double currentPower;
	
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
			initialDiff = angle;
			angle += robot.getRelativeYaw();
		}
		started = true;
		if(robot.gyroEnabled) {
			if(clockwise()) {
				robot.leftDrive.set(ControlMode.PercentOutput, -currentPower);
				robot.rightDrive.set(ControlMode.PercentOutput, currentPower);
			}
			else {
				robot.leftDrive.set(ControlMode.PercentOutput, currentPower);
				robot.rightDrive.set(ControlMode.PercentOutput, -currentPower);
			}
		}
		update();
	}

	
	private boolean clockwise() {
		double diff = angle - robot.getRelativeYaw();
		currentPower = Math.max(minPower, (Math.abs(diff)/Math.abs(initialDiff))*maxPower);
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
		if((Math.abs(robot.getRelativeYaw() - angle) < error) || !robot.gyroEnabled) {
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
