package autonomous;

import org.usfirst.frc.team5468.robot.Hardware;
import templates.AutoProgram;

public class RightRightSwitchPower extends AutoProgram{

	public RightRightSwitchPower(Hardware r) {
		super(r,"WRRP");
	}
	
	@Override
	public void addActions() {
		commands.addMastT(10);
		commands.addSequence(0, 1);
		
		commands.addForwardT(5, .6);
		commands.addRotationGyro(90);
		commands.addForwardT(2, .2);
		commands.addExtend();	
		commands.addClamp();
		commands.addSequence(0, 2);
	}

}
