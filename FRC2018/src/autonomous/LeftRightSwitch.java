package autonomous;

import org.usfirst.frc.team5468.robot.Hardware;

import templates.AutoProgram;

public class LeftRightSwitch extends AutoProgram{

	public LeftRightSwitch(Hardware r) {
		super(r, "WRL");
	}

	@Override
	public void addActions() {
		commands.addMastT(4, .5);
		commands.addSequence(0, 1);
		
		commands.addForwardD(240);
		commands.addRotationGyro(90);
		commands.addForwardD(190);
		commands.addRotationGyro(90);
		commands.addForwardT(4, .2);
		commands.addExtend();
		commands.addForwardT(.5, 0);
		commands.addClamp();
		commands.addSequence(0, 2);
	}

}
