package autonomous;

import org.usfirst.frc.team5468.robot.Hardware;
import templates.AutoProgram;

public class AutoForward extends AutoProgram{

	public AutoForward(Hardware r) {
		super(r, "STRAIGHT!");
	}

	@Override
	public void autonomousInit() {
		initCommands(1);
		commands[0].addForwardD(144);
	}

}
