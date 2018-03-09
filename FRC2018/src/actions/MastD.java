package actions;

import org.usfirst.frc.team5468.robot.Hardware;

import edu.wpi.first.wpilibj.Counter;
import functions.HallEncoder;
import templates.Action;

public class MastD extends Action {
	//distances
	private double target;
	private double offset = 10;
	double power = .5;
	
	private int upperCounter;
	private int lowerCounter;
	
	Counter mastEncoder;
	
	//constructor
	public MastD(Hardware r, double distance) {
		super(r);
		target = distance;
	}

	@Override
	public void actionInit() {
		encoder = new HallEncoder(robot.hall);
		upperCounter = robot.higherMastInteraction.get();
		lowerCounter = robot.lowerMastInteraction.get();
		
		mastEncoder = robot.hall;
		mastEncoder.reset();
		mastEncoder.setDistancePerPulse(robot.variables.getDistancePerPulse());
		
		if(target < 0) {
			power = -power;
		}
		
		
	}
	
	private double smoothPower(double point) {
		int n = 2;
		return ((point*n) + power)/(1+n);
	}

	@Override
	public void actionPeriodic() {
		if(robot.mastEnabled) {
			robot.mast.set(ControlMode.PercentOutput, smoothPower(robot.mast.getMotorOutputPercent()));
			updateDistance();
		}
	}

	@Override
	public boolean actionFinished() {
		
		//if the target has been reached or some technical problems discovered
		if(Math.abs(target - mastEncoder.getDistance()) < offset || !robot.mastEnabled || checkLimits()) {
			//Log our change
			robot.addMastDistance(mastEncoder.getDistance());
			//then finish action
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public String getAction() {
		return "Distance";
	}
	
	private void updateDistance() {
		if(robot.lowerMastInteraction.get() != lowerCounter || robot.lowerMastSwitch.get()) {
			lowerCounter = robot.lowerMastInteraction.get();
			robot.setMastDistance(0);
		}
	}
	
	private boolean checkLimits() {
		if(robot.higherMastInteraction.get() != upperCounter || robot.lowerMastInteraction.get() != lowerCounter || robot.lowerMastSwitch.get() || robot.higherMastSwitch.get()) {
			if(robot.higherMastSwitch.get() && robot.mast.getMotorOutputPercent() <= 0) {
				return false;
			} else {
				if(robot.lowerMastSwitch.get() && robot.mast.getMotorOutputPercent() >= 0) {
					return false;
				} else {
					return true;
				}
			}
			
		} else {
			return false;
		}
	}

}
