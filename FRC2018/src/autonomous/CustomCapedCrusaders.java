package autonomous;

import org.usfirst.frc.team5468.robot.Hardware;

import templates.AutoProgram;

public class CustomCapedCrusaders extends AutoProgram {

	public CustomCapedCrusaders(Hardware r) {
		super(r, "CAPED CRUSADERS");
		positionRelevant = true;
		switchRelevant = true;
		scaleRelevant = true;
	}

	@Override
	protected void RLL() {
		commands.addForwardD(144);
		commands.addSequence(0, 1);
	}

	@Override
	protected void RLR() {
		commands.addForwardD(339);
		commands.addRotationGyro(-95);
		commands.addSequence(0, 1);
		
		commands.addMastTop();
		commands.addSequence(0, 2);
		
		commands.addForwardD(20);
		commands.addEject();
		commands.addForwardD(-20);
		commands.addMastBottom();
		commands.addSequence(1, 2, 3);
	}

	@Override
	protected void RRL() {
		commands.addForwardD(170);
		commands.addRotationGyro(-90);
		commands.addSequence(0, 1);
		
		commands.addMastSwitch();
		commands.addSequence(0, 2);
		
		commands.addForwardT(2, .5);
		commands.addEject();
		commands.addForwardD(-5);
		commands.addMastBottom();
		commands.addSequence(1, 2, 3);
	}

	@Override
	protected void RRR() {
		commands.addForwardD(339);
		commands.addRotationGyro(-95);
		commands.addSequence(0, 1);
		
		commands.addMastTop();
		commands.addSequence(0, 2);
		
		commands.addForwardD(20);
		commands.addEject();
		commands.addForwardD(-20);
		commands.addMastBottom();
		commands.addSequence(1, 2, 3);
	}

	@Override
	protected void LRR() {
		commands.addForwardD(144);
		commands.addSequence(0, 1);
	}

	@Override
	protected void LLR() {
		commands.addForwardD(170);
		commands.addRotationGyro(90);
		commands.addSequence(0, 1);
		
		commands.addMastSwitch();
		commands.addSequence(0, 2);
		
		commands.addForwardT(2, .5);
		commands.addEject();
		commands.addForwardD(-5);
		commands.addMastBottom();
		commands.addSequence(1, 2, 3);
	}

	@Override
	protected void LRL() {
		commands.addForwardD(339);
		commands.addRotationGyro(95);
		commands.addSequence(0, 1);
		
		commands.addMastTop();
		commands.addSequence(0, 2);
		
		commands.addForwardD(15);
		commands.addEject();
		commands.addForwardD(-20);
		commands.addMastBottom();
		commands.addSequence(1, 2, 3);
	}

	@Override
	protected void LLL() {
		commands.addForwardD(339);
		commands.addRotationGyro(95);
		commands.addSequence(0, 1);
		
		commands.addMastTop();
		commands.addSequence(0, 2);
		
		commands.addForwardD(15);
		commands.addEject();
		commands.addForwardD(-20);
		commands.addMastBottom();
		commands.addSequence(1, 2, 3);
	}

}
