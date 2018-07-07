package teleop;

import java.util.ArrayList;
import org.usfirst.frc.team5468.robot.Hardware;
import com.ctre.phoenix.motorcontrol.ControlMode;
import actions.*;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import sequencing.Subsequence;
import templates.Action;
import templates.TeleopProgram;
import utilities.Leds;
import utilities.Xbox;

public class NewStandard extends TeleopProgram{
	//for doing mathy stuff
	private Utility functions = new Utility();
	private Xbox gamepad;
	private boolean inputConfigured = false;
	
	//controlling the clamp
	private Piston clampAction;
	
	//controlling the piston
	private Piston extenderAction;
	
	//master kill switch
	private boolean resetInterrupted = true;
	
	//shooting cubes
	private boolean ejectInterrupted = true;
	private Subsequence ejectAction;
	
	//vault sequence
	private boolean vaultInterrupted = true;
	private Subsequence vaultAction;
	
	//vision & drive functions
	private boolean driveDisabled = false;
	private Subsequence visionAction;
	
	//controlling the mast
	private boolean mastInterrupted = true;
	private MastT mastAction;
	
	//ramp deploy
	private boolean rampInterrupted = true;
	private Subsequence rampAction;
	
	private Leds neoPixels;
	
	//setting up toggles/subsequences
	public NewStandard(Hardware r) {
		super(r, "NewStandard");
		neoPixels = new Leds();
		gamepad = new Xbox(r.controller);
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
		//actions.add(new Piston(robot, robot.clamp));
		return new Subsequence(actions);
	}
	
	private Subsequence newRampSequence(){
		ArrayList<Action> actions = new ArrayList<Action>();
		//actions.add(new Piston(robot, robot.ramp));
		actions.add(new Ramp(robot, 10));
		//actions.add(new Piston(robot, robot.ramp));
		return new Subsequence(actions);
	}

	@Override
	public void teleopInit() {
		//zero the motion magic profile. It can be weird otherwise
		if(!driveDisabled){
			robot.leftDrive.set(ControlMode.MotionMagic, robot.leftDrive.getSelectedSensorPosition(0));
			robot.rightDrive.set(ControlMode.MotionMagic, robot.rightDrive.getSelectedSensorPosition(0));
		}
		//this prevents the need for double toggle at the beginning of teleop
		if(robot.pneumaticsEnabled){
			robot.extender.set(robot.extender.get());
			robot.clamp.set(robot.clamp.get());
		}
		//zero the encoders
		robot.leftDrive.setSelectedSensorPosition(0, 0, 0);
		robot.rightDrive.setSelectedSensorPosition(0, 0, 0);
		robot.leftDrive.set(ControlMode.MotionMagic, robot.leftDrive.getSelectedSensorPosition(0));
		robot.rightDrive.set(ControlMode.MotionMagic, robot.rightDrive.getSelectedSensorPosition(0));
		robot.leftDrive.set(ControlMode.PercentOutput, 0);
		robot.rightDrive.set(ControlMode.PercentOutput, 0);
	}

	@Override
	public void teleopPeriodic() {	
		SmartDashboard.putNumber("LEFT ENCODER:", robot.leftDrive.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("RIGHT ENCODER:", robot.rightDrive.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("LIMELIGHT AREA", robot.lemonlight.getArea());
		//SmartDashboard.putBoolean("HIGHER SWITCH", robot.higherMastSwitch.get());
		refreshKeys();
		//methods are organized by interrupts
		//drive functions
		vision();
		drive();
		//pneumatic controls
		clamp();
		extend();
		reset();
		intake();
		//eject();
		//vault();
		//controlling the mast
		mast();
		//ramp deploy
		//ramp();
	}
	
	private int n = 1;
	private void clamp() {
		if(gamepad.xIsToggled()) {
			neoPixels.input(n);
			n = 1+((n + 1) % 3);
			ejectInterrupted = true;
			vaultInterrupted = true;
			resetInterrupted = true;
			clampAction.run();
		}
	}
	
	//extend the intake
	private void extend() {
		if(gamepad.aIsToggled()) {
			ejectInterrupted = true;
			vaultInterrupted = true;
			resetInterrupted = true;
			extenderAction.run();
		}
	}
	
	private void intake() {
		if(robot.intakeEnabled) {
			double intakePower = 0;
			if(gamepad.leftBumper()) {
				intakePower -= 1;
			}
			else if(gamepad.rightBumper()) {
				intakePower += 1;
			}
			robot.intake.set(ControlMode.PercentOutput, intakePower);
		}
	}
	
	//**************//
	//
	//	Various functionality
	//
	//**************//
	private void reset() {
		if(gamepad.bIsToggled()) {
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
	/*private void eject() {
		if(gamepad.leftBumperIsToggled()) {
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
	}*/
	
	//rocket league drive controls
	//is overriden by vision controls
	//the only manual funciton overriden by auto action
	private double lastLeftPower = 0;
	private double lastRightPower = 0;
	private void drive() {
		if(!driveDisabled) {
			double forwardsPower = functions.toExponential(functions.deadzone(gamepad.getRightTrigger() - gamepad.getLeftTrigger(), 0.2), 2.3)*0.85;
			double turningPower = functions.toExponential(functions.deadzone(gamepad.leftJoystickX(), 0.2), 2.3)*0.9;
			
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
		if(gamepad.y()) {
			return Math.max(robot.variables.getMinRotatePower(), .3);
		}
		else {
			return .7;
		}
	}
	
	//for auto intake
	private void vision() {
		if(gamepad.leftDPAD()) {
			if(gamepad.leftDPADIsToggled()) {
				visionAction = newVisionSequence();
			}	
			visionAction.run();
			driveDisabled = true;
		}
		else {
			driveDisabled = false;
		}
	}
	
	//1 = up
	//2 = down
	//like anything else idk 6 = static aka not lifting boomerino
	
	//for manipulating the mast
	private void mast() {
		if(functions.deadzone(gamepad.rightJoystickY(), .3) != 0) {
			mastInterrupted = true;
			//SmartDashboard.putNumber("OUTPUT:", functions.deadzone(gamepad.rightJoystickY(), .3));
			mastAction.setPower(functions.clamp(gamepad.rightJoystickY(), -.7,1));
			if(functions.clamp(gamepad.rightJoystickY(), -.7,1) >= 0){
				//SmartDashboard.putNumber("Led Mode", 1);
			}
			else{
				//SmartDashboard.putNumber("Led Mode", 2);
			}
			mastAction.run();
			return;
		}
		else if(gamepad.lowerDPADIsToggled()) {
			mastInterrupted = false;
			mastAction.setPower(-.3);
			mastAction.run();
		}
		else if(gamepad.upperDPADIsToggled()) {
			mastInterrupted = false;
			mastAction.setPower(.5);
			mastAction.run();
		}
		else {
			if(!mastInterrupted) {
				mastAction.run();
			}
			else {
				//SmartDashboard.putNumber("Led Mode", 6);
				mastAction.setPower(robot.variables.getMinimumMastPower());
				mastAction.run();
			}
		}
	}
	
	/*private void vault() {
		if(gamepad.rightBumperIsToggled()) {
			resetInterrupted = true;
			ejectInterrupted = true;
			vaultInterrupted = false;
			vaultAction = newVaultSequence();
			vaultAction.run();
		}
		if(!vaultInterrupted) {
			vaultAction.run();
		}
	}*/
	
	/*private int iteration = 0;
	private void ramp() {
		if(gamepad.backIsToggled()){
			if(iteration % 2 == 0){
				rampAction = newRampSequence();
				rampInterrupted = false;
				SmartDashboard.putString("RAMP", "0");
			}
			else if(iteration % 2 == 1){
				robot.ramp.set(DoubleSolenoid.Value.kReverse);
				robot.ramp.set(DoubleSolenoid.Value.kReverse);
				SmartDashboard.putString("RAMP", "1");
			}
			iteration++;
		}
		else {
			if(robot.winchEnabled && rampInterrupted) {
				robot.winch.set(ControlMode.PercentOutput, robot.variables.getWinchMinPower());
			}
		}
		//run auto actions
		if(!rampInterrupted) {
			rampAction.run();
		}
		//manual winch control
		if(gamepad.start()){
			robot.ramp.set(DoubleSolenoid.Value.kReverse);
			robot.winch.set(ControlMode.PercentOutput, -.5);
			rampInterrupted = true;
		}
	}*/

	@Override
	public void teleopDisabledInit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void teleopDisabledPeriodic() {
		// TODO Auto-generated method stub
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
