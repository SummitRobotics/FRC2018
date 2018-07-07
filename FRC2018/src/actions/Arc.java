package actions;

import org.usfirst.frc.team5468.robot.Hardware;

import com.ctre.phoenix.motorcontrol.ControlMode;

import templates.Action;

public class Arc extends Action{
	private double x;
	private double y;
	
	private double error = 500;
	
	private double leftDistance;
	private double rightDistance;
	
	public Arc(Hardware r, double X, double Y) {
		super(r);
		x = X;
		y = Y;
	}

	@Override
	public void actionInit() {
		robot.leftDrive.setSelectedSensorPosition(0, 0, 0);
		robot.rightDrive.setSelectedSensorPosition(0, 0, 0);
		if(y == 0) {
			leftDistance = y;
			rightDistance = y;
		}
		else if(y < 0) {
			leftDistance = getArcLength(x, y);
			rightDistance = getArcLength(x + robot.variables.getWidth(), y);
		}
		else {
			rightDistance = getArcLength(x, y);
			leftDistance = getArcLength(x + robot.variables.getWidth(), y);
		}
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
			robot.leftDrive.setSelectedSensorPosition(0, 0, 0);
			robot.rightDrive.setSelectedSensorPosition(0, 0, 0);
			robot.leftDrive.set(ControlMode.MotionMagic, robot.leftDrive.getSelectedSensorPosition(0));
			robot.rightDrive.set(ControlMode.MotionMagic, robot.rightDrive.getSelectedSensorPosition(0));
			robot.leftDrive.set(ControlMode.PercentOutput, 0);
			robot.rightDrive.set(ControlMode.PercentOutput, 0);
			return true;					
		}
		return false;
	}

	@Override
	public String getAction() {
		// TODO Auto-generated method stub
		return null;
	}
	
	//approximation of quarter ellipse perimeter
	private double getArcLength(double a, double b) {
		try {
			return ITE((a + b)*(Math.pow((Math.PI / 4), Math.abs(4*a*b)/(Math.pow(a + b, 2)))));
		}catch(Exception e) {
			return 0;
		}
	}
	
	//inches to encoder
	private int ITE(double distance) {
		return (int) Math.round(((distance) / (Math.PI*(robot.variables.getWheelDiam())) * 4096));
	}

}
