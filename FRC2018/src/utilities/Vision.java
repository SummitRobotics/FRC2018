package utilities;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Vision extends Thread{
	NetworkTable limelight;
	
	public Vision() {
		limelight = NetworkTableInstance.getDefault().getTable("limelight");
	}
	
	public void run() {
		NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").forceSetDouble(1);
	}
}
