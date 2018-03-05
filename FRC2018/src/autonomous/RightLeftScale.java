package autonomous;

import org.usfirst.frc.team5468.robot.Hardware;
import templates.AutoProgram;

public class RightLeftScale extends AutoProgram {

	public RightLeftScale(Hardware r) {
		super(r, "CLR");
	}

	@Override
	public void autonomousInit() {
		initCommands(2);
		
		commands[1].addClamp();
		commands[1].addMastT(10);
		
		commands[0].addForwardD(230);
		commands[0].addRotationGyro(90);
		commands[0].addForwardD(220);
		commands[0].addRotationGyro(-90);
		commands[0].addForwardD(90);
		commands[0].addRotationGyro(-90);
		//commands[0].addForwardT(2, .2);
		commands[0].addExtend();
		commands[0].addClamp();
	}

}
