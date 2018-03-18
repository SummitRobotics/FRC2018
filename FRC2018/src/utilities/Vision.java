package utilities;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Vision extends Thread{
	private NetworkTable limelight;
	private NetworkTableEntry xOffset;
	private NetworkTableEntry area;
	private NetworkTableEntry targetPresent;
	
	//initialize entries
	public Vision() {
		limelight = NetworkTableInstance.getDefault().getTable("limelight");
		limelight.getEntry("ledMode").forceSetNumber(0);
		limelight.getEntry("stream").forceSetNumber(2);
	 	run();
	}
	
	//keep updating the values
	public void run() {
		if (!Thread.currentThread().isInterrupted()) {
			xOffset = limelight.getEntry("tx");
			targetPresent= limelight.getEntry("tv");
			area = limelight.getEntry("ta");
			//the lemonlight never wants to eat its food
			//so we'll need to shove the vegetables down its throat
			limelight.getEntry("ledMode").forceSetNumber(0);
			limelight.getEntry("stream").forceSetNumber(2);
		}
	}
	
	//returns offset from center in degrees
	public double getOffsetDegrees() {
		return xOffset.getDouble(0);
	}
	
	//returns whether a contoured object is present
	public double getTargetPresent() {
		return targetPresent.getDouble(0);
	}
	
	//return the size of contour relative to FOV
	//100 means the object blocks the entire limelight
	public double getArea() {
		return area.getDouble(0);
	}
}