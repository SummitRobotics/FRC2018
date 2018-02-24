package actions;

import org.usfirst.frc.team5468.robot.Hardware;

import com.ctre.phoenix.motorcontrol.ControlMode;

import templates.Action;

public class AutoIntake extends Action{
	private double forwardPower = .4;
	private double minRotatePower = .3;
	private double sensitivity = .1;
	private double maxAngleError = 1;
	
	
	public AutoIntake(Hardware r) {
		super(r);
	}

	@Override
	public void run() {
		started = true;
		
		double steeringAdjust = 0;
		if(robot.lemonlightPresent) {
			if(robot.lemonlight.getTargetPresent()) {
				if(robot.lemonlight.getOffsetDegrees() > maxAngleError) {
					steeringAdjust = robot.lemonlight.getOffsetDegrees()*sensitivity + minRotatePower;
				}
				else if(robot.lemonlight.getOffsetDegrees() < -maxAngleError) {
					steeringAdjust = robot.lemonlight.getOffsetDegrees()*sensitivity - minRotatePower;
				}
				robot.leftDrive.set(ControlMode.PercentOutput, forwardPower - steeringAdjust);
				robot.rightDrive.set(ControlMode.PercentOutput, forwardPower + steeringAdjust);
			}
			else {
				robot.leftDrive.set(ControlMode.PercentOutput, -forwardPower);
				robot.rightDrive.set(ControlMode.PercentOutput, forwardPower);
			}
		}
		
	}

	@Override
	public String getAction() {
		return "Seeking action";
	}

	@Override
	public void update() {
	}

}
