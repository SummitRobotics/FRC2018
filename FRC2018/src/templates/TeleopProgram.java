package templates;

import org.usfirst.frc.team5468.robot.Hardware;

public abstract class TeleopProgram {
	public Hardware robot;
	public String programName;
	
	public TeleopProgram(Hardware r,String name)
	{
		robot = r;
		programName = name;
	}
	
	public abstract void teleopInit();
	public abstract void teleopPeriodic();
	public abstract void teleopDisabledInit();
	public abstract void teleopDisabledPeriodic();
	public String getName() {
		return programName;
	}
}

