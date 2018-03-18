package autonomous;
import org.usfirst.frc.team5468.robot.Hardware;
import templates.AutoProgram;

public class LeftLeftSwitch extends AutoProgram{

	public LeftLeftSwitch(Hardware r) {
		super(r, "WLL");
	}
	
	@Override
	public void addActions() {
		commands.addMastSwitch();
		commands.addSequence(0, 1);
		
		commands.addForwardD(158);
		commands.addRotationGyro(90);
		commands.addForwardT(2, .2);
		commands.addSequence(0, 2);
		
		commands.addEject();
		commands.addSequence(1, 2, 3);
	}

}
