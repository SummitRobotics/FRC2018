package actions;

import org.usfirst.frc.team5468.robot.Hardware;
import com.ctre.phoenix.motorcontrol.ControlMode;

import functions.HallEncoder;
import templates.Action;

public class MastD extends Action {
	//distances
	private double target;
	private double offset;
	private double maxPower;
	private double minPower;
	private double variation;
	private HallEncoder encoder;
	
	//constructor
	public MastD(Hardware r, double distance) {
		super(r);
		target = distance;
	}

	@Override
	//travel distance - beta
	public void run() {
		if(!started) {
			encoder = new HallEncoder(robot.hall);
		}
		started = true;
		if(robot.mastEnabled) {
			
		}
		update();
	}
	
	@Override
	public void update() {
		if(Math.abs(target - robot.mast.getSelectedSensorPosition(0)) < 500 || !robot.mastEnabled) {
			//setMast(robot.mast.getSelectedSensorPosition(0));
			finished = true;
		}
	}
	
	private void getPower(double input, double target) {
		double error = target - input;
		if(error > offset) {
			robot.mast.set(ControlMode.PercentOutput, maxPower);
		}
		else if(error > 0 && error < offset) {
			double power = (error/offset)*maxPower;
			robot.mast.set(ControlMode.PercentOutput, Math.max(minPower, power));
		}
		else if(error < -offset) {
			robot.mast.set(ControlMode.PercentOutput, -maxPower);
		}
		else if(error < 0 && error > -offset) {
			double power = -(error/offset)*maxPower;
			robot.mast.set(ControlMode.PercentOutput, Math.max(-minPower, power));
		}
	}

	
	@Override
	public String getAction() {
		return "Distance";
	}

}
