package autonomous;

import org.usfirst.frc.team5468.robot.Hardware;
import templates.AutoProgram;
import utilities.Variables;

public class Test extends AutoProgram{

	public Test(Hardware r) {
		super(r, "KEY");
	}

	@Override
	public void autonomousInit() {
		initCommands(2);
		//insert here
	}
}

/*
commands[0].addClamp();
commands[0].addMastT(10);
commands[0].addExtend();
commands[0].addClamp();
*/

/*
commands[1].addClamp();
commands[1].addMastT(10);

commands[0].addForwardD(333);
commands[0].addRotationGyro(90);
commands[0].addExtend();
commands[0].addClamp();
commands[0].addExtend();

commands[0].addRotationGyro(45);
commands[0].addAutoCube();
commands[0].addClamp();
commands[0].addMastT(3);
commands[0].addExtend();
commands[0].addClamp();
*/