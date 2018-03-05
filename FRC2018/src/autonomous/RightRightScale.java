package autonomous;

import org.usfirst.frc.team5468.robot.Hardware;
import templates.AutoProgram;

public class RightRightScale extends AutoProgram {

	public RightRightScale(Hardware r) {
		super(r, "CRR");
	}

	@Override
	public void addActions() {
		commands.addMastT(10);
		commands.addSequence(0, 1);
		
		commands.addForwardD(333);
		commands.addRotationGyro(-90);
		commands.addExtend();
		commands.addClamp();
		commands.addSequence(0, 2);
	}

}
