package teleop;

import org.usfirst.frc.team5468.robot.Hardware;

import com.ctre.phoenix.motorcontrol.ControlMode;

import actions.AutoIntake;
import actions.*;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import functions.MotorControl;
import templates.TeleopProgram;

public class Standard extends TeleopProgram{
	//pneumatics
	private Piston clamp;
	private boolean lastClampState = false;
	private Piston extend;
	private boolean lastExtendState = false;
	
	//auto intake
	private AutoIntake collect;
	
	//mast actions
	private MastD automaticMast;
	private MastT manualMast;
	
	//joystick error
	private double joystickError = .3;
	
	public Standard(Hardware r) {
		super(r, "Standard");
		clamp = new Piston(r, r.clamp);
		extend = new Piston(r, r.extender);
	}

	@Override
	public void teleopInit() {
		collect = new AutoIntake(robot);
		automaticMast = new MastD(robot, 0);
	}

	@Override
	public void teleopPeriodic() {
		if(robot.controllerPresent) {
			if(robot.driveEnabled) {
				rocketDrive();
			}
			if(robot.pneumaticsEnabled) {
				actuateClamp();
				actuateExtender();
				//release();
			}
			if(robot.intakeEnabled) {
				intake();
			}
			if(robot.mastEnabled && robot.hallPresent) {
				mast();
			}
			if(robot.lemonlightPresent) {
				//autoCollect();
			}
		}
		SmartDashboard.putNumber("Mast Encoder:", robot.mastEncoder.get());
		SmartDashboard.putBoolean("Bottom:", robot.lowerMastSwitch.get());
		SmartDashboard.putBoolean("Top:", robot.higherMastSwitch.get());
	}
	
	//**************//
	//
	//	Clamp Toggle : X		
	//
	//**************//
	private void actuateClamp() {
		if(detectChangeClamp()) {
			clamp.run();
		}
	}

	private boolean detectChangeClamp() {
		if(robot.controller.getRawButton(3) == lastClampState) {
			return false;
		}
		else {
			//this prevents the release of the butto
			//from reversing the action
			if(lastClampState == true) {
				lastClampState = false;
				return false;
			}
			else {
				lastClampState = robot.controller.getRawButton(3);
				return true;
			}
		}
	}
	
	private void release() {
		if(robot.controller.getRawButton(2)) {
			robot.extender.set(DoubleSolenoid.Value.kReverse);
			robot.clamp.set(DoubleSolenoid.Value.kForward);
		}
	}
		
	private void actuateExtender() {
		if(detectChangeExtender()) {
			extend.run();
		}
	}
	
	private boolean detectChangeExtender() {
		if(robot.controller.getRawButton(1) == lastExtendState) {
			return false;
		}
		else {
			//this prevents the release of the butto
			//from reversing the action
			if(lastExtendState == true) {
				lastExtendState = false;
				return false;
			}
			else {
				lastExtendState = robot.controller.getRawButton(1);
				return true;
			}
		}
	}
	
	//**************//
	//
	//	Rocket League Drive : Triggers and Left Joystick X Axis	
	//
	//**************//
	private void rocketDrive() {	
		double forwardsPower = (toExponential(deadzone(robot.controller.getThrottle() - robot.controller.getZ(), 0.2), 2.3))*0.85;
		double turningPower = (toExponential(deadzone(robot.controller.getX(Hand.kLeft), 0.2), 2.3))*0.9;
		
		double leftPower = clamp(deadzone(forwardsPower,.1)+turningPower, -1, 1);
		double rightPower = clamp(deadzone(forwardsPower,.1)-turningPower, -1, 1);
				
		robot.leftDrive.set(ControlMode.PercentOutput, leftPower*maxPower());
		robot.rightDrive.set(ControlMode.PercentOutput, rightPower*maxPower());
	}
	
	private double maxPower(){
		return (robot.variables.getMastRange()[1] - robot.mastEncoder.getDistance())/robot.variables.getMastRange()[1];
	}
	
	public double toExponential(double value, double exponent)
	{
		value = Math.pow(Math.abs(value), exponent) * Math.signum(value);
		
		return value;
}
	
	public double deadzone(double joystickValue, double deadzone)
	{
		//if the joystickValue falls within the range of the deadzone...
		if (Math.abs(joystickValue) < deadzone)
		{
			//Set the joystick value to 0
			joystickValue = 0;
		}
		
		return joystickValue;
}
	
	public double clamp(double value, double min, double max)
	{
		//Clamp the value to not be lower than the minimum value
		if(value < min)
		{
			value = min;
		}
		
		//Clamp the value to not be greater than the maximum value
		if(value > max)
		{
			value = max;
		}
		
		return value;
	}
	
	//**************//
	//
	//	Intake : Left & Right Bumpers
	//
	//**************//
	private void intake() {
		double power = .7;
		double controllerOutput = 0;
		
		//controller output
		if(robot.controller.getRawButton(5)) {
			controllerOutput += power;
		}
		
		if(robot.controller.getRawButton(6)) {
			controllerOutput -= power;
		}
		
		robot.winch.set(ControlMode.PercentOutput, controllerOutput);
	}
	
	@SuppressWarnings("unused")
	private void autoCollect() {
		if(robot.controller.getRawButton(2)) {
			collect.run();
		}
	}
	
	
	//**************//
	//
	//	Mast Imput:
	//	Analog and Preset
	//
	//**************//
	private void mast() {
		robot.mast.set(ControlMode.PercentOutput, -robot.controller.getRawAxis(5));
	}

	@Override
	public void teleopDisabledInit() {
		// TODO Auto-generated method stub
	}

	@Override
	public void teleopDisabledPeriodic() {
		// TODO Auto-generated method stub
		
	}

}
