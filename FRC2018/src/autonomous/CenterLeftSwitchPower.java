package autonomous;

import org.usfirst.frc.team5468.robot.Hardware;
import templates.AutoProgram;

public class CenterLeftSwitchPower extends AutoProgram{
	
	public CenterLeftSwitchPower(Hardware r) {
		super(r, "WLCP");
	}
	
	@Override
	public void autonomousInit() {
		initCommands(2);
		
		commands[1].addClamp();
		commands[1].addMastT(10);
		
		commands[0].addForwardT(.8, .4);
		commands[0].addRotationGyro(-42.7);
		commands[0].addForwardT(1, .5);
		commands[0].addRotationGyro(42.7);
		commands[0].addForwardT(1, .3);
		commands[0].addExtend();
		commands[0].addClamp();
	}
}
