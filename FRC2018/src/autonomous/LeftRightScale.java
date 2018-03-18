package autonomous;

import org.usfirst.frc.team5468.robot.Hardware;
import templates.AutoProgram;

public class LeftRightScale extends AutoProgram{

	public LeftRightScale(Hardware r) {
		super(r, "CRL");
	}

	@Override
	public void addActions() {
		commands.addDelay(6);
		commands.addMastTop();
		commands.addSequence(0, 1);
		
		commands.addForwardD(250);
		commands.addRotationGyro(90);
		commands.addForwardD(228);
		commands.addRotationGyro(-110);
		commands.addForwardD(45);
		commands.addSequence(0, 2);
		
		commands.addEject();
		commands.addForwardD(-40);
		commands.addSequence(1, 2, 5);
	}
	
	/*
	 * @Override
	public void addActions() {
		commands.addDelay(6);
		commands.addMastTop();
		commands.addSequence(0, 1);
		
		commands.addForwardD(250);
		commands.addRotationGyro(90);
		commands.addSequence(0, 2);
		
		commands.addForwardD(236);
		//commands.addRotationGyro(-100);
		commands.addSequence(2, 3);
		
		commands.addRotationGyro(-100);
		//commands.addForwardD(45);
		commands.addSequence(1, 3, 4);
		
		/*
		commands.addEject();
		commands.addForwardD(-20);
		commands.addSequence(1, 4, 5);
		*/
}
