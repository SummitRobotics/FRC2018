package templates;

import org.usfirst.frc.team5468.robot.Hardware;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.Timer;
import utilities.Sequence;

public abstract class AutoProgram {
	public Hardware robot;
	public String programName;
	protected Sequence[] commands;
	protected Timer timer;
	
	private boolean timerStarted = false;
	
	public AutoProgram(Hardware r, String name)
	{
		robot = r;
		programName = name;
	}
	public abstract void autonomousInit();
	
	public void autonomousPeriodic() {
		startTimer();
		for(int a = 0; a < commands.length; ++a) {
			if(timer.get() > 15) {
				throwExceptions();
			}
			if(!commands[a].done()) {
				commands[a].run();
			}
		}
	}
	
	public void autonomousDisabledInit() {
		robot.leftDrive.setSelectedSensorPosition(0, 0, 0);
		robot.rightDrive.setSelectedSensorPosition(0, 0, 0);
		robot.leftDrive.set(ControlMode.MotionMagic, 0);
		robot.rightDrive.set(ControlMode.MotionMagic, 0);
	}
	
	public String getName() {
		return programName;
	}
	
	public String getKey() {
		return programName;
	}
	
	protected void initCommands(int x) {
		commands = new Sequence[x];
		for(int a = 0; a < x; ++a) {
			commands[a] = new Sequence(robot);
		}
	}
	
	
	private void startTimer() {
		if(!timerStarted) {
			timer.start();
		}
		timerStarted = true;
	}
	private void throwExceptions() {
		for(int a = 0; a < commands.length; ++a) {
			commands[a].interuptActions();
		}
	}
}
