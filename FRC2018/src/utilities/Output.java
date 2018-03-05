package utilities;
import org.usfirst.frc.team5468.robot.Hardware;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Output {
	private Hardware robot;
	
	public Output(Hardware r) {
		robot = r;
	}
	
	public void update() {
		printComponentStatus();
	}
	
	private void printComponentStatus() {
		SmartDashboard.putBoolean("Drive Status", robot.driveEnabled);
		SmartDashboard.putBoolean("Intake Status", robot.intakeEnabled);
		SmartDashboard.putBoolean("Pneumatics Status", robot.pneumaticsEnabled);
		SmartDashboard.putBoolean("Mast Motor Status", robot.mastEnabled);
		SmartDashboard.putBoolean("Hall Effect Status", robot.hallPresent);
		SmartDashboard.putBoolean("Controller Status", robot.controllerPresent);
		SmartDashboard.putBoolean("Gyro Status", robot.gyroEnabled);
	}
}
