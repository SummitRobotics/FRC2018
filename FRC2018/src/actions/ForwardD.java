package actions;

import org.usfirst.frc.team5468.robot.Hardware;
import com.ctre.phoenix.motorcontrol.ControlMode;
import templates.Action;

public class ForwardD extends Action {
	//input distance
	private int distance;
	//this is an error of about 1.5 inches
	private int error = 500;
	
	//constructor
	public ForwardD(Hardware r, int d) {
		super(r);
		distance = d;
	}

	@Override
	public void actionInit() {
		//zero encoders
		robot.leftDrive.setSelectedSensorPosition(0, 0, 0);
		robot.rightDrive.setSelectedSensorPosition(0, 0, 0);
	}

	@Override
	public void actionPeriodic() {
		//if the drivetrain is functional
		if(robot.driveEnabled) {
			//then update the TalonSRX PID Subsystem
			robot.leftDrive.set(ControlMode.MotionMagic, distance);
			robot.rightDrive.set(ControlMode.MotionMagic, distance);
		}
	}

	@Override
	public boolean actionFinished() {
		//if we have reached the target or something breaks
		if(Math.abs(robot.leftDrive.getSelectedSensorPosition(0) - distance) < error || !robot.driveEnabled) {
			robot.leftDrive.set(ControlMode.MotionMagic, robot.leftDrive.getSelectedSensorPosition(0));
			robot.rightDrive.set(ControlMode.MotionMagic, robot.rightDrive.getSelectedSensorPosition(0));
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public String getAction() {
		return "Forward by distance";
	}
}