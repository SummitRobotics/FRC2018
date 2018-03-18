package autonomous;

import org.usfirst.frc.team5468.robot.Hardware;
import templates.AutoProgram;

public class CenterRightSwitch extends AutoProgram{

	public CenterRightSwitch(Hardware r) {
		super(r, "WRC");
	}

	@Override
	public void addActions() {
		commands.addMastSwitch();
		commands.addSequence(0, 1);
		
		commands.addForwardD(20);
		commands.addRotationGyro(42.7);
		commands.addForwardD(60);
		commands.addRotationGyro(-42.7);
		commands.addForwardT(1.25, .3);
		commands.addSequence(0, 2);
		
		commands.addEject();
		commands.addSequence(1, 2, 3);
	}
}

