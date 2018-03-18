package templates;

import org.usfirst.frc.team5468.robot.Hardware;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import sequencing.Sequence;

public abstract class AutoProgram {
	protected Hardware robot;
	private String programName;
	protected Sequence commands;
	private boolean modeDisabled = false;

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
		if(robot.winchEnabled) {
			//robot.winch.set(ControlMode.PercentOutput, robot.variables.getWinchMinPower());
		}
		if(robot.pneumaticsEnabled) {
			robot.clamp.set(DoubleSolenoid.Value.kForward);
			robot.extender.set(DoubleSolenoid.Value.kReverse);
			//robot.ramp.set(false);
		}
		commands = new Sequence(robot);
	}
	
	public abstract void addActions();
	
	public final void autonomousPeriodic() {
		if(!commands.isFinished() && !modeDisabled) {
			commands.run();
		}
	}
	
	public final void autonomousDisabledInit() {
		robot.leftDrive.set(ControlMode.PercentOutput, 0);
		robot.rightDrive.set(ControlMode.PercentOutput, 0);
		commands = new Sequence(robot);
		modeDisabled = true;
	}
	
	public final String getName() {
		return programName;
	}

}
