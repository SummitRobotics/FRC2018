package actions;

import org.usfirst.frc.team5468.robot.Hardware;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Timer;
import templates.Action;

public class Ramp extends Action{
	//forward by time
	private double time;
	private double power = .5;
	
	//timer
	private Timer clock;
	
	public Ramp(Hardware r, double t) {
		super(r);
		time = t;
	}
	
	public Ramp(Hardware r, double t, double p){
		super(r);
		time = t;
		power = p;
	}

	@Override
	public void actionInit() {
		clock = new Timer();
		clock.start();
	}

	@Override
	public void actionPeriodic() {
		if(robot.winchEnabled){
			robot.winch.set(ControlMode.PercentOutput, power);
		}
	}

	@Override
	public boolean actionFinished() {
		if(time < clock.get() || !robot.winchEnabled) {
			clock.stop();
			if(robot.winchEnabled){
				robot.winch.set(ControlMode.PercentOutput, 0);
			}
			return true;
		}
		return false;
	}

	@Override
	public String getAction() {
		return "Ramp Deploy";
	}

}
