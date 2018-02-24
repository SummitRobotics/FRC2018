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
		initCommands(1);
		int n = 1;
		for(int a = 0; a < 4; ++a) {
			commands[0].addForwardTGyro(.3, 5);
		}
	}
}
