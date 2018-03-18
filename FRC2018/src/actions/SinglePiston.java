
package actions;

import org.usfirst.frc.team5468.robot.Hardware;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import templates.Action;

public class SinglePiston extends Action{
	private Solenoid piston;
	
	public SinglePiston(Hardware r, Solenoid x) {
		super(r);
		piston = x;
	}

	@Override
	public void actionInit() {
		//nothing to init
	}

	@Override
	public void actionPeriodic() {
		//if the pneumatics are all functional
		if(robot.pneumaticsEnabled) {
			piston.set(!piston.get());
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
