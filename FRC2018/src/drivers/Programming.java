package drivers;

import templates.Driver;

public class Programming extends Driver{
	public Programming() {
		super("Development");
		driveExponent = 1;
		maxPower = 1;
		threshold = .3;
		steeringCoef = 1;
	}
}
