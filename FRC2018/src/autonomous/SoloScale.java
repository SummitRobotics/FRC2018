package autonomous;

import org.usfirst.frc.team5468.robot.Hardware;

import templates.AutoProgram;

public class SoloScale extends AutoProgram{

	public SoloScale(Hardware r) {
		super(r, "SCALE");
		positionRelevant = true;
		switchRelevant = false;
		scaleRelevant = true;
	}

	@Override
	protected void RLL() {
		commands.addForwardD(250);
		commands.addRotationGyro(-85);
		commands.addSequence(0, 1);
		
		commands.addForwardD(220);
		commands.addRotationGyro(90);
		commands.addSequence(1, 2);
		
		commands.addDelay(.1);
		commands.addMastTop();
		commands.addSequence(1, 3);
		
		commands.addForwardD(56);
		commands.addEject();
		//commands.addForwardD(-40);
		//commands.addMastBottom();
		commands.addSequence(2, 3, 4);
	}

	@Override
	protected void RLR() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void RRL() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void RRR() {
		commands.addForwardD(338);
		commands.addRotationGyro(-90);
		commands.addSequence(0, 1);
		
		commands.addDelay(.1);
		commands.addMastTop();
		commands.addSequence(0, 2);
		
		commands.addForwardD(10);
		commands.addEject();
		commands.addForwardD(-20);
		commands.addMastBottom();
		commands.addSequence(1, 2, 3);
	}

	@Override
	protected void LRR() {
		commands.addForwardD(244);
		commands.addRotationGyro(89);
		commands.addSequence(0, 1);
		
		commands.addForwardD(220);
		commands.addRotationGyro(-100);
		commands.addSequence(1, 2);
		
		commands.addDelay(.1);
		commands.addMastTop();
		commands.addSequence(1, 3);
		
		commands.addForwardD(44);
		commands.addEject();
		//commands.addForwardD(-40);
		//commands.addMastBottom();
		commands.addSequence(2, 3, 4);
	}

	@Override
	protected void LLR() {
	}

	@Override
	protected void LRL() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void LLL() {
		commands.addForwardD(338);
		commands.addRotationGyro(90);
		commands.addSequence(0, 1);
		
		commands.addDelay(.1);
		commands.addMastTop();
		commands.addSequence(0, 2);
		
		commands.addForwardD(10);
		commands.addEject();
		commands.addForwardD(-20);
		commands.addMastBottom();
		commands.addSequence(1, 2, 3);
	}

}
