package templates;

import org.usfirst.frc.team5468.robot.Hardware;
import com.ctre.phoenix.motorcontrol.ControlMode;
import utilities.Sequence;

public abstract class AutoProgram {
	protected Hardware robot;
	private String programName;
	protected Sequence[] commands;
	
	public AutoProgram(Hardware r, String name)
	{
		robot = r;
		programName = name;
	}
	
	public abstract void autonomousInit();
	
	public void autonomousPeriodic() {
		for(int a = 0; a < commands.length; ++a) {
			if(!commands[a].done()) {
				commands[a].run();
			}
		}
	}
	
	public void autonomousDisabledInit() {
		robot.leftDrive.set(ControlMode.PercentOutput, 0);
		robot.rightDrive.set(ControlMode.PercentOutput, 0);
	}
	
	public String getName() {
		return programName;
	}
	
	protected void initCommands(int x) {
		commands = new Sequence[x];
		for(int a = 0; a < x; ++a) {
			commands[a] = new Sequence(robot);
		}
	}

}
