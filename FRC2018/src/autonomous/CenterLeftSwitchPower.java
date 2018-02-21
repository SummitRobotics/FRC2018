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
		
		commands[1].addAutoClamp();
		commands[1].addMastD(1);
		
		commands[0].addForwardT(1, .2);
		commands[0].addRotationGyro(42.7);
		commands[0].addForwardT(4, .5);
		commands[0].addRotationGyro(-42.7);
		commands[0].addForwardT(2, .2);
		//commands[0].addEject(1);
	}
}
