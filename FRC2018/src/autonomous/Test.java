package autonomous;

import org.usfirst.frc.team5468.robot.Hardware;
import templates.AutoProgram;
import utilities.Variables;

public class Test extends AutoProgram{

	public Test(Hardware r) {
		super(r, "KEY");
	}
	
	@Override
	public void addActions() {
		for(int a = 0; a < 4; ++a) {
			commands.addRotationGyro(90);
			commands.addForwardD(24);
		}
		commands.addSequence(0, 2);
	}
}