package autonomous;

import org.usfirst.frc.team5468.robot.Hardware;
import templates.AutoProgram;

public class LeftLeftScaleLeftSwitch extends AutoProgram{

	public LeftLeftScaleLeftSwitch(Hardware r) {
		super(r, "LLL");
	}

	@Override
	public void addActions() {
		//commands.addForwardD(324.5);
		commands.addForwardD(200);
		commands.addRotationGyro(90);
		commands.addSequence(0, 1);
		
		//commands.addMastD(0);
		commands.addSequence(0, 2);
		
		//commands.addEject();
		//commands.addExtend();
		commands.addSequence(1, 2, 3);
		
		commands.addRotationGyro(90);
		commands.addForwardD(92);
		commands.addRotationGyro(-90);
		commands.addForwardD(68);
		commands.addRotationGyro(90);
		commands.addSequence(3, 4);
		
		commands.addMastD(0);
		commands.addSequence(3, 5);
		
		commands.addAutoCube();
		commands.addMastD(0);
		//commands.addEject();
		commands.addSequence(5, 6);
	}

}
