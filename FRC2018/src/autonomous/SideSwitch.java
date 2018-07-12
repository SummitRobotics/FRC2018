package autonomous;

import org.usfirst.frc.team5468.robot.Hardware;

import templates.AutoProgram;

public class SideSwitch extends AutoProgram{

	public SideSwitch(Hardware r) {
		super(r, "SIDE SWITCH");
		positionRelevant = true;
		switchRelevant = true;
		scaleRelevant = false;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void RRL() {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void LRL() {
		// TODO Auto-generated method stub
		
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
