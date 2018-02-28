package autonomous;
import org.usfirst.frc.team5468.robot.Hardware;
import templates.AutoProgram;

public class LeftLeftSwitchPower extends AutoProgram{

	public LeftLeftSwitchPower(Hardware r) {
		super(r, "WLLP");
	}

	@Override
	public void autonomousInit() {
		initCommands(2);
		
		commands[1].addClamp();
		commands[1].addMastT(10);
		
		commands[0].addForwardT(3, .4);
		commands[0].addRotationGyro(90);
		commands[0].addForwardT(.5, .2);
		commands[0].addExtend();
		commands[0].addClamp();
	}

}
