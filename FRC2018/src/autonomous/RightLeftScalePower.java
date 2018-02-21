package autonomous;

import org.usfirst.frc.team5468.robot.Hardware;
import templates.AutoProgram;

public class RightLeftScalePower extends AutoProgram {

	public RightLeftScalePower(Hardware r) {
		super(r, "CLRP");
	}

	@Override
	public void autonomousInit() {
		initCommands(2);
		
		commands[1].addAutoClamp();
		commands[1].addMastD(1);
		
		commands[0].addForwardT(6, .7);
		commands[0].addRotationGyro(90);
		commands[0].addForwardT(4, .7);
		commands[0].addRotationGyro(-90);
		commands[0].addForwardT(2, .7);
		commands[0].addRotationGyro(-90);
		commands[0].addForwardT(2, .2);
		//commands[0].addEject(1);
	}

}
