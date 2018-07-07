package autonomous;

import org.usfirst.frc.team5468.robot.Hardware;

import templates.AutoProgram;

public class DoubleScale extends AutoProgram{

	public DoubleScale(Hardware r) {
		super(r, "NEW DOUBLE SCALE");
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
		commands.addForwardD(302);
		commands.addRotationGyro(-40);
		commands.addSequence(0,1);
		
		commands.addMastTop();
		commands.addSequence(0,2);
		
		commands.addShoot();
		commands.addForwardD(-24);
		commands.addSequence(1,2,3);
		
		/*commands.addDelay(1.5);
		commands.addRotationGyro(-80);
		commands.toggleIntake(-1);
		commands.addForwardD(72);
		commands.addSequence(3,4);
		
		commands.addMastBottom();
		commands.addSequence(3, 5);
		
		commands.addClamp();
		commands.addDelay(.1);
		commands.toggleIntake(0);
		commands.addSequence(4, 5,6);
		
		commands.addForwardD(-12);
		commands.addRotationGyro(110);
		commands.addForwardD(40);
		commands.addSequence(6, 7);
		
		commands.addDelay(.3);
		commands.addMastT(3, 1);
		commands.addSequence(6, 8);
		
		commands.addEject();
		commands.addForwardD(-10);
		commands.addMastBottom();
		commands.addSequence(7,8,9);*/
	}

	@Override
	protected void RRL() {
		/*commands.addForwardD(120);
		commands.addRotationGyro(-45);
		commands.addSequence(0, 1);
		
		commands.addMastSwitch();
		commands.addSequence(0, 2);
		
		commands.addForwardD(6);
		commands.addEject();
		commands.addForwardD(-6);
		commands.addMastBottom();
		commands.addSequence(1, 2, 3);*/
		commands.addForwardD(144);
		commands.addSequence(0, 1);
	}

	@Override
	protected void RRR() {
		commands.addForwardD(302);
		commands.addRotationGyro(-40);
		commands.addSequence(0,1);
		
		commands.addMastTop();
		commands.addSequence(0,2);
		
		commands.addShoot();
		commands.addForwardD(-24);
		commands.addSequence(1,2,3);
		
		/*commands.addDelay(1.5);
		commands.addRotationGyro(-80);
		commands.addClamp();
		commands.toggleIntake(-1);
		commands.addForwardD(72);
		commands.addSequence(3,4);
		
		commands.addMastBottom();
		commands.addSequence(3,5);
		
		commands.addClamp();
		commands.addDelay(.1);
		commands.toggleIntake(0);
		commands.addMastT(.2, 1);
		commands.addSequence(4,5,6);
		
		commands.addForwardD(-12);
		commands.addRotationGyro(110);
		commands.addForwardD(40);
		commands.addSequence(6,7);
		
		commands.addDelay(.3);
		commands.addMastT(3.5, 1);
		commands.addSequence(6,8);
		
		commands.addEject();
		commands.addForwardD(-10);
		commands.addMastBottom();
		commands.addSequence(7,8,9);*/
	}

	@Override
	protected void LRR() {
		commands.addForwardD(144);
		commands.addSequence(0, 1);
	}

	@Override
	protected void LLR() {
		/*commands.addForwardD(120);
		commands.addRotationGyro(45);
		commands.addSequence(0, 1);
		
		commands.addMastSwitch();
		commands.addSequence(0, 2);
		
		commands.addForwardD(6);
		commands.addEject();
		commands.addForwardD(-6);
		commands.addMastBottom();
		commands.addSequence(1, 2, 3);*/
		commands.addForwardD(144);
		commands.addSequence(0, 1);
	}

	@Override
	protected void LRL() {
		commands.addForwardD(302);
		commands.addRotationGyro(40);
		commands.addSequence(0,1);
		
		commands.addMastTop();
		commands.addSequence(0,2);
		
		commands.addShoot();
		commands.addForwardD(-24);
		commands.addSequence(1,2,3);
		
		/*commands.addDelay(1.5);
		commands.addRotationGyro(80);
		commands.toggleIntake(-1);
		commands.addForwardD(72);
		commands.addSequence(3,4);
		
		commands.addMastBottom();
		commands.addSequence(3, 5);
		
		commands.addClamp();
		commands.addDelay(.1);
		commands.toggleIntake(0);
		commands.addSequence(4, 5,6);
		
		commands.addForwardD(-12);
		commands.addRotationGyro(-110);
		commands.addForwardD(40);
		commands.addSequence(6, 7);
		
		commands.addDelay(.3);
		commands.addMastT(3, 1);
		commands.addSequence(6, 8);
		
		commands.addEject();
		commands.addForwardD(-10);
		commands.addMastBottom();
		commands.addSequence(7,8,9);*/
	}

	@Override
	protected void LLL() {
		commands.addForwardD(302);
		commands.addRotationGyro(40);
		commands.addSequence(0,1);
		
		commands.addMastTop();
		commands.addSequence(0,2);
		
		commands.addShoot();
		commands.addForwardD(-24);
		commands.addSequence(1,2,3);
		
		/*commands.addDelay(1.5);
		commands.addRotationGyro(80);
		commands.toggleIntake(-1);
		commands.addForwardD(72);
		commands.addSequence(3,4);
		
		commands.addMastBottom();
		commands.addSequence(3, 5);
		
		commands.addClamp();
		commands.addDelay(.1);
		commands.toggleIntake(0);
		commands.addMastT(.2, 1);
		commands.addSequence(4, 5,6);
		
		commands.addForwardD(-12);
		commands.addRotationGyro(-110);
		commands.addForwardD(40);
		commands.addSequence(6, 7);
		
		commands.addDelay(.3);
		commands.addMastT(3, 1);
		commands.addSequence(6, 8);
		
		commands.addEject();
		commands.addForwardD(-10);
		commands.addMastBottom();
		commands.addSequence(7,8,9);*/
	}

}
