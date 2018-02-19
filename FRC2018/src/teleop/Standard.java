package teleop;

import org.usfirst.frc.team5468.robot.Hardware;

import com.ctre.phoenix.motorcontrol.ControlMode;

import actions.Piston;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import templates.Driver;
import templates.TeleopProgram;

public class Standard extends TeleopProgram{
	//pneumatics
	private Piston clamp;
	private boolean lastClampState = false;
	private Piston extend;
	private boolean lastExtendState = false;
	
	//variables
	private double driveExponent;
	private double maxPower;
	private double steeringCoef;
	private double deadzone;
	
	
	public Standard(Hardware r) {
		super(r, "Standard");
		clamp = new Piston(r, r.clamp);
		extend = new Piston(r, r.extender);
	}

	@Override
	public void teleopInit() {
		driveExponent = robot.variables.getDriver().getDriveExponent();
		maxPower = robot.variables.getDriver().getMaxPower();
		steeringCoef = robot.variables.getDriver().getSteeringCoef();
		deadzone = robot.variables.getDriver().getThreshold();
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
		double forward = robot.controller.getThrottle() - robot.controller.getZ();
		double steering = robot.controller.getRawAxis(0) * steeringCoef;
		robot.leftDrive.set(ControlMode.PercentOutput, smoothPower((forward + steering)));
		robot.rightDrive.set(ControlMode.PercentOutput, smoothPower((forward - steering)));
	}
	
	//scaling, deadzone, curve shape, etc
	//the exponent function is applied to absolute values
	//to avoid sub 1 exponents on negative bases
	private double smoothPower(double x) {
		x = capPower(x);
		double sign = Math.signum(x);
		x = ((Math.abs(x) - deadzone)/(1 - deadzone)) * maxPower;
		return Math.pow(x, driveExponent)*sign;
	}
	
	private double capPower(double x) {
		if(Math.abs(x) < deadzone){
			return 0;
		}
		if(x < -1) {
			return -maxPower;
		}
		else if(x > 1) {
			return maxPower;
		}
		else {
			return x;
		}
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
	
	
	//**************//
	//
	//	Mast Imput:
	//	Analog and Preset
	//
	//**************//
	private void mast() {
		robot.mast.set(ControlMode.PercentOutput, capPower(robot.controller.getRawAxis(5)));
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
