package autonomous;

import org.usfirst.frc.team5468.robot.Hardware;
import templates.AutoProgram;

public class Test extends AutoProgram{

	public Test(Hardware r) {
		super(r, "KEY");
	}

	@Override
	public void autonomousInit() {
		initCommands(1);
		commands[0].addForwardD(10);
	}

}
