package autonomous;

import org.usfirst.frc.team5468.robot.Hardware;
import templates.AutoProgram;


public class CenterLeftSwitch extends AutoProgram{
	
	public CenterLeftSwitch(Hardware r) {
		super(r, "WLC");
	}
	
	@Override
	public void autonomousInit() {
		initCommands(2);
		
		commands[1].addAutoClamp();
		commands[1].addMastD(1);
		
		commands[0].addForwardD(20);
		commands[0].addRotationGyro(42.7);
		commands[0].addForwardD(88.5);
		commands[0].addRotationGyro(-42.7);
		commands[0].addForwardT(2, .2);
		commands[0].addEject();
	}
}
