package autonomous;
import org.usfirst.frc.team5468.robot.Hardware;
import templates.AutoProgram;

public class RightLeftSwitchPower extends AutoProgram{

	public RightLeftSwitchPower(Hardware r) {
		super(r, "WLRP");
	}

	@Override
	public void addActions() {
		commands.addMastT(10);
		commands.addSequence(0, 1);
		
		commands.addForwardT(5, .7);
		commands.addRotationGyro(90);
		commands.addForwardT(5, .7);
		commands.addRotationGyro(90);
		commands.addForwardT(3, .2);
		commands.addExtend();
		commands.addClamp();
		commands.addSequence(0, 2);
	}

}
