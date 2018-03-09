package actions;

import org.usfirst.frc.team5468.robot.Hardware;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.Timer;
import functions.HallEncoder;
import templates.Action;

public class MastT extends Action {
	//duration
	private double time;
	//timer
	private Timer clock;
	//default power
	private double power = .5;
	
	private int upperCounter;
	private int lowerCounter;
	
	private Counter mastEncoder;
	
	//constructor
	public MastT(Hardware r, double t) {
		super(r);
		time = t;
	}
	
	//constructor 2
	public MastT(Hardware r, double t, double p) {
		super(r);
		time = t;
		power = p;
	}
	
	@Override
	public void actionInit() {
		//start timer
		clock = new Timer();
		clock.start();
		mastEncoder = robot.hall;
		mastEncoder.reset();
		mastEncoder.setDistancePerPulse(robot.variables.getDistancePerPulse());
		//Init limit counters
		upperCounter = robot.higherMastInteraction.get();
		lowerCounter = robot.lowerMastInteraction.get();
	}

	@Override
	public void actionPeriodic() {
		//if the mast is enabled
		if(robot.mastEnabled){
			updateDistance();
			//then set power smoothly
			robot.mast.set(ControlMode.PercentOutput, smoothPower(robot.mast.getMotorOutputPercent()));
		}
	}
	
	//this prevents a 0 to X acceleration jump in the first iteration of the action
	private double smoothPower(double point) {
		int n = 2;
		return ((point*n) + power)/(1+n);
	}

	@Override
	public boolean actionFinished() {
		//if the timer has elapsed or the mast disabled or a limit switch is hit
		if(clock.get() > time || !robot.mastEnabled || checkLimits()) {
			//then finish the action
			//set the mast to remain in position
			robot.mast.set(ControlMode.PercentOutput, 0.1);
			clock.stop();
			robot.addMastDistance(mastEncoder.getDistance());
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
		public String getAction() {
			return "Time-based Mast";
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
