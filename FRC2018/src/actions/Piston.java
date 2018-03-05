
package actions;

import org.usfirst.frc.team5468.robot.Hardware;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import templates.Action;

public class Piston extends Action{
	private DoubleSolenoid piston;
	
	public Piston(Hardware r, DoubleSolenoid x) {
		super(r);
		piston = x;
	}

	@Override
	public void actionInit() {
		//nothing to init
	}

	@Override
	public void actionPeriodic() {
		if(robot.pneumaticsEnabled) {
			if(piston.get() == DoubleSolenoid.Value.kForward) {
				piston.set(DoubleSolenoid.Value.kReverse);
			}
			else {
				piston.set(DoubleSolenoid.Value.kForward);
			}
		}
	}

	@Override
	public boolean actionFinished() {
		return true;
	}
	
	@Override
	public String getAction() {
		return "Clamp action";
	}

}
