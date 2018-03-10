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
	private double minPower = .3;
	private double maxPower = .5;
	private double currentPower;
	
	//constructor
	public RotateGyro(Hardware r, double theta) {
		super(r);
		angle = theta;
	}
	
	//constructor 2
	public RotateGyro(Hardware r, double theta, double power) {
		super(r);
		angle = theta;
		maxPower = power;
	}

	@Override
	public void actionInit() {
		//rotate in a relative manner
		initialDiff = angle;
		angle += robot.getRelativeYaw();
	}

	@Override
	public void actionPeriodic() {
		//if the gyro is functional
		if(robot.gyroEnabled) {
			//then rotate clocwise or counter clockwise
			if(clockwise()) {
				//set power clockwise
				robot.leftDrive.set(ControlMode.PercentOutput, -currentPower);
				robot.rightDrive.set(ControlMode.PercentOutput, currentPower);
			}
			else {
				//set power counter clockwise
				robot.leftDrive.set(ControlMode.PercentOutput, currentPower);
				robot.rightDrive.set(ControlMode.PercentOutput, -currentPower);
			}
		}
	}
	
	//decide the direction in which to rotate
	private boolean clockwise() {
		double diff = angle - robot.getRelativeYaw();
		//rotate in a smooth manner
		currentPower = Math.max(minPower, (Math.abs(diff)/Math.abs(initialDiff))*maxPower);
		if(diff > 0){
			return false;
		}
		else {
			return true;
		}
	}

	@Override
	public boolean actionFinished() {
		//if the robot has rotated close enough or the gyro disconnected
		if((Math.abs(robot.getRelativeYaw() - angle) < error) || !robot.gyroEnabled) {
			//then finish the rotation
			robot.leftDrive.set(ControlMode.PercentOutput, 0);
			robot.rightDrive.set(ControlMode.PercentOutput, 0);
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public String getAction() {
		return "Gyro Rotation";
	}
}
