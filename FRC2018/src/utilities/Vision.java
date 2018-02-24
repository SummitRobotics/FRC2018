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
	}
	
	//keep updating the values
	public void run() {
		if (!Thread.currentThread().isInterrupted()) {
			xOffset = limelight.getEntry("tx");
			targetPresent= limelight.getEntry("tv");
			area = limelight.getEntry("ta");
		}
	}
	
	//returns offset from center in degrees
	public double getOffsetDegrees() {
		return xOffset.getDouble(0);
	}
	
	//returns whether a contoured object is present
	public boolean getTargetPresent() {
		return targetPresent.getBoolean(false);
	}
	
	//return the size of contour relative to FOV
	//100 means the object blocks the entire limelight
	public double getArea() {
		return area.getDouble(0);
	}
}