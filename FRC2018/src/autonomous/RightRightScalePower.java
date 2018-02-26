package autonomous;

import org.usfirst.frc.team5468.robot.Hardware;
import templates.AutoProgram;

public class RightRightScalePower extends AutoProgram {

	public RightRightScalePower(Hardware r) {
		super(r, "CRRP");
	}

	@Override
	public void autonomousInit() {
		initCommands(2);
		
		commands[1].addAutoClamp();
		commands[1].addMastD(1);
		commands[0].addForwardT(7, .7);
		commands[0].addRotationGyro(90);
		//commands[0].addForwardT(2, .2);
		commands[0].addEject();
	}

}
