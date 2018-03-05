package autonomous;

import org.usfirst.frc.team5468.robot.Hardware;

import templates.AutoProgram;

public class LeftRightSwitch extends AutoProgram{

	public LeftRightSwitch(Hardware r) {
		super(r, "WRL");
	}

	@Override
	public void autonomousInit() {
		initCommands(2);
		//commands[1].addClamp();
		commands[1].addMastT(4, .5);
		
		commands[0].addForwardD(240);
		commands[0].addRotationGyro(90);
		commands[0].addForwardD(190);
		commands[0].addRotationGyro(90);
		commands[0].addForwardT(4, .2);
		commands[0].addExtend();
		commands[0].addForwardT(.5, 0);
		commands[0].addClamp();
	}

}
