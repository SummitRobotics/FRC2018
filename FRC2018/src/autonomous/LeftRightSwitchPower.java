package autonomous;

import org.usfirst.frc.team5468.robot.Hardware;

import templates.AutoProgram;

public class LeftRightSwitchPower extends AutoProgram{

	public LeftRightSwitchPower(Hardware r) {
		super(r, "WRLP");
	}

	@Override
	public void autonomousInit() {
		initCommands(2);
		
		commands[1].addClamp();
		commands[1].addMastT(10);
		
		commands[0].addForwardT(2, .2);
		commands[0].addRotationGyro(-90);
		commands[0].addForwardT(10, .5);
		commands[0].addRotationGyro(-90);
		commands[0].addForwardT(3, .2);
		commands[0].addExtend();
		commands[0].addClamp();
	}

}
