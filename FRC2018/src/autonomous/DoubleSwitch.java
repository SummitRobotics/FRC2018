package autonomous;

import org.usfirst.frc.team5468.robot.Hardware;

import templates.AutoProgram;

public class DoubleSwitch extends AutoProgram{

	public DoubleSwitch(Hardware r) {
		super(r, "SWITCH t. SWITCH");
		positionRelevant = false;
		switchRelevant = true;
		scaleRelevant = false;
	}

	@Override
	protected void RLL() {
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
		commands.addMastSwitch();
		commands.addSequence(0, 1);
		
		commands.addForwardD(20);
		commands.addRotationGyro(20);
		commands.addForwardD(94);
		commands.addSequence(0, 2);
		
		commands.addEject();
		commands.addSequence(1, 2, 3);
		
		commands.addForwardD(-100);
		commands.addRotationGyro(-20);
		commands.addSequence(3, 4);
		
		commands.addMastBottom();
		commands.addSequence(3, 5);
		
		commands.addForwardT(1.5, .4);
		commands.addTempIntake();
		commands.addClamp();
		commands.addForwardD(-70);
		commands.addSequence(4, 5, 6);
		
		commands.addMastSwitch();
		commands.addSequence(6, 7);
		
		commands.addRotationGyro(20);
		commands.addForwardD(50);
		commands.addSequence(6, 8);
		
		commands.addEject();
		commands.addSequence(7,8,9);
	}

	@Override
	protected void LRR() {
	}

	@Override
	protected void LLR() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void LRL() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void LLL() {
		commands.addMastSwitch();
		commands.addSequence(0, 1);
		
		commands.addForwardD(20);
		commands.addRotationGyro(-30);
		commands.addForwardD(100);
		commands.addSequence(0, 2);
		
		commands.addEject();
		commands.addSequence(1, 2, 3);
		
		commands.addForwardD(-100);
		commands.addRotationGyro(30);
		commands.addSequence(3, 4);
		
		commands.addMastBottom();
		commands.addSequence(3, 5);
		
		commands.addForwardT(1, .5);
		commands.addTempIntake();
		commands.addClamp();
		commands.addForwardD(-70);
		commands.addSequence(4, 5, 6);
		
		commands.addMastSwitch();
		commands.addSequence(6, 7);
		
		commands.addRotationGyro(-30);
		commands.addForwardD(50);
		commands.addSequence(6, 8);
		
		commands.addEject();
		commands.addSequence(7,8,9);
	}

}
