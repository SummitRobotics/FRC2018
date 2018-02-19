package utilities;

import templates.Driver;

public class Variables {
	//constants relevant for encoders
	public static final int delay = 0;
	public static final int kPIDLoopIdx = 0;
	public static final int kSlotIdx = 0;
	
	//pidf values
	public static final double p = .2;
	public static final double i = 0;
	public static final double d = 0;
	public static final double f = .2;
	
	//defines the variables specific to the robot
	private String robotName;
	private Driver driver;
	
	//necessary for correct distance calculation
	private double wheelDiam;
	private double driveTrainWidth;
	
	//reversing polarity of power output
	private boolean leftReversed = false;
	private boolean rightReversed = false;
	
	//Relevant for MotionMagic configuration
	private boolean leftSensorPhase = false;
	private boolean rightSensorPhase = false;
	
	//drive train
	private int leftFollowerId;
	private int leftDriveId;
	private int rightFollowerId;
	private int rightDriveId;
	
	//pneaumatics
	private int clampIdA;
	private int clampIdB;
	private int extenderIdA;
	private int extenderIdB;
	
	//left intake motor
	private int leftIntakeId;
	private int rightIntakeId;
	private boolean leftReversedI = false;
	private boolean rightReversedI = false;
	
	//mast 
	private int mastId;
	private boolean mastReversed = false;
	private boolean mastPhase = false;
	
	private int hallId;

	public Variables(String name) {
		robotName = name;
		updateVars();
	}

	private void updateVars() {
		if(robotName == "A") {
			wheelDiam = 6;
			driveTrainWidth = 28;
			leftReversed = true;
			rightReversed = false;
			leftSensorPhase = false;
			rightSensorPhase = false;
			leftFollowerId = 32;
			leftDriveId = 21;
			rightFollowerId = 31;
			rightDriveId = 22;
		}
		else if(robotName == "B") {
			wheelDiam = 8;
			driveTrainWidth = 33;
			leftReversed = true;
			rightReversed = false;
			leftSensorPhase = false;
			rightSensorPhase = false;
			leftIntakeId = 37;
			rightIntakeId = 34;
			leftFollowerId = 32;
			leftDriveId = 21;
			rightFollowerId = 36;
			rightDriveId = 25;
			clampIdA = 0;
			clampIdB = 1;
			extenderIdA = 2;
			extenderIdB = 3;
			mastId = 23;
		}
	}
	
	public double getWheelDiam() {
		return wheelDiam;
	}
	
	public boolean getLeftPolarityD() {
		return leftReversed;
	}
	
	public boolean getRightPolarityD() {
		return rightReversed;
	}
	
	public boolean getRSensorPhase() {
		return rightSensorPhase;
	}
	
	public boolean getLSensorPhase() {
		return leftSensorPhase;
	}
	
	public int[] getLeftIds() {
		int[] x = {leftDriveId, leftFollowerId};
		return x;
	}
	
	public int[] getRightIds() {
		int[] x = {rightDriveId, rightFollowerId};
		return x;
	}
	
	public int getMastId() {
		return mastId;
	}
	
	public int[] getExtenderIds() {
		int[] x = {extenderIdA, extenderIdB};
		return x;
	}
	
	public int[] getClampIds() {
		int[] x = {clampIdA, clampIdB};
		return x;
	}
	
	public int[] getIntakeWheelIds() {
		int[] x = {leftIntakeId, rightIntakeId};
		return x;
	}
	
	public boolean getMastPolarity() {
		return mastReversed;
	}
	
	public boolean getMastPhase() {
		return mastPhase;
	}
	
	public boolean getLeftPolarityI() {
		return leftReversedI;
	}
	
	public boolean getRightPolarityI() {
		return rightReversedI;
	}
	
	public double getWidth() {
		return driveTrainWidth;
	}
	
	public void setDriver(Driver x) {
		driver = x;
	}
	
	public Driver getDriver() {
		return driver;
	}
	
	public int getHallId() {
		return hallId;
	}
}
