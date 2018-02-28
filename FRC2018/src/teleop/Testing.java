package teleop;

import org.usfirst.frc.team5468.robot.Hardware;

import com.ctre.phoenix.motorcontrol.ControlMode;

import templates.TeleopProgram;

public class Testing extends TeleopProgram {
	
	public Testing(Hardware r) {
		super(r, "Testing");
	}

	@Override
	public void teleopInit() {
	}

	@Override
	public void teleopPeriodic() {
		robot.winch.set(ControlMode.PercentOutput, deadzone(robot.controller.getRawAxis(1), .2));
	}
	
	public static double deadzone(double joystickValue, double deadzone)
	{
		//if the joystickValue falls within the range of the deadzone...
		if (Math.abs(joystickValue) < deadzone)
		{
			//Set the joystick value to 0
			joystickValue = 0;
		}
		
		return joystickValue;
	}

	@Override
	public void teleopDisabledInit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void teleopDisabledPeriodic() {
		// TODO Auto-generated method stub
		
	}
}