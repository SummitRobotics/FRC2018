package drivers;

import templates.Driver;

public class Alex extends Driver{
	public Alex() {
		super("Alex");
		driveExponent = .7;
		maxPower = 1;
		threshold = .2;
		steeringCoef = 2;
	}
}