package actions;

import org.usfirst.frc.team5468.robot.Hardware;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
		if(!started) {
			init();
		}
		started = true; 
		if(robot.driveEnabled) {
			robot.leftDrive.set(ControlMode.MotionMagic, distance);
			robot.rightDrive.set(ControlMode.MotionMagic, distance);
		}
		update();
	}

	@Override
	public String getAction() {
		return "Forward by distance";
	}
	
	private void init() {
		robot.leftDrive.setSelectedSensorPosition(0, 0, 0);
		robot.rightDrive.setSelectedSensorPosition(0, 0, 0);
	}

	@Override
	public void update() {
		if(Math.abs(robot.leftDrive.getSelectedSensorPosition(0) - distance) < error || !robot.driveEnabled) {
			robot.leftDrive.set(ControlMode.MotionMagic, robot.leftDrive.getSelectedSensorPosition(0));
			robot.rightDrive.set(ControlMode.MotionMagic, robot.rightDrive.getSelectedSensorPosition(0));
			finished = true;
		}
	}
	
}