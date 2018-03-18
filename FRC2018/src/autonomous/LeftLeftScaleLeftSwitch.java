package autonomous;

import org.usfirst.frc.team5468.robot.Hardware;
import templates.AutoProgram;

public class LeftLeftScaleLeftSwitch extends AutoProgram{

	public LeftLeftScaleLeftSwitch(Hardware r) {
		super(r, "LLL");
	}

	@Override
	public void addActions() {
		commands.addForwardD(324.5);
		commands.addRotationGyro(90);
		commands.addSequence(0, 1);
		
		commands.addMastTop();
		commands.addSequence(0, 2);
		
		commands.addForwardD(10);
		commands.addEject();
		commands.addSequence(1, 2, 3);
		
		commands.addForwardD(-10);
		commands.addRotationGyro(60);
		commands.addForwardD(70);
		commands.addRotationGyro(30);
		commands.addSequence(3, 4);
		
		commands.addMastBottom();
		commands.addSequence(3, 5);
		
		commands.addAutoCube();
		commands.addMastSwitch();
		commands.addEject();
		commands.addSequence(5, 6);
	}

}
