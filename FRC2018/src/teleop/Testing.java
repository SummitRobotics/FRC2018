package teleop;

import org.usfirst.frc.team5468.robot.Hardware;
import actions.MastT;
import templates.TeleopProgram;

public class Testing extends TeleopProgram {
	private MastT mastAction;
	public Testing(Hardware r) {
		super(r, "Testing");
	}

	@Override
	public void teleopInit() {
		mastAction = new MastT(robot, 5, .5);
	}

	@Override
	public void teleopPeriodic() {
		if(robot.controller.getRawButton(2)){
			mastAction.run();
		}
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