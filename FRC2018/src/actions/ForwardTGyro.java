package actions;

import org.usfirst.frc.team5468.robot.Hardware;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Timer;
import templates.Action;

public class ForwardTGyro extends Action {
	
	private double angle;
	private double angleSensitivity = .1;	
	
	private double time;
	private double lateralPower;
	private Timer clock;
	
	public ForwardTGyro(Hardware r, double t, double p) {
		super(r);
		time = t;
		lateralPower = p;
		angle = robot.getRelativeYaw();
	}

	@Override
	public void run() {
		if(!started) {
			clock = new Timer();
			clock.start();
		}
		started = true;
		if(robot.driveEnabled && robot.gyroEnabled) {
			robot.leftDrive.set(ControlMode.PercentOutput, capPower(lateralPower + getCorrection()));
			robot.rightDrive.set(ControlMode.PercentOutput, capPower(lateralPower - getCorrection()));
		}
		update();
	}
	
	private double getCorrection() {
		double diff = angle - robot.getAbsoluteYaw();
		if(diff > 0) {
			return Math.min(1, diff*angleSensitivity);
		}
		else {
			return Math.max(-1, diff*angleSensitivity);
		}
	}
	
	private double capPower(double x) {
		if(x < -1) {
			return -1;
		}
		else if(x > 1) {
			return 1;
		}
		else {
			return x;
		}
	}

	@Override
	public String getAction() {
		return "Forward by Time and Gyro";
	}

	@Override
	public void update() {
		if(clock.get() > time || !robot.driveEnabled) {
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

}
