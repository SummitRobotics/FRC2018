package functions;

public class MotorControl {
	private double maxChange;
	private double lastMotorPower = 0;
	
	public MotorControl(double max) {
		maxChange = max;
	}
	
	public double rampPower(double p) {
		double newPower = 0;
		if(lastMotorPower > p) {
			newPower = lastMotorPower - (2*maxChange);
		}
		else {
			newPower = lastMotorPower + maxChange;
		}		
		lastMotorPower = newPower;
		return newPower;
	}

}
