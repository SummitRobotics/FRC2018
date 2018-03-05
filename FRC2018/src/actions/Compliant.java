package actions;

import org.usfirst.frc.team5468.robot.Hardware;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Timer;
import templates.Action;

public class Compliant extends Action{
	//time for intake action
	private double time;
	//default power
	private double power = .8;
	//timer
	private Timer clock;
	
	//constructor
	public Compliant(Hardware r, double t, double p) {
		super(r);
		time = t;
		power = p;
	}

	@Override
	public void actionInit() {
		//start timer
		clock = new Timer();
		clock.start();
	}

	@Override
	public void actionPeriodic() {
		//if the intake is connected
		if(robot.intakeEnabled) {
			//set defined power
			robot.intake.set(ControlMode.PercentOutput, power);
		}
	}

	@Override
	public boolean actionFinished() {
		//if the duration completed or the intake is disconnected
		if(clock.get() > time || !robot.intakeEnabled) {
			//then finish the action
			clock.stop();
			disablePower();
			return true;
		}
		return false;
	}
	
	//disable motor output
	private void disablePower() {
		if(robot.driveEnabled) {
			robot.intake.set(ControlMode.PercentOutput, 0);
		}
	}
	
	@Override
	public String getAction() {
		return "Compliant Actuation";
	}
}
