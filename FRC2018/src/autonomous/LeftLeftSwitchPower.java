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
		
		commands[1].addAutoClamp();
		commands[1].addMastD(1);
		
		commands[0].addForwardT(3, .4);
		commands[0].addRotationGyro(90);
		commands[0].addForwardT(2, .2);
		commands[0].addEject();
	}

}
