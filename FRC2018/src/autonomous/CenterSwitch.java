package autonomous;

import org.usfirst.frc.team5468.robot.Hardware;

import templates.AutoProgram;

public class CenterSwitch extends AutoProgram {

	public CenterSwitch(Hardware r) {
		super(r, "DOUBLE CENTER SWITCH");
		positionRelevant = false;
		switchRelevant = true;
		scaleRelevant = false;
	}

	@Override
	protected void RLL() {
		// TODO Auto-generated method stub
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
		commands.addMastT(1.5, 1);
		commands.addSequence(0, 1);
		
		commands.addForwardT(.4, .5);
		commands.addRotationGyro(15);
		commands.addForwardD(91);
		commands.addSequence(0, 2);
		
		commands.addExtend();
		commands.addDelay(.2);
		commands.addShoot();
		commands.addExtend();
		commands.addSequence(1, 2, 3);
		
		commands.addForwardD(-91);
		commands.addRotationGyro(-22);
		commands.addSequence(3, 4);
		
		commands.addMastT(1.3, -.7);
		commands.addSequence(3, 5);
		
		commands.addClamp();
		commands.toggleIntake(-1);
		commands.addForwardD(72);
		commands.addClamp();
		commands.addDelay(.1);
		commands.toggleIntake(0);
		commands.addDelay(.25);
		commands.addSequence(4, 5, 6);
		
		commands.addForwardD(-62);
		commands.addSequence(6, 7);
		
		commands.addMastT(.5, 1);
		commands.addSequence(6, 8);
		
		commands.addRotationGyro(24);
		commands.addForwardD(90);
		commands.addRotationGyro(-15);
		commands.addSequence(7, 9);
		
		commands.addMastT(1.25, 1);
		commands.addSequence(7, 8, 10);
		
		commands.addExtend();
		commands.addDelay(.2);
		commands.addShoot();
		commands.addExtend();
		commands.addSequence(9,10,11);
		
		commands.addRotationGyro(15);
		commands.addForwardD(-45);
		commands.addRotationGyro(-45);
		commands.addSequence(11, 12);
		
		commands.addMastT(2, -.7);
		commands.addSequence(11, 13);
	}

	@Override
	protected void LRR() {
		// TODO Auto-generated method stub
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
		commands.addMastT(1.5, 1);
		commands.addSequence(0, 1);
		
		commands.addForwardT(.4, .5);
		commands.addDelay(.15);
		commands.addRotationGyro(-33);
		commands.addForwardD(102);
		commands.addRotationGyro(15);
		commands.addSequence(0, 2);
		
		commands.addExtend();
		commands.addDelay(.2);
		commands.addShoot();
		commands.addExtend();
		commands.addSequence(1, 2, 3);
		
		commands.addRotationGyro(-15);
		commands.addForwardD(-104);
		commands.addRotationGyro(27);
		commands.addSequence(3, 4);
		
		commands.addMastT(1.3, -.7);
		commands.addSequence(3, 5);
		
		commands.addClamp();
		commands.toggleIntake(-1);
		commands.addForwardD(72);
		commands.addClamp();
		commands.addDelay(.1);
		commands.toggleIntake(0);
		commands.addDelay(.25);
		commands.addSequence(4, 5, 6);
		
		commands.addForwardD(-64);
		commands.addSequence(6, 7);
		
		commands.addMastT(.5, 1);
		commands.addSequence(6, 8);
		
		commands.addRotationGyro(-30);
		commands.addForwardD(110);
		commands.addRotationGyro(30);
		commands.addSequence(7, 9);
		
		commands.addMastT(1.25, 1);
		commands.addSequence(7, 8, 10);
		
		commands.addExtend();
		commands.addDelay(.2);
		commands.addShoot();
		commands.addExtend();
		commands.addSequence(9,10,11);
		
		commands.addRotationGyro(-30);
		commands.addForwardD(-45);
		commands.addRotationGyro(45);
		commands.addSequence(11, 12);
		
		commands.addMastT(2, -.7);
		commands.addSequence(11, 13);
	}
}