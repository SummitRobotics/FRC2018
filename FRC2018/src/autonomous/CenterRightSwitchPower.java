package autonomous;

import org.usfirst.frc.team5468.robot.Hardware;
import templates.AutoProgram;

public class CenterRightSwitchPower extends AutoProgram{

	public CenterRightSwitchPower(Hardware r) {
		super(r, "WRCP");
	}

	@Override
	public void addActions() {
		commands.addMastSwitch();
		commands.addSequence(0, 1);
		
		commands.addForwardT(.8, .4);
		commands.addRotationGyro(42.7);
		commands.addForwardT(1, .5);
		commands.addRotationGyro(-42.7);
		commands.addForwardT(1, .3);
		commands.addSequence(0, 2);
		
		commands.addEject();
		commands.addSequence(1, 2, 3);
	}
}