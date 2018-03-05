package functions;

public class MotorControl {
	private double maxChange;
	private double lastMotorPower = 0;
	
	public MotorControl(double max) {
		maxChange = max;
	}
	
	public double rampPower(double p) {
		double newPower = lastMotorPower;
		if(Math.abs(p - lastMotorPower) < 10*maxChange){
			return newPower;
		}
		if(lastMotorPower > p) {
			newPower -= (2*maxChange);
		}
		else {
			newPower += maxChange;
		}		
		lastMotorPower = newPower;
		return newPower;
	}

}
