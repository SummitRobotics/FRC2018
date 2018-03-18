package autonomous;

import org.usfirst.frc.team5468.robot.Hardware;
import templates.AutoProgram;

public class RightLeftScale extends AutoProgram {

	public RightLeftScale(Hardware r) {
		super(r, "CLR");
	}

	@Override
	public void addActions() {
		commands.addDelay(6);
		commands.addMastTop();
		commands.addSequence(0, 1);
		
		commands.addForwardD(250);
		commands.addRotationGyro(-90);
		commands.addForwardD(236);
		commands.addRotationGyro(100);
		commands.addSequence(0, 2);
		
		commands.addForwardD(45);
		commands.addEject();
		commands.addForwardD(-20);
		commands.addSequence(1, 2, 5);
		
		/*
		commands.addDelay(3);
		commands.addMastTop();
		commands.addSequence(0, 1);
		
		commands.addForwardD(250);
		commands.addRotationGyro(-90);
		commands.addSequence(0, 2);
		
		commands.addForwardD(230);
		//commands.addRotationGyro(90);
		commands.addSequence(2, 3);
		
		commands.addForwardD(50);
		commands.addEject();
		commands.addSequence(1, 3, 4);
		*/
	}

}
