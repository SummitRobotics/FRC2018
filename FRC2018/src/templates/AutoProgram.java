package templates;

import org.usfirst.frc.team5468.robot.Hardware;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import sequencing.Sequence;

public abstract class AutoProgram {
	protected Hardware robot;
	
	private String programName;
	private String input;
	
	protected Sequence commands;
	private boolean modeDisabled = false;
	
	protected boolean positionRelevant;
	protected boolean switchRelevant;
	protected boolean scaleRelevant;

	public AutoProgram(Hardware r, String name){
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
			robot.clamp.set(DoubleSolenoid.Value.kReverse);
			robot.clamp.set(DoubleSolenoid.Value.kReverse);
			robot.extender.set(DoubleSolenoid.Value.kReverse);
			robot.extender.set(DoubleSolenoid.Value.kReverse);
			//robot.ramp.set(false);
		}
		commands = new Sequence(robot);
	}
	
	private final void addActions() {
		if(input.length() == 0) {
			LLL();
		}
		else if(input.length() == 1) {
			switch(input) {
			case "R": RRR();
				break;
			case "L": LLL();
				break;
			}
		}
		else if(input.length() == 2) {
			switch(input) {
			case "RL": RLL(); 
				break;
			case "RR": RRR();
				break;
			case "LR": LRR(); 
				break;
			case "LL": LLL();
				break;
			}
		}
		else {
			switch(input) {
			case "RLL": RLL();
				break;
			case "RLR": RLR();
				break;
			case "RRL": RRL();
				break;
			case "RRR": RRR();
				break;
			case "LLL": LLL();
				break;
			case "LRL": LRL();
				break;
			case "LLR": LLR();
				break;
			case "LRR": LRR();
				break;
			}
		}
		
	}
	
	public final void sendFMS(String data) {
		if(!scaleRelevant) {
			data = subChar(data, 2);
		}
		if(!switchRelevant) {
			data = subChar(data, 1);
		}
		if(!positionRelevant) {
			data = subChar(data, 0);
		}
		input = data;
	}
	
	protected abstract void RLL();
	protected abstract void RLR();
	protected abstract void RRL();
	protected abstract void RRR();
	protected abstract void LRR();
	protected abstract void LLR();
	protected abstract void LRL();
	protected abstract void LLL();
	
	public final void autonomousPeriodic() {
		if(!commands.isFinished() && !modeDisabled) {
			commands.run();
		}
	}
	
	public final void autonomousDisabledInit() {
		robot.leftDrive.set(ControlMode.PercentOutput, 0);
		robot.rightDrive.set(ControlMode.PercentOutput, 0);
		robot.intake.set(ControlMode.PercentOutput, 0);
		commands = new Sequence(robot);
		modeDisabled = true;
	}
	
	private String subChar(String text, int index) {
		return text.substring(0, index) + text.substring(index+1, text.length());
	}
	
	public final String getName() {
		return programName;
	}

}
