package autonomous;

import org.usfirst.frc.team5468.robot.Hardware;
import templates.AutoProgram;

public class AutoForward extends AutoProgram{

	public AutoForward(Hardware r) {
		super(r, "FOR");
	}
	
	@Override
	public void addActions() {
		commands.addForwardD(144);
		commands.addSequence(0, 1);
	}

}
