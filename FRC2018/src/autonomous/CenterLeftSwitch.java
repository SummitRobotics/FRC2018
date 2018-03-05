package autonomous;

import org.usfirst.frc.team5468.robot.Hardware;
import templates.AutoProgram;

public class CenterLeftSwitch extends AutoProgram{
	
	public CenterLeftSwitch(Hardware r) {
		super(r, "WLC");
	}
	
	@Override
	public void addActions() {
		commands.addMastT(4, .5);
		commands.addSequence(0, 1);
		
		commands.addForwardD(10);
		commands.addRotationGyro(-42.7);
		commands.addForwardD(100);
		commands.addRotationGyro(42.7);
		commands.addForwardT(1.25, .3);
		commands.addExtend();
		commands.addForwardT(.75, 0);
		commands.addClamp();
		commands.addForwardT(.75, 0);
		commands.addSequence(0, 2);
	}
}
