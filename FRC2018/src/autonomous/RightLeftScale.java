package autonomous;

import org.usfirst.frc.team5468.robot.Hardware;
import templates.AutoProgram;

public class RightLeftScale extends AutoProgram {

	public RightLeftScale(Hardware r) {
		super(r, "CLR");
	}

	@Override
	public void addActions() {
		commands.addMastT(10);
		commands.addSequence(0, 1);
		
		commands.addForwardD(230);
		commands.addRotationGyro(90);
		commands.addForwardD(220);
		commands.addRotationGyro(-90);
		commands.addForwardD(90);
		commands.addRotationGyro(-90);
		commands.addExtend();
		commands.addClamp();
		commands.addSequence(0, 2);
	}

}
