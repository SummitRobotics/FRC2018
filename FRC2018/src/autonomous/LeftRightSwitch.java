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
		
		commands[1].addClamp();
		commands[1].addMastT(10);
		
		commands[0].addForwardD(225);
		commands[0].addRotationGyro(90);
		commands[0].addForwardD(170);
		commands[0].addRotationGyro(90);
		commands[0].addForwardT(1, .2);
		commands[0].addExtend();
		commands[0].addClamp();
	}

}
