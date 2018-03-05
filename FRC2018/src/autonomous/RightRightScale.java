package autonomous;

import org.usfirst.frc.team5468.robot.Hardware;
import templates.AutoProgram;

public class RightRightScale extends AutoProgram {

	public RightRightScale(Hardware r) {
		super(r, "CRR");
	}

	@Override
	public void autonomousInit() {
		initCommands(2);
		
		commands[1].addClamp();
		commands[1].addMastT(10);
		commands[0].addForwardD(333);
		commands[0].addRotationGyro(-90);
		//commands[0].addForwardT(2, .2);
		commands[0].addExtend();
		commands[0].addClamp();
	}

}
