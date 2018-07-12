package actions;

import org.usfirst.frc.team5468.robot.Hardware;

import com.ctre.phoenix.motorcontrol.ControlMode;

import templates.Action;

public class CompliantToggle extends Action {

	private double power;
	
	public CompliantToggle(Hardware r, double p) {
		super(r);
		power = p;
	}

	@Override
	public void actionInit() {
		if(robot.intake.getMotorOutputPercent() != 0){
			power = 0;
		}
	}

	@Override
	public void actionPeriodic() {
		if(robot.intakeEnabled){
			robot.intake.set(ControlMode.PercentOutput, power);
		}
	}

	@Override
	public boolean actionFinished() {
		return true;
	}

	@Override
	public String getAction() {
		return "Compliant Toggle";
	}

}
