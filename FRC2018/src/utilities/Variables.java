package utilities;

public class Variables {
	//constants relevant for encoders
	public static final int delay = 0;
	public static final int kPIDLoopIdx = 0;
	public static final int kSlotIdx = 0;
	
	//pidf values
	public static final double p = 0.2;
	public static final double i = 0;
	public static final double d = 0;
	public static final double f = 0.2;
	
	//defines the variables specific to the robot
	private String robotName;
	
	//necessary for correct distance calculation
	private double wheelDiam;
	private double driveTrainWidth;
	private double minRotatePower;
	
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
	
	//pneumatics
	private int clampIdA;
	private int clampIdB;
	private int extenderIdA;
	private int extenderIdB;
	
	//left intake motor
	private int leftIntakeId;
	private int rightIntakeId;
	private boolean leftReversedI = false;
	private boolean rightReversedI = false;
	
	//mast motor
	private int mastId;
	private boolean mastReversed = false;
	private boolean mastPhase = false;
	private double minStallingPower = .15;
	
	//ramp
	private int winchId;
	private boolean winchReversed = false;
	private double minWinchPower;
	private int[] rampPistonIds = new int[2];
	
	//hall effect sensor
	private int mastHallEffectSensorId;
	private int mastDistancePerPulse;
	private int[] mastEncoderRange = {1, 10000};
	
	//switches
	private int intakeSwitchId;
	private int lowerMastId;
	private int higherMastId;

	public Variables(String name) {
		robotName = name;
		updateVars();
	}

	private void updateVars() {
		if(robotName == "A") {
			wheelDiam = 6;
			driveTrainWidth = 28;
			minRotatePower = .3;
			minWinchPower = -.2;
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
			minRotatePower = .5;
			leftReversed = true;
			rightReversed = false;
			leftSensorPhase = false;
			rightSensorPhase = false;
			rightReversedI = true;
			leftIntakeId = 34;
			winchId = 37;
			winchReversed = true;
			rightIntakeId = 38;
			leftFollowerId = 32;
			leftDriveId = 21;
			rightFollowerId = 36;
			rightDriveId = 25;
			clampIdA = 0;
			clampIdB = 1;
			extenderIdA = 3;
			extenderIdB = 2;
			mastId = 23;
			mastReversed = true;
			mastHallEffectSensorId = 0;
			mastDistancePerPulse = 5;
			higherMastId = 2;
			lowerMastId = 1;
			minWinchPower = 0;
			rampPistonIds[0] = 4;
			rampPistonIds[1] = 5;
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
	
	public int getIntakeSwitchId() {
		return intakeSwitchId;
	}
	
	public int getLowerSwitchId() {
		return lowerMastId;
	}
	
	public int getHigherSwitchId() {
		return higherMastId;
	}
	
	public int getWinchId() {
		return winchId;
	}
	
	public boolean getWinchPolarity() {
		return winchReversed;
	}
	
	public int getMastSensorId() {
		return mastHallEffectSensorId;
	}
	
	public double getDistancePerPulse() {
		return mastDistancePerPulse;
	}
	
	public double getMinimumMastPower() {
		return minStallingPower;
	}
	
	public int[] getMastRange() {
		return mastEncoderRange;
	}
	
	public double getMinRotatePower() {
		return minRotatePower;
	}
	
	
	public double getWinchMinPower() {
		return minWinchPower;
	}
	
	public int[] getRampPistonIds(){
		return rampPistonIds;
	}
}
