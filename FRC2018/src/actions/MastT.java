package actions;

import org.usfirst.frc.team5468.robot.Hardware;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Timer;
import templates.Action;

public class MastT extends Action {
	private double time;
	private Timer clock;
	private double power = .5;
	
	//forward for t seconds with default power
	public MastT(Hardware r, double t) {
		super(r);
		time = t;
	}
	
	public MastT(Hardware r, double t, double p) {
		super(r);
		time = t;
		power = p;
	}

	@Override
	//go forward for a finite period of time
	public void run() {	
		if(!started) {
			clock = new Timer();
			clock.start();
		}
		started = true;
		if(robot.mastEnabled){
			robot.mast.set(ControlMode.PercentOutput, smoothPower(robot.mast.getMotorOutputPercent()));
		}
		update();
	}
	
	private double smoothPower(double point) {
		int n = 2;
		return ((point*n) + power)/(1+n);
	}

	@Override
	public void update() {
		if(clock.get() > time || !robot.mastEnabled) {
			robot.mast.set(ControlMode.PercentOutput, 0.1);
			clock.stop();
			finished = true;
		}
	}
	
	@Override
	public String getAction() {
		return "Time-based Mast";
	}

}
