package templates;

public class Driver {
	private String name;
	protected double driveExponent;
	protected double maxPower;
	protected double threshold;
	protected double steeringCoef;
	

	public Driver(String n) {
		name = n;
	}
	
	public String getName() {
		return name;
	}
	
	public double getDriveExponent() {
		return driveExponent;
	}
	public double getMaxPower() {
		return maxPower;
	}
	public double getThreshold() {
		return threshold;
	}
	public double getSteeringCoef() {
		return steeringCoef;
	}

}
