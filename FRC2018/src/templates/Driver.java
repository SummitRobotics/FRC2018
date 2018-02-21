package templates;

public class Driver {
	private String name;
	protected double lateralExponent;
	protected double rotateSensitivity;
	
	protected double maxPower;
	
	protected double threshold;
	protected double joystickError;
	
	public Driver(String n) {
		name = n;
	}
	
	public String getName() {
		return name;
	}
	
	public double getLateralExponent() {
		return lateralExponent;
	}
	public double getRotationSensitivity() {
		return rotateSensitivity;
	}
	public double getMaxPower() {
		return maxPower;
	}
	public double getThreshold() {
		return threshold;
	}
	public double getJoystickError() {
		return joystickError;
	}

}
