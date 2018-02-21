
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
	public void run() {
		started = true;
		if(robot.pneumaticsEnabled) {
			if(piston.get() == DoubleSolenoid.Value.kReverse) {
				piston.set(DoubleSolenoid.Value.kForward);
			}
			else {
				piston.set(DoubleSolenoid.Value.kReverse);
			}
		}
		update();
	}
	
	public DoubleSolenoid.Value getPosition(){
		return piston.get();
	}

	@Override
	public void update() {
		finished = true;
	}

	@Override
	public String getAction() {
		return "Clamp action";
	}

}
