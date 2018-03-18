package teleop;

import java.util.ArrayList;
import org.usfirst.frc.team5468.robot.Hardware;

import com.ctre.phoenix.motorcontrol.ControlMode;

import actions.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import sequencing.Subsequence;
import templates.Action;
import templates.TeleopProgram;

public class NewStandard extends TeleopProgram{
	//for doing mathy stuff
	private Utility functions = new Utility();
	private boolean inputConfigured = false;
	
	//controlling the clamp
	private Piston clampAction;
	private Button clampKey;
	
	//controlling the piston
	private Piston extenderAction;
	private Button extenderKey;
	
	//master kill switch
	private boolean resetInterrupted = true;
	private Button resetKey;
	
	//shooting cubes
	private boolean ejectInterrupted = true;
	private Subsequence ejectAction;
	private Button ejectKey;
	
	//vault sequence
	private boolean vaultInterrupted = true;
	private Subsequence vaultAction;
	private Button vaultKey;
	
	//vision & drive functions
	private boolean driveDisabled = false;
	private Subsequence visionAction;
	private Button slowKey;
	private DPAD visionKey;
	
	//controlling the mast
	private boolean mastInterrupted = true;
	private MastT mastAction;
	private Joy mastKey;
	
	//ramp deploy
	private boolean rampInterrupted = true;
	private Subsequence rampAction;
	private Button[] rampKeys = new Button[2];
	
	private DPAD lowerMastKey;
	private DPAD higherMastKey;
	
	//setting up toggles/subsequences
	public NewStandard(Hardware r) {
		super(r, "NewStandard");
		clampAction = new Piston(r, robot.clamp);
		extenderAction = new Piston(r, robot.extender);
		mastAction = new MastT(robot, 0);
		ejectAction = newEjectSequence();
		visionAction = newVisionSequence();
		vaultAction = newVaultSequence();
		rampAction = newRampSequence();
		refreshKeys();
	}
	
	private void refreshKeys() {
		if(robot.controllerPresent && !inputConfigured){
			vaultKey = new Button(robot.controller, 5);
			clampKey = new Button(robot.controller, 3);
			extenderKey = new Button(robot.controller, 1);
			resetKey = new Button(robot.controller, 2);
			ejectKey = new Button(robot.controller, 6);
			visionKey = new DPAD(robot.controller, 270);
			slowKey = new Button(robot.controller, 4);
			mastKey = new Joy(robot.controller, 5);
			lowerMastKey = new DPAD(robot.controller, 180);
			higherMastKey = new DPAD(robot.controller, 0);
			rampKeys[0] = new Button(robot.controller, 7);
			rampKeys[1] = new Button(robot.controller, 8);
			inputConfigured = true;
		}
		else if(!robot.controllerPresent){
			inputConfigured = false;
		}
	}
	
	//**************//
	//
	//	Init subsequence actions
	//
	//**************//
	private Subsequence newEjectSequence() {
		ArrayList<Action> actions = new ArrayList<Action>();
		actions.add(new Delay(robot, .4));
		actions.add(new Piston(robot, robot.clamp));
		return new Subsequence(actions);
	}
	
	private Subsequence newVaultSequence() {
		ArrayList<Action> actions = new ArrayList<Action>();
		actions.add(new Piston(robot, robot.extender));
		actions.add(new Piston(robot, robot.clamp));
		actions.add(new Piston(robot, robot.extender));
		return new Subsequence(actions);
	}
	
	private Subsequence newVisionSequence() {
		ArrayList<Action> actions = new ArrayList<Action>();
		actions.add(new AutoIntake(robot));
		actions.add(new Piston(robot, robot.clamp));
		return new Subsequence(actions);
	}
	
	private Subsequence newRampSequence(){
		ArrayList<Action> actions = new ArrayList<Action>();
		actions.add(new Piston(robot, robot.ramp));
		actions.add(new Ramp(robot, 20));
		actions.add(new Piston(robot, robot.ramp));
		return new Subsequence(actions);
	}

	@Override
	public void teleopInit() {
		if(!driveDisabled){
			robot.leftDrive.set(ControlMode.MotionMagic, robot.leftDrive.getSelectedSensorPosition(0));
			robot.rightDrive.set(ControlMode.MotionMagic, robot.rightDrive.getSelectedSensorPosition(0));
		}
		if(robot.pneumaticsEnabled){
			robot.ramp.set(DoubleSolenoid.Value.kReverse);
			robot.ramp.set(DoubleSolenoid.Value.kReverse);
		}
	}

	@Override
	public void teleopPeriodic() {	
		SmartDashboard.putBoolean("Vault Interrupted:", vaultInterrupted);
		SmartDashboard.putBoolean("Drive Interrupted:", driveDisabled);
		SmartDashboard.putBoolean("Mast Interrupted:", mastInterrupted);
		SmartDashboard.putBoolean("Eject Interrupted", ejectInterrupted);
		SmartDashboard.putBoolean("Reset Interrupted:", resetInterrupted);
		
		refreshKeys();
		//methods are organized by interrupts
		//drive functions
		//vision();
		drive();
		//pneumatic controls
		clamp();
		extend();
		reset();
		eject();
		vault();
		//controlling the mast
		mast();
		//ramp deploy
		ramp();
	}
	
	//clamp the intake
	private void clamp() {
		if(clampKey.toggled()) {
			ejectInterrupted = true;
			vaultInterrupted = true;
			resetInterrupted = true;
			clampAction.run();
		}
	}
	
	//extend the intake
	private void extend() {
		if(extenderKey.toggled()) {
			ejectInterrupted = true;
			vaultInterrupted = true;
			resetInterrupted = true;
			extenderAction.run();
		}
	}
	
	//**************//
	//
	//	Various functionality
	//
	//**************//
	private void reset() {
		if(resetKey.toggled()) {
			resetInterrupted = false;
			ejectInterrupted = true;
			vaultInterrupted = true;
		}
		if(!resetInterrupted) {
			actuateReset();
		}
		resetInterrupted = true;
	}
	
	//sets the intake to an inward position
	private void actuateReset() {
		if(robot.pneumaticsEnabled) {
			robot.clamp.set(DoubleSolenoid.Value.kReverse);
			robot.extender.set(DoubleSolenoid.Value.kReverse);
		}
	}
	
	//actuates the cube into the switch or scale
	private void eject() {
		if(ejectKey.toggled()) {
			resetInterrupted = true;
			ejectInterrupted = false;
			vaultInterrupted = true;
			ejectAction = newEjectSequence();
		}
		if(!ejectInterrupted) {
			if(robot.pneumaticsEnabled) {
				robot.extender.set(DoubleSolenoid.Value.kForward);
			}
			ejectAction.run();
		}
	}
	
	//rocket league drive controls
	//is overriden by vision controls
	//the only manual funciton overriden by auto action
	private double lastLeftPower = 0;
	private double lastRightPower = 0;
	private void drive() {
		if(!driveDisabled) {
			double forwardsPower = functions.toExponential(functions.deadzone(robot.controller.getThrottle() - robot.controller.getZ(), 0.2), 2.3)*0.85;
			double turningPower = functions.toExponential(functions.deadzone(robot.controller.getX(Hand.kLeft), 0.2), 2.3)*0.9;
			
			double leftPower = functions.clamp(functions.deadzone(forwardsPower,.1)+turningPower, -1, 1);
			double rightPower = functions.clamp(functions.deadzone(forwardsPower,.1)-turningPower, -1, 1);
			
			if(leftPower != lastLeftPower || rightPower != lastRightPower) {
				robot.leftDrive.set(ControlMode.PercentOutput, leftPower*maxDrivePower());
				robot.rightDrive.set(ControlMode.PercentOutput, rightPower*maxDrivePower());
				lastLeftPower = leftPower;
				lastRightPower = rightPower;
				//insert interrupts here
			}
		}
	}
	
	//for manually thresholding drivetrain power
	private double maxDrivePower() {
		if(slowKey.getStatus()) {
			return Math.max(robot.variables.getMinRotatePower(), .3);
		}
		else {
			return .7;
		}
	}
	
	//for auto intake
	private void vision() {
		if(visionKey.getStatus()) {
			if(visionKey.isDPADToggled()) {
				visionAction = newVisionSequence();
				actuateReset();
			}	
			visionAction.run();
			driveDisabled = true;
		}
		else {
			driveDisabled = false;
		}
	}
	
	//for manipulating the mast
	private void mast() {
		if(functions.deadzone(robot.controller.getRawAxis(5), .3) != 0) {
			mastInterrupted = true;
			mastAction.setPower(functions.clamp(-mastKey.getValue(), -.7,1));
			mastAction.run();
		}
		else if(lowerMastKey.isDPADToggled()) {
			mastInterrupted = false;
			mastAction.setPower(-.3);
			mastAction.run();
		}
		else if(higherMastKey.isDPADToggled()) {
			mastInterrupted = false;
			mastAction.setPower(.5);
			mastAction.run();
		}
		else {
			if(!mastInterrupted) {
				mastAction.run();
			}
			else {
				mastAction.setPower(robot.variables.getMinimumMastPower());
				mastAction.run();
			}
		}
	}
	
	private void vault() {
		if(vaultKey.toggled()) {
			resetInterrupted = true;
			ejectInterrupted = true;
			vaultInterrupted = false;
			vaultAction = newVaultSequence();
			vaultAction.run();
		}
		if(!vaultInterrupted) {
			vaultAction.run();
		}
	}
	
	private void ramp() {
		if(rampKeys[0].toggled()){
			rampAction = newRampSequence();
			rampInterrupted = !rampInterrupted;
		}
		else {
			if(robot.winchEnabled) {
				robot.winch.set(ControlMode.PercentOutput, robot.variables.getWinchMinPower());
			}
		}
		if(!rampInterrupted) {
			rampAction.run();
		}else{
			if(robot.controller.getRawButton(8)){
				robot.winch.set(ControlMode.PercentOutput, -.5);
			}
			else{
				robot.winch.set(ControlMode.PercentOutput, 0);
			}
		}
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

class Button {
	private Joystick controller;
	private boolean prevState;
	private int index;
	
	public Button(Joystick con, int i) {
		controller = con;
		index = i;
		prevState = true;
	}
	
	public boolean toggled(){
		try{
			if(controller.getRawButton(index) != prevState) {
				if(controller.getRawButton(index) == true) {
					prevState = true;
					SmartDashboard.putBoolean("TOGGLED:", controller.getRawButton(index));
					return true;
				}
				else {
					prevState = false;
					SmartDashboard.putBoolean("TOGGLED:", controller.getRawButton(index));
					return false;
				}
			}
			else {
				return false;
			}
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean getStatus() {
		try{
			return controller.getRawButton(index);
		}catch(Exception e){
			return false;
		}
	}
}

class Joy{
	private Joystick controller;
	private double prevPos;
	private int index;
	
	public Joy(Joystick con, int i) {
		controller = con;
		index = i;
	}
	
	public boolean isOverriden() {
		try{
			if(prevPos != controller.getRawAxis(index)) {
				return true;
			}
			else {
				return false;
			}
		}catch(Exception e){
			return false;
		}
	}
	
	public double getValue() {
		try{
			return controller.getRawAxis(index);
		}catch(Exception e){
			return 0;
		}
	}
}

class DPAD{
	private Joystick controller;
	private boolean prevState;
	private int val;
	
	public DPAD(Joystick con, int v) {
		controller = con;
		val = v;
		if(val == con.getPOV()) {
			prevState = true;
		}
		else {
			prevState = false;
		}
	}
	
	public boolean isDPADToggled() {
		try{
			if(!prevState) {
				if(controller.getPOV() == val) {
					prevState = true;
					return true;
				}
				else {
					prevState = false;
					return false;
				}
			}
			else {
				if(controller.getPOV() == val) {
					prevState = true;
					return false;
				}
				else {
					prevState = false;
					return false;
				}
			}
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean getStatus() {
		try{
			if(controller.getPOV() == val) {
				return true;
			}
			else {
				return false;
			}
		}catch(Exception e){
			return false;
		}
	}
}

class Utility{
	public double toExponential(double value, double exponent){
		value = Math.pow(Math.abs(value), exponent) * Math.signum(value);
		return value;
	}
	
	public double deadzone(double joystickValue, double deadzone){
		if (Math.abs(joystickValue) < deadzone){
			joystickValue = 0;
		}
		return joystickValue;
	}
	
	public double clamp(double value, double min, double max){
		if(value < min){
			value = min;
		}
		if(value > max){
			value = max;
		}
		return value;
	}
}
