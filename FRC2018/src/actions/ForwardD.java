package actions;

import org.usfirst.frc.team5468.robot.Hardware;
import com.ctre.phoenix.motorcontrol.ControlMode;
import templates.Action;

//TO-DO
//Implement ControlMode.MotionMagic
public class ForwardD extends Action {
	private int distance;
	private int error = 500;
	
	public ForwardD(Hardware r, int d) {
		super(r);
		distance = d;
	}

	@Override
	public void run() {
		started = true; 
		if(!Thread.interrupted() && robot.driveEnabled) {
			robot.leftDrive.set(ControlMode.MotionMagic, distance);
			robot.rightDrive.set(ControlMode.MotionMagic, distance);
		}
		update();
	}

	@Override
	public String getAction() {
		return "Forward by distance";
	}

	@Override
	public void update() {
		if(Math.abs(robot.leftDrive.getSelectedSensorPosition(0) - distance) < error || this.isInterrupted() || !robot.driveEnabled) {
			robot.leftDrive.set(ControlMode.MotionMagic, robot.leftDrive.getSelectedSensorPosition(0));
			robot.rightDrive.set(ControlMode.MotionMagic, robot.leftDrive.getSelectedSensorPosition(0));
		}
	}
	
}