package autonomous;

import org.usfirst.frc.team5468.robot.Hardware;

import templates.AutoProgram;

public class SwitchScale extends AutoProgram{

	public SwitchScale(Hardware r) {
		super(r, "SWITCH t. SCALE");
		positionRelevant = true;
		switchRelevant = true;
		scaleRelevant = true;
	}

	@Override
	protected void RLL() {
		commands.addMastSwitch();
		commands.addSequence(0, 1);
		
		commands.addForwardD(240);
		commands.addRotationGyro(-90);
		commands.addForwardD(190);
		commands.addRotationGyro(-90);
		commands.addForwardT(4, .2);
		commands.addSequence(0, 2);
		
		commands.addEject();
		commands.addSequence(1, 2, 3);
	}

	@Override
	protected void RLR() {
		commands.addMastSwitch();
		commands.addSequence(0, 1);
		
		commands.addForwardD(240);
		commands.addRotationGyro(-90);
		commands.addForwardD(190);
		commands.addRotationGyro(-90);
		commands.addForwardT(4, .2);
		commands.addSequence(0, 2);
		
		commands.addEject();
		commands.addSequence(1, 2, 3);
	}

	@Override
	protected void RRL() {
		commands.addForwardD(165);
		commands.addRotationGyro(-90);
		commands.addForwardT(1, .5);
		commands.addSequence(0, 1);
		
		commands.addMastSwitch();
		commands.addSequence(0, 2);
		
		commands.addEject();
		commands.addSequence(1, 2);
	}

	@Override
	protected void RRR() {
		commands.addForwardD(165);
		commands.addRotationGyro(-90);
		commands.addForwardT(1, .5);
		commands.addSequence(0, 1);
		
		commands.addMastSwitch();
		commands.addSequence(0, 2);
		
		commands.addEject();
		commands.addSequence(1, 2);
	}

	@Override
	protected void LRR() {
		commands.addMastSwitch();
		commands.addSequence(0, 1);
		
		commands.addForwardD(240);
		commands.addRotationGyro(90);
		commands.addForwardD(190);
		commands.addRotationGyro(90);
		commands.addForwardT(4, .2);
		commands.addSequence(0, 2);
		
		commands.addEject();
		commands.addSequence(1, 2, 3);
	}

	@Override
	protected void LLR() {
		commands.addForwardD(165);
		commands.addRotationGyro(90);
		commands.addForwardT(1, .5);
		commands.addSequence(0, 1);
		
		commands.addMastSwitch();
		commands.addSequence(0, 2);
		
		commands.addEject();
		commands.addSequence(1, 2);
	}

	@Override
	protected void LRL() {
		commands.addMastSwitch();
		commands.addSequence(0, 1);
		
		commands.addForwardD(240);
		commands.addRotationGyro(90);
		commands.addForwardD(190);
		commands.addRotationGyro(90);
		commands.addForwardT(4, .2);
		commands.addSequence(0, 2);
		
		commands.addEject();
		commands.addSequence(1, 2, 3);
	}

	@Override
	protected void LLL() {
		commands.addForwardD(165);
		commands.addRotationGyro(90);
		commands.addForwardT(1, .5);
		commands.addSequence(0, 1);
		
		commands.addMastSwitch();
		commands.addSequence(0, 2);
		
		commands.addEject();
		commands.addSequence(1, 2);
	}

}
