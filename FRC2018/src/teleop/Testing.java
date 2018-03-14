package teleop;

import org.usfirst.frc.team5468.robot.Hardware;
import actions.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import templates.TeleopProgram;

public class Testing extends TeleopProgram {
	private MastT mastAction;
	private MastD mastAuto;
	private int n = 0;
	
	public Testing(Hardware r) {
		super(r, "Testing");
	}

	@Override
	public void teleopInit() {
		mastAuto = new MastD(robot, 0);
		mastAction = new MastT(robot, 0);
	}

	@Override
	public void teleopPeriodic() {
		if(robot.controller.getRawButton(2)) {
			SmartDashboard.putNumber("MastAuto:", robot.mastEncoder.get());
			if(Math.abs(robot.controller.getRawAxis(1)) > .3){	
				n += -robot.controller.getRawAxis(1);
				if(n < robot.variables.getMastRange()[0]) {
					n  = 0;
				}
				mastAuto.setTarget(n);
				mastAuto.run();
			}
		}
		else {
			if(Math.abs(robot.controller.getRawAxis(5)) > .3) {
				mastAction.setPower(-robot.controller.getRawAxis(5));
				mastAction.run();
			}
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