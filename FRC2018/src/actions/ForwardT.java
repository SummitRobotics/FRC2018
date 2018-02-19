package actions;

import org.usfirst.frc.team5468.robot.Hardware;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.Timer;
import templates.Action;

//go forward for t seconds
public class ForwardT extends Action{
	private double time;
	private double power = .5;
	private Timer clock;
	
	public ForwardT(Hardware r, double t) {
		super(r);
		time = t;
	}
	
	public ForwardT(Hardware r, double t, double p) {
		super(r);
		time = t;
		power = p;
	}

	@Override
	public void run() {
		if(!started) {
			clock = new Timer();
			clock.start();
		}
		started = true;
		if(!this.isInterrupted() && robot.driveEnabled) {
			robot.leftDrive.set(ControlMode.PercentOutput, power);
			robot.rightDrive.set(ControlMode.PercentOutput, power);
		}
		update();
	}

	@Override
	public void update() {
		if(clock.get() > time || this.isInterrupted() || !robot.driveEnabled) {
			finished = true;
			clock.stop();
			disablePower();
		}
	}
	
	private void disablePower() {
		if(robot.driveEnabled) {
			robot.leftDrive.set(ControlMode.PercentOutput, 0);
			robot.rightDrive.set(ControlMode.PercentOutput, 0);
		}
	}

	@Override
	public String getAction() {
		return "Forward by time";
	}
	
}