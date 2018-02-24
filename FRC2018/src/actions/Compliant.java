package actions;

import org.usfirst.frc.team5468.robot.Hardware;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Timer;
import templates.Action;

public class Compliant extends Action{
	private double time;
	private double power = .8;
	private Timer clock;
	
	
	public Compliant(Hardware r, double t, double p) {
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
		if(robot.intakeEnabled) {
			robot.intake.set(ControlMode.PercentOutput, power);
		}
		update();
	}

	@Override
	public String getAction() {
		return "Compliant Actuation";
	}

	@Override
	public void update() {
		if(clock.get() > time || !robot.intakeEnabled) {
			finished = true;
			clock.stop();
			disablePower();
		}
	}

	private void disablePower() {
		if(robot.driveEnabled) {
			robot.intake.set(ControlMode.PercentOutput, 0);
		}
	}
}
