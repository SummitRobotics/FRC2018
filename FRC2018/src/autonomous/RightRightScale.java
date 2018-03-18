package autonomous;

import org.usfirst.frc.team5468.robot.Hardware;
import templates.AutoProgram;

public class RightRightScale extends AutoProgram {

	public RightRightScale(Hardware r) {
		super(r, "CRR");
	}

	@Override
	public void addActions() {
		commands.addMastTop();
		commands.addSequence(0, 1);
		
		commands.addForwardD(333);
		commands.addRotationGyro(-90);
		commands.addSequence(0, 2);
		
		commands.addForwardD(10);
		commands.addEject();
		commands.addForwardD(-10);
		commands.addSequence(1, 2, 3);
		
		commands.addRotationGyro(-60);
		commands.addForwardD(50);
		commands.addSequence(3, 4);
		
		commands.addDelay(2);
		commands.addMastBottom();
		commands.addSequence(3, 5);
	}

}
