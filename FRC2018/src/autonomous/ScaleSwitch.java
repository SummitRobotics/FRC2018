package autonomous;

import org.usfirst.frc.team5468.robot.Hardware;

import templates.AutoProgram;

public class ScaleSwitch extends AutoProgram{

	public ScaleSwitch(Hardware r) {
		super(r, "SCALE t. SWITCH");
		positionRelevant = true;
		switchRelevant = true;
		scaleRelevant = true;
	}

	@Override
	protected void RLL() {
		commands.addForwardD(244);
		commands.addRotationGyro(-90);
		commands.addSequence(0, 1);
		
		commands.addForwardD(220);
		commands.addRotationGyro(90);
		commands.addSequence(1, 2);
		
		commands.addMastTop();
		commands.addSequence(1, 3);
		
		commands.addForwardD(40);
		commands.addEject();
		commands.addForwardD(-40);
		commands.addSequence(2, 3, 4);
		
		commands.addRotationGyro(180);
		commands.addForwardD(0);
		commands.addSequence(5, 6);
		
		commands.addMastBottom();
		commands.addSequence(5, 7);
		
		commands.addAutoCube();
		commands.addMastSwitch();
		commands.addEject();
		commands.addSequence(6,7,8);
	}

	@Override
	protected void RLR() {
		commands.addForwardD(346);
		commands.addRotationGyro(-95);
		commands.addSequence(0, 1);
		
		commands.addMastTop();
		commands.addSequence(0, 2);
		
		commands.addForwardD(10);
		commands.addEject();
		commands.addForwardD(-20);
		commands.addMastBottom();
		commands.addSequence(3, 4);
	}

	@Override
	protected void RRL() {
		commands.addForwardD(244);
		commands.addRotationGyro(-90);
		commands.addSequence(0, 1);
		
		commands.addForwardD(220);
		commands.addRotationGyro(90);
		commands.addSequence(1, 2);
		
		commands.addMastTop();
		commands.addSequence(1, 3);
		
		commands.addForwardD(40);
		commands.addEject();
		commands.addForwardD(-40);
		commands.addSequence(2, 3, 4);
	}

	@Override
	protected void RRR() {
		commands.addForwardD(244);
		commands.addRotationGyro(-90);
		commands.addSequence(0, 1);
		
		commands.addForwardD(220);
		commands.addRotationGyro(90);
		commands.addSequence(1, 2);
		
		commands.addMastTop();
		commands.addSequence(1, 3);
		
		commands.addForwardD(40);
		commands.addEject();
		commands.addForwardD(-40);
		commands.addSequence(2, 3, 4);
		
		commands.addRotationGyro(180);
		commands.addForwardD(0);
		commands.addSequence(5, 6);
		
		commands.addMastBottom();
		commands.addSequence(5, 7);
		
		commands.addAutoCube();
		commands.addMastSwitch();
		commands.addEject();
		commands.addSequence(6,7,8);
	}

	@Override
	protected void LRR() {
		commands.addForwardD(244);
		commands.addRotationGyro(90);
		commands.addSequence(0, 1);
		
		commands.addForwardD(220);
		commands.addRotationGyro(-90);
		commands.addSequence(1, 2);
		
		commands.addMastTop();
		commands.addSequence(1, 3);
		
		commands.addForwardD(40);
		commands.addEject();
		commands.addForwardD(-40);
		commands.addSequence(2, 3, 4);
		
		commands.addRotationGyro(180);
		commands.addForwardD(0);
		commands.addSequence(5, 6);
		
		commands.addMastBottom();
		commands.addSequence(5, 7);
		
		commands.addAutoCube();
		commands.addMastSwitch();
		commands.addEject();
		commands.addSequence(6,7,8);
	}

	@Override
	protected void LLR() {
		commands.addForwardD(244);
		commands.addRotationGyro(90);
		commands.addSequence(0, 1);
		
		commands.addForwardD(220);
		commands.addRotationGyro(-90);
		commands.addSequence(1, 2);
		
		commands.addMastTop();
		commands.addSequence(1, 3);
		
		commands.addForwardD(40);
		commands.addEject();
		commands.addForwardD(-40);
		commands.addSequence(2, 3, 4);
	}

	@Override
	protected void LRL() {
		commands.addForwardD(346);
		commands.addRotationGyro(95);
		commands.addSequence(0, 1);
		
		commands.addMastTop();
		commands.addSequence(0, 2);
		
		commands.addForwardD(10);
		commands.addEject();
		commands.addForwardD(-20);
		commands.addMastBottom();
		commands.addSequence(3, 4);
	}

	@Override
	protected void LLL() {
		commands.addForwardD(244);
		commands.addRotationGyro(90);
		commands.addSequence(0, 1);
		
		commands.addForwardD(220);
		commands.addRotationGyro(-90);
		commands.addSequence(1, 2);
		
		commands.addMastTop();
		commands.addSequence(1, 3);
		
		commands.addForwardD(40);
		commands.addEject();
		commands.addForwardD(-40);
		commands.addSequence(2, 3, 4);
		
		commands.addRotationGyro(180);
		commands.addForwardD(0);
		commands.addSequence(5, 6);
		
		commands.addMastBottom();
		commands.addSequence(5, 7);
		
		commands.addAutoCube();
		commands.addMastSwitch();
		commands.addEject();
		commands.addSequence(6,7,8);
	}

}
