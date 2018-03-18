package autonomous;

import org.usfirst.frc.team5468.robot.Hardware;
import templates.AutoProgram;

public class RightLeftScaleLeftSwitch extends AutoProgram{

	public RightLeftScaleLeftSwitch(Hardware r) {
		super(r, "RLL");
	}

	@Override
	public void addActions() {
		commands.addForwardD(240);
		commands.addRotationGyro(-90);
		commands.addForwardD(232);
		commands.addRotationGyro(90);
		commands.addForwardD(20);
		commands.addSequence(0, 1);
		
		commands.addMastTop();
		commands.addSequence(0, 2);
		
		commands.addEject();
		commands.addSequence(1, 2, 3);
		
		commands.addForwardD(-20);
		commands.addRotationGyro(90);
		commands.addForwardD(40);
		commands.addRotationGyro(90);
		commands.addSequence(3, 4);
		
		commands.addMastBottom();
		commands.addSequence(3, 5);
		
		commands.addAutoCube();
		commands.addMastSwitch();
		commands.addEject();
		commands.addSequence(5, 6);
	}

}
