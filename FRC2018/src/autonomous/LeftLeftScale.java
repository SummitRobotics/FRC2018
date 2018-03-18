package autonomous;

import org.usfirst.frc.team5468.robot.Hardware;
import templates.AutoProgram;

public class LeftLeftScale extends AutoProgram {
	
	public LeftLeftScale(Hardware r) {
		super(r, "CLL");
	}
	
	@Override
	public void addActions() {
		commands.addMastTop();
		commands.addSequence(0, 1);
		
		commands.addForwardD(333);
		commands.addRotationGyro(90);
		commands.addSequence(0, 2);
		
		commands.addForwardD(10);
		commands.addEject();
		commands.addForwardD(-20);
		commands.addSequence(1, 2, 3);
		
		commands.addRotationGyro(40);
		commands.addForwardD(100);
		commands.addSequence(3, 4);
		
		commands.addMastBottom();
		commands.addSequence(3, 5);
		
		commands.addAutoCube();
		commands.addSequence(4, 5, 6);
	}
}
