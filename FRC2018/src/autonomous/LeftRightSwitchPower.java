package autonomous;

import org.usfirst.frc.team5468.robot.Hardware;

import templates.AutoProgram;

public class LeftRightSwitchPower extends AutoProgram{

	public LeftRightSwitchPower(Hardware r) {
		super(r, "WRLP");
	}

	@Override
	public void addActions() {
		commands.addMastT(10);
		commands.addSequence(0, 1);
		
		commands.addForwardT(2, .2);
		commands.addRotationGyro(-90);
		commands.addForwardT(10, .5);
		commands.addRotationGyro(-90);
		commands.addForwardT(3, .2);
		commands.addExtend();
		commands.addClamp();
		commands.addSequence(0, 2);
	}

}
