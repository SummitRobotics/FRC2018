package autonomous;

import org.usfirst.frc.team5468.robot.Hardware;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import templates.AutoProgram;

public class CustomTestingEnvironment extends AutoProgram{

	public CustomTestingEnvironment(Hardware r) {
		super(r, "TESTING");
		positionRelevant = true;
		switchRelevant = false;
		scaleRelevant = true;
	}

	@Override
	protected void RLL() {
		SmartDashboard.putString("OUTPUTKEY", "RLL");
	}

	@Override
	protected void RLR() {
		// TODO Auto-generated method stub
		SmartDashboard.putString("OUTPUTKEY", "RLR");
	}

	@Override
	protected void RRL() {
		// TODO Auto-generated method stub
		SmartDashboard.putString("OUTPUTKEY", "RRL");
	}

	@Override
	protected void RRR() {
		SmartDashboard.putString("OUTPUTKEY", "RRR");
	}

	@Override
	protected void LRR() {
		// TODO Auto-generated method stub
		SmartDashboard.putString("OUTPUTKEY", "LRR");
	}

	@Override
	protected void LLR() {
		// TODO Auto-generated method stub
		SmartDashboard.putString("OUTPUTKEY", "LLR");
	}

	@Override
	protected void LRL() {
		// TODO Auto-generated method stub
		SmartDashboard.putString("OUTPUTKEY", "LRL");
	}

	@Override
	protected void LLL() {
		// TODO Auto-generated method stub
		commands.addMastTop();
		commands.addMastBottom();
		commands.addSequence(0, 1);
		
		SmartDashboard.putString("OUTPUTKEY", "LLL");
	}

}
