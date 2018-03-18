package org.usfirst.frc.team5468.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import utilities.Variables;
import utilities.Vision;

public class Hardware {
	//drive train
	public TalonSRX leftDrive;
	public TalonSRX rightDrive;
	private VictorSPX leftFollower;
	private VictorSPX rightFollower;
	
	//intake complaint wheels
	public VictorSPX intake;
	private VictorSPX intake2;
	
	//pneumatics
	public Compressor compressor;
	public DoubleSolenoid clamp;
	public DoubleSolenoid extender;
	public DoubleSolenoid ramp;
	
	//carriage lift
	public TalonSRX mast;
	
	//winch
	public VictorSPX winch;
	
	//gamepad1
	public Joystick controller;
	
	//relevant info
	public Variables variables;
	
	//gyro
	private ADXRS450_Gyro gyro;
	private double relativeAngle;
	
	//hall effect encoder for the mast
	public Counter mastEncoder;
	
	//limit switches
	public DigitalInput intakeSwitch;
	public DigitalInput lowerMastSwitch;
	public DigitalInput higherMastSwitch;
	
	//the limelight camera
	public Vision lemonlight;
	
	//Enabling all code to function
	//despite non-available components
	public boolean driveEnabled;
	public boolean pneumaticsEnabled;
	public boolean intakeEnabled;
	public boolean mastEnabled;
	public boolean gyroEnabled;
	public boolean controllerPresent;
	public boolean hallPresent;
	public boolean lemonlightPresent;
	public boolean limitSwitchPresent;
	public boolean winchEnabled;
	
	//**************//
	//
	//	Configuration of components
	//
	//**************//
	public Hardware() {
		//A = polybot, B = Our actual robot
		variables = new Variables("B");
		initGyro();
		initCounter();
		initGamepad();
		initDrive();
		initPneumatics();
		initIntake();
		initMast();
		initLimitSwitch();
	}
	
	//**************//
	//
	//	Live updating of current components
	//	If a component is disconnected/reconnected
	//  Its status will be accounted for with minimal delay
	//
	//**************//
	public void refreshConfig() {
		if(!gyroEnabled) {
			initGyro();
		}else {
			refreshGyro();
		}
		if(!hallPresent) {
			initCounter();
		}else {
			refreshCounter();
		}
		if(!controllerPresent) {
			initGamepad();
		}else {
			refreshGamepad();
		}
		if(!driveEnabled) {
			initDrive();
		}else {
			refreshDrive();
		}
		if(!pneumaticsEnabled) {
			initPneumatics();
		}else {
			refreshPneumatics();
		}
		if(!intakeEnabled) {
			initIntake();
		}else {
			refreshIntake();
		}
		if(!mastEnabled) {
			initMast();
		}else {
			refreshMast();
		}
		if(!lemonlightPresent) {
			initVision();
		}else {
			refreshVision();
		}
		if(!limitSwitchPresent) {
			initLimitSwitch();
		}
		else {
			refreshLimit();
		}
		if(!winchEnabled) {
			initWinch();
		}
		else {
			refreshWinch();
		}
	}
	
	//**************//
	//
	//	Setup sensors:
	//	Hall Effect Sensors and gyro
	//
	//**************//
	private void initGyro() {
		try {
			gyro = new ADXRS450_Gyro();
			gyroEnabled = true;
		}catch(Exception e) {
			gyroEnabled = false;
		}
	}
	
	private void refreshGyro() {
		try {
			gyro.getAngle();
			gyroEnabled = true;
		}catch(Exception e) {
			gyroEnabled = false;
		}
	}
	
	//adjust distance per pulse
	private void initCounter() {
		try {
			mastEncoder = new Counter(variables.getMastSensorId());
			mastEncoder.setDistancePerPulse(variables.getDistancePerPulse());
			mastEncoder.reset();
			hallPresent = true;
		}catch(Exception e) {
			hallPresent = false;
		}
	}
	
	private void refreshCounter() {
		try {
			mastEncoder.get();
			hallPresent = true;
		}catch(Exception e) {
			hallPresent = false;
		}
	}
	
	private void initLimitSwitch() {
		try {
			//intakeSwitch = new DigitalInput(variables.getIntakeSwitchId());
			lowerMastSwitch = new DigitalInput(variables.getLowerSwitchId());
			higherMastSwitch = new DigitalInput(variables.getHigherSwitchId());
			limitSwitchPresent = true;
		}catch(Exception e) {
			limitSwitchPresent = false;
		}
	}
	
	private void refreshLimit() {
		try {
			lowerMastSwitch.get();
			higherMastSwitch.get();
			limitSwitchPresent = true;
		}catch(Exception e) {
			limitSwitchPresent = false;
		}
	}
	
	private void initGamepad() {
		try {
			controller = new Joystick(0);
			controllerPresent = true;
		}catch(Exception e) {
			controllerPresent = false;
		}
	}
	
	private void refreshGamepad() {
		try {
			controller.getRawAxis(0);
			controllerPresent = true;
		}catch(Exception e) {
			controllerPresent = false;
		}
	}
	
	private void initVision() {
		try {
			lemonlight = new Vision();
			lemonlightPresent = true;
		}catch(Exception e) {
			lemonlightPresent = false;
		}
	}
	
	private void refreshVision() {
		try {
			lemonlight.getArea();
			lemonlightPresent = true;
		}catch(Exception e) {
			lemonlightPresent = false;
		}
	}
	
	public void zeroYaw() {
		if(gyroEnabled) {
			relativeAngle = gyro.getAngle();
		}
	}
	
	public double getRelativeYaw() {
		if(gyroEnabled) {
			return gyro.getAngle() - relativeAngle;
		}
		return Double.NaN;
	}
	
	public double getAbsoluteYaw() {
		if(gyroEnabled) {
			return gyro.getAngle();
		}
		return Double.NaN;
	}
	
	//**************//
	//
	//	Setup and configure drive train
	//
	//**************//
	private void initDrive() {
		try {
			leftDrive = new TalonSRX(variables.getLeftIds()[0]);
			leftFollower = new VictorSPX(variables.getLeftIds()[1]);
			rightDrive = new TalonSRX(variables.getRightIds()[0]);
			rightFollower = new VictorSPX(variables.getRightIds()[1]);
			configDrive();
			driveEnabled = true;
			
		}catch(Exception e) {
			driveEnabled = false;
		}
	}
	
	private void refreshDrive() {
		try {
			leftDrive.getMotorOutputPercent();
			rightDrive.getMotorOutputPercent();
			leftFollower.getMotorOutputPercent();
			rightFollower.getMotorOutputPercent();
			driveEnabled = true;
		}catch(Exception e) {
			driveEnabled = false;
		}
	}
	
	private void configDrive() {
		leftDrive.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, Variables.kPIDLoopIdx, Variables.delay);
		rightDrive.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, Variables.kPIDLoopIdx, Variables.delay);
	
		leftFollower.setInverted(variables.getLeftPolarityD());
		leftDrive.setInverted(variables.getLeftPolarityD());
		rightFollower.setInverted(variables.getRightPolarityD());
		rightDrive.setInverted(variables.getRightPolarityD());
		
		leftDrive.setSensorPhase(variables.getLSensorPhase());
		rightDrive.setSensorPhase(variables.getRSensorPhase());
		
		leftFollower.follow(leftDrive);
		rightFollower.follow(rightDrive);

		/* Set relevant frame periods to be at least as fast as periodic rate */
		leftDrive.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, Variables.delay);
		leftDrive.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, Variables.delay);
		rightDrive.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, Variables.delay);
		rightDrive.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, Variables.delay);

		/* set the peak and nominal outputs */
		leftDrive.configNominalOutputForward(0, Variables.delay);
		leftDrive.configNominalOutputReverse(0, Variables.delay);
		leftDrive.configPeakOutputForward(1, Variables.delay);
		leftDrive.configPeakOutputReverse(-1, Variables.delay);
		
		rightDrive.configNominalOutputForward(0, Variables.delay);
		rightDrive.configNominalOutputReverse(0, Variables.delay);
		rightDrive.configPeakOutputForward(1, Variables.delay);
		rightDrive.configPeakOutputReverse(-1, Variables.delay);

		/* set closed loop gains in slot0 - see documentation */
		leftDrive.selectProfileSlot(Variables.kSlotIdx, Variables.kPIDLoopIdx);
		leftDrive.config_kF(0, Variables.f, Variables.delay);
		leftDrive.config_kP(0, Variables.p, Variables.delay);
		leftDrive.config_kI(0, Variables.i, Variables.delay);
		leftDrive.config_kD(0, Variables.d, Variables.delay);
		
		rightDrive.selectProfileSlot(Variables.kSlotIdx, Variables.kPIDLoopIdx);
		rightDrive.config_kF(0, Variables.f, Variables.delay);
		rightDrive.config_kP(0, Variables.p, Variables.delay);
		rightDrive.config_kI(0, Variables.i, Variables.delay);
		rightDrive.config_kD(0, Variables.d, Variables.delay);
		
		/* set acceleration and vcruise velocity - see documentation */
		leftDrive.configMotionCruiseVelocity(3000, Variables.delay);
		leftDrive.configMotionAcceleration(500, Variables.delay);
		
		rightDrive.configMotionCruiseVelocity(3000, Variables.delay);
		rightDrive.configMotionAcceleration(500, Variables.delay);
		
		/* zero the sensor */
		leftDrive.setSelectedSensorPosition(0, Variables.kPIDLoopIdx, Variables.delay);
		rightDrive.setSelectedSensorPosition(0, Variables.kPIDLoopIdx, Variables.delay);
	}
	
	//**************//
	//
	//	Setup pneumatics
	//
	//**************//
	private void initPneumatics() {
		try {
			if((variables.getClampIds()[0] != 0 || variables.getClampIds()[1] != 0) && (variables.getExtenderIds()[0] != 0 || variables.getExtenderIds()[1] != 0)) { 
				clamp = new DoubleSolenoid(variables.getClampIds()[0], variables.getClampIds()[1]);
				extender = new DoubleSolenoid(variables.getExtenderIds()[0], variables.getExtenderIds()[1]);
				ramp = new DoubleSolenoid(variables.getRampPistonIds()[0], variables.getRampPistonIds()[1]);
				compressor = new Compressor(0);
				pneumaticsEnabled = true;
			}
			else {
				pneumaticsEnabled = false;
			}
		}catch(Exception e) {
			pneumaticsEnabled = false;
		}
	}
	
	private void refreshPneumatics() {
		try {
			clamp.get();
			extender.get();
			ramp.get();
			compressor.getClosedLoopControl();
			pneumaticsEnabled = true;
		}catch(Exception e) {
			pneumaticsEnabled = false;
		}
	}
	
	//turn on/off the compressor
	public void enableCompressor(boolean x) {
		if(pneumaticsEnabled) {
			compressor.setClosedLoopControl(x);
		}
	}
	
	//**************//
	//
	//  Setup and configure intake system	
	//
	//**************//
	private void initIntake() {
		try {
			if(!(variables.getIntakeWheelIds()[0] == 0 || variables.getIntakeWheelIds()[1] == 0)) {
				intake = new VictorSPX(variables.getIntakeWheelIds()[0]);
				intake2 = new VictorSPX(variables.getIntakeWheelIds()[1]);
				configIntake();
				intakeEnabled = true;
			}
			else {
				intakeEnabled = false;
			}
		}catch(Exception e) {
			intakeEnabled = false;
		}
	}
	
	private void refreshIntake() {
		try {
			if(!(variables.getIntakeWheelIds()[0] == 0 || variables.getIntakeWheelIds()[1] == 0)) {
				intake.getMotorOutputPercent();
				intake2.getMotorOutputPercent();
				intakeEnabled = true;
			}
			else {
				intakeEnabled = false;
			}
		}catch(Exception e) {
			intakeEnabled = false;
		}
	}
	
	private void configIntake() {
		intake.setInverted(variables.getLeftPolarityI());
		intake2.setInverted(variables.getRightPolarityI());
		intake2.follow(intake);
	}
	
	//**************//
	//
	//	Setup and configure mast
	//  TODO: Implement Hall Effect encoder
	//
	//**************//
	private void initMast() {
		try {
			if(variables.getMastId() != 0) {
				mast = new TalonSRX(variables.getMastId());
				configMast();
				mastEnabled = true;
			}
			else {
				mastEnabled = false;
			}
		}catch(Exception e) {
			mastEnabled = false;
		}
	}
	
	private void refreshMast() {
		try {
			if(variables.getMastId() != 0) {
				mast.getMotorOutputPercent();
				mastEnabled = true;
			}
			else {
				mastEnabled = false;
			}
		}catch(Exception e) {
			mastEnabled = false;
		}
	}
	
	private void configMast() {
		mast.setInverted(variables.getMastPolarity());
		mast.setSensorPhase(variables.getMastPhase());
	}
	
	private void initWinch() {
		try {
			if(variables.getWinchId() != 0) {
				winch = new VictorSPX(variables.getWinchId());
				configWinch();
				winchEnabled = true;
			}
			else {
				winchEnabled = false;
			}
		}catch(Exception e) {
			winchEnabled = false;
		}
	}
	
	private void refreshWinch() {
		try {
			if(variables.getWinchId() != 0) {
				winch.getMotorOutputPercent();
				winchEnabled = true;
			}
			else {
				winchEnabled = false;
			}
		}catch(Exception e) {
			winchEnabled = true;
		}
	}
	
	private void configWinch() {
		winch.setInverted(variables.getWinchPolarity());
	}
}
