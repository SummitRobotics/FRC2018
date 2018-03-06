package templates;

import org.usfirst.frc.team5468.robot.Hardware;
import com.ctre.phoenix.motorcontrol.ControlMode;
import sequencing.Sequence;

public abstract class AutoProgram {
	protected Hardware robot;
	private String programName;
	protected Sequence commands;

	public AutoProgram(Hardware r, String name)
	{
		robot = r;
		programName = name;
	}
	
	public final void autonomousInit() {
		setupComponents();
		addActions();
	}
	
	private final void setupComponents() {
		//Initialize necessary components like pneumatics
	}
	
	public abstract void addActions();
	
	public final void autonomousPeriodic() {
		if(!commands.isFinished()) {
			try {
				commands.run();
			} catch (InterruptedException e) {
				autonomousDisabledInit();
			}
		}
	}
	
	public final void autonomousDisabledInit() {
		robot.leftDrive.set(ControlMode.PercentOutput, 0);
		robot.rightDrive.set(ControlMode.PercentOutput, 0);
	}
	
	public final String getName() {
		return programName;
	}

}
