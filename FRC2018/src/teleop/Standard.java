package teleop;

import org.usfirst.frc.team5468.robot.Hardware;

import com.ctre.phoenix.motorcontrol.ControlMode;

import actions.AutoIntake;
import actions.Piston;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import regulation.PID;
import templates.TeleopProgram;
import utilities.Variables;

public class Standard extends TeleopProgram{
	//pneumatics
	private Piston clamp;
	private boolean lastClampState = false;
	private Piston extend;
	private boolean lastExtendState = false;
	
	//auto intake
	private AutoIntake collect;
	
	//variables
	protected double lateralExponent;
	protected double rotateSensitivity;
	protected double maxPower;
	protected double threshold;
	protected double joystickError;
	
	
	public Standard(Hardware r) {
		super(r, "Standard");
		clamp = new Piston(r, r.clamp);
		extend = new Piston(r, r.extender);
	}

	@Override
	public void teleopInit() {
		lateralExponent = robot.variables.getDriver().getLateralExponent();
		maxPower = robot.variables.getDriver().getMaxPower();
		rotateSensitivity = robot.variables.getDriver().getRotationSensitivity();
		threshold = robot.variables.getDriver().getThreshold();
		joystickError = robot.variables.getDriver().getJoystickError();
		collect = new AutoIntake(robot);
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
			}
			if(robot.intakeEnabled) {
				intake();
			}
			if(robot.mastEnabled) {
				mast();
			}
			if(robot.lemonlightPresent) {
				autoCollect();
			}
		}
		if(robot.controller.getRawButton(4)) {
			robot.leftDrive.setSelectedSensorPosition(0, Variables.kPIDLoopIdx, Variables.delay);
			robot.rightDrive.setSelectedSensorPosition(0, Variables.kPIDLoopIdx, Variables.delay);
		}
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
	
	//**************//
	//
	//	Extender Toggle : A	
	//
	//**************//
	private void actuateExtender() {
		if(detectChangeExtender()) {
			extend.run();
			/*
			 * if(extend == out){
			 * intake.set(-)
			 * else{
			 * intake.set(0)
			 */
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
		double forwardsPower = toExponential(deadzone(robot.controller.getThrottle() - robot.controller.getZ(), 0.2), 2.3);
		double turningPower = (toExponential(deadzone(robot.controller.getX(Hand.kLeft), 0.2), 2.3))*0.8;
		
		double leftPower = clamp(forwardsPower+turningPower, -1, 1);
		double rightPower = -clamp(forwardsPower-turningPower, -1, 1);
				
		robot.leftDrive.set(ControlMode.PercentOutput, leftPower);
		robot.rightDrive.set(ControlMode.PercentOutput, -rightPower);
	}
	
	public static double toExponential(double value, double exponent)
	{
		value = Math.pow(Math.abs(value), exponent) * Math.signum(value);
		
		return value;
}
	
	public static double deadzone(double joystickValue, double deadzone)
	{
		//if the joystickValue falls within the range of the deadzone...
		if (Math.abs(joystickValue) < deadzone)
		{
			//Set the joystick value to 0
			joystickValue = 0;
		}
		
		return joystickValue;
}
	
	public static double clamp(double value, double min, double max)
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
		double power = 1;
		double autoOutput = 0;
		double controllerOutput = 0;
		
		//controller output
		if(robot.controller.getRawButton(5)) {
			controllerOutput += power;
		}
		
		if(robot.controller.getRawButton(6)) {
			controllerOutput -= power;
		}
		
		//pneumatic state
		if(robot.pneumaticsEnabled) {
			if(robot.clamp.get() == DoubleSolenoid.Value.kReverse) {
				//if(!compression detected): off
				autoOutput -= power;		
			}
			else {
				autoOutput = 0;
			}
		}
		
		if(!(controllerOutput == 0)) {
			robot.intake.set(ControlMode.PercentOutput, controllerOutput);
		}
		else {
			robot.intake.set(ControlMode.PercentOutput, autoOutput);
		}
	}
	
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
		robot.mast.set(ControlMode.PercentOutput, deadzone(robot.controller.getRawAxis(5), joystickError));
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
