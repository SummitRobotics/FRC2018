package actions;

import org.usfirst.frc.team5468.robot.Hardware;

import com.ctre.phoenix.motorcontrol.ControlMode;

import templates.Action;

public class RotateEncoder extends Action{
	private double theta;
	
	private final double error = 500;
	
	private double leftDistance;
	private double rightDistance;
	
	public RotateEncoder(Hardware r, double t) {
		super(r);
		theta = t;
	}

	@Override
	public void actionInit() {
		//zero encoders
		robot.leftDrive.setSelectedSensorPosition(0, 0, 0);
		robot.rightDrive.setSelectedSensorPosition(0, 0, 0);
		
		if(theta > 0) {
			leftDistance = ITE(-(theta / 360)*(Math.PI)*(robot.variables.getWidth()));
			rightDistance = ITE((theta / 360)*(Math.PI)*(robot.variables.getWidth()));
		}
		else {
			leftDistance = ITE((theta / 360)*(Math.PI)*(robot.variables.getWidth()));
			rightDistance = ITE((theta / 360)*(Math.PI)*(robot.variables.getWidth()));
		}
	}
	//inches to encoder
	private int ITE(double distance) {
		return (int) Math.round(((distance) / (Math.PI*(robot.variables.getWheelDiam())) * 4096));
	}

	@Override
	public void actionPeriodic() {
		if(robot.driveEnabled) {
			robot.leftDrive.set(ControlMode.MotionMagic, leftDistance);
			robot.rightDrive.set(ControlMode.MotionMagic, rightDistance);
		}
	}

	@Override
	public boolean actionFinished() {
		if(Math.abs(robot.leftDrive.getSelectedSensorPosition(0) - leftDistance) < error &&
		   Math.abs(robot.rightDrive.getSelectedSensorPosition(0) - rightDistance) < error){
			robot.leftDrive.set(ControlMode.MotionMagic, robot.leftDrive.getSelectedSensorPosition(0));
			robot.rightDrive.set(ControlMode.MotionMagic, robot.rightDrive.getSelectedSensorPosition(0));
			return true;					
		}
		return false;
	}

	@Override
	public String getAction() {
		return "Rotation by Encoder";
	}

}
