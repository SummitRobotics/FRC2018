package actions;

import org.usfirst.frc.team5468.robot.Hardware;
import com.ctre.phoenix.motorcontrol.ControlMode;
import templates.Action;

public class MastD extends Action {
	//distances
	private double target;
	
	//constructor
	public MastD(Hardware r, double distance) {
		super(r);
		target = distance;
	}

	@Override
	//travel distance - beta
	public void run() {
		started = true;
		//setMast(target);
		update();
	}
	
	@Override
	public void update() {
		if(Math.abs(target - robot.mast.getSelectedSensorPosition(0)) < 500 || !robot.mastEnabled) {
			setMast(robot.mast.getSelectedSensorPosition(0));
			finished = true;
		}
	}
	
	private void setMast(double t) {
		if(robot.mastEnabled) {
			robot.mast.set(ControlMode.MotionMagic, t);
		}
	}

	@Override
	public String getAction() {
		return "Distance";
	}

}
