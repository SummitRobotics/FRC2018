package autonomous;
import org.usfirst.frc.team5468.robot.Hardware;
import templates.AutoProgram;

public class LeftLeftSwitchPower extends AutoProgram{

	public LeftLeftSwitchPower(Hardware r) {
		super(r, "WLLP");
	}

	@Override
	public void addActions() {
		commands.addMastSwitch();
		commands.addSequence(0, 1);
		
		commands.addForwardT(3, .4);
		commands.addRotationGyro(90);
		commands.addForwardT(.5, .2);
		commands.addSequence(0, 2);
		
		commands.addEject();
		commands.addSequence(1, 2, 3);
	}

}
