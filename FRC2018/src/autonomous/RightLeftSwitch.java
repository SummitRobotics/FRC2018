package autonomous;
import org.usfirst.frc.team5468.robot.Hardware;
import templates.AutoProgram;

public class RightLeftSwitch extends AutoProgram{

	public RightLeftSwitch(Hardware r) {
		super(r, "WLR");
	}

	@Override
	public void addActions() {
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

}
