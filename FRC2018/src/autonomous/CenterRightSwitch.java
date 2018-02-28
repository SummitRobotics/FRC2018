package autonomous;

import org.usfirst.frc.team5468.robot.Hardware;
import templates.AutoProgram;

public class CenterRightSwitch extends AutoProgram{

	public CenterRightSwitch(Hardware r) {
		super(r, "WRC");
	}
	
	@Override
	public void autonomousInit() {
		initCommands(2);
		
		commands[1].addClamp();
		commands[1].addMastT(10);
		
		commands[0].addForwardD(20);
		commands[0].addRotationGyro(-42.7);
		commands[0].addForwardD(88.5);
		commands[0].addRotationGyro(42.7);
		commands[0].addForwardT(2, .2);
		commands[0].addExtend();
		commands[0].addClamp();
	}
}
