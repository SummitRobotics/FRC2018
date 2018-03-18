
package autonomous;

import org.usfirst.frc.team5468.robot.Hardware;
import templates.AutoProgram;

public class Test extends AutoProgram{

	public Test(Hardware r) {
		super(r, "KEY");
	}
	
	
	@Override
	public void addActions() {
		commands.addForwardD(100);
		commands.addForwardD(-100);
		commands.addForwardD(100);
		commands.addForwardD(-100);
		commands.addSequence(0, 1);
	}
}