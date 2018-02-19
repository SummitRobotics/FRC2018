package autonomous;
import org.usfirst.frc.team5468.robot.Hardware;
import templates.AutoProgram;

public class RightLeftSwitch extends AutoProgram{

	public RightLeftSwitch(Hardware r) {
		super(r, "WLR");
	}

	@Override
	public void autonomousInit() {
		initCommands(2);
		
		commands[1].addAutoClamp();
		commands[1].addMastD(1);
		
		commands[0].addForwardD(225);
		commands[0].addRotationGyro(90);
		commands[0].addForwardD(170);
		commands[0].addRotationGyro(90);
		commands[0].addForwardT(3, .2);
		//commands[0].addEject(1);
	}

}
