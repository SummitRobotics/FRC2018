package utilities;

import org.usfirst.frc.team5468.robot.Hardware;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Output {
	private Hardware robot;
	//private Leds neoPixels;
	
	public Output(Hardware r) {
		robot = r;
		//neoPixels = new Leds();
	}
	
	public void update() {
		printComponentStatus();
		//neoPixels.input((int) SmartDashboard.getNumber("Led Mode", 0));
	}
	
	private void printComponentStatus() {
		SmartDashboard.putBoolean("Drive Status", robot.driveEnabled);
		SmartDashboard.putBoolean("Intake Status", robot.intakeEnabled);
		SmartDashboard.putBoolean("Pneumatics Status", robot.pneumaticsEnabled);
		SmartDashboard.putBoolean("Mast Motor Status", robot.mastEnabled);
		SmartDashboard.putBoolean("Hall Effect Status", robot.hallPresent);
		SmartDashboard.putBoolean("Controller Status", robot.controllerPresent);
		SmartDashboard.putBoolean("Gyro Status", robot.gyroEnabled);
		SmartDashboard.putBoolean("Limit Switch:", robot.limitSwitchPresent);
		SmartDashboard.putBoolean("Ramp Status", robot.winchEnabled);
		//SmartDashboard.putBoolean("LEDs Connected:", neoPixels.ledsEnabled);
	}
}
