package functions;

public class MotorControl {
	private double lastMotorPower;
	
	public MotorControl() {
		lastMotorPower = 0;
	}
	
	public double smoothPower(double power) {
		int n = 2;
		double output = ((lastMotorPower*n) + power)/(1+n);
		lastMotorPower = output;
		return output;
	}
	
	public double toExponential(double value, double exponent) {
		value = Math.pow(Math.abs(value), exponent) * Math.signum(value);
		
		return value;
	}
	
	public double deadzone(double joystickValue, double deadzone) {
		//if the joystickValue falls within the range of the deadzone...
		if (Math.abs(joystickValue) < deadzone)
		{
			//Set the joystick value to 0
			joystickValue = 0;
		}
		
		return joystickValue;
	}
	
	public double clamp(double value, double min, double max) {
		//Clamp the value to not be lower than the minimum value
		if(value < min)
		{
			value = min;
		}
		
		//Clamp the value to not be greater than the maximum value
		if(value > max)
		{
			value = max;
		}
		
		return value;
	}

}
