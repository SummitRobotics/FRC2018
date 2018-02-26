package teleop;

import org.usfirst.frc.team5468.robot.Hardware;

import actions.AutoIntake;
import actions.RotateGyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import regulation.*;
import templates.TeleopProgram;

public class Testing extends TeleopProgram {
	private AutoIntake collect;
	
	public Testing(Hardware r) {
		super(r, "Testing");
	}

	@Override
	public void teleopInit() {
		collect = new AutoIntake(robot);
	}

	@Override
	public void teleopPeriodic() {
		double p = SmartDashboard.getNumber("P", 0);
		double i = SmartDashboard.getNumber("I", 0);
		double d = SmartDashboard.getNumber("D", 0);
		
		double[] x = {p,i,d};
		//collect.updatePID(x);
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