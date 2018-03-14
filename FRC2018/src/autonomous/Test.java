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
		commands.addMastD(10);
		commands.addMastD(-10);
		commands.addSequence(0, 0, 1);
	}
}