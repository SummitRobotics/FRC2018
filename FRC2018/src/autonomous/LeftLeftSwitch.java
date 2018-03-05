package autonomous;
import org.usfirst.frc.team5468.robot.Hardware;
import templates.AutoProgram;

public class LeftLeftSwitch extends AutoProgram{

	public LeftLeftSwitch(Hardware r) {
		super(r, "WLL");
	}
	
	@Override
	public void addActions() {
		commands.addMastT(4, .5);
		commands.addSequence(0, 1);
		
		commands.addForwardD(158);
		commands.addRotationGyro(90);
		commands.addForwardT(2, .2);
		commands.addExtend();
		commands.addForwardT(.5, 0);
		commands.addClamp();
		commands.addSequence(0, 2);
	}

}
