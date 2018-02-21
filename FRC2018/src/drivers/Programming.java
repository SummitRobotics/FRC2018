package drivers;

import templates.Driver;

public class Programming extends Driver{
	public Programming() {
		super("Development");
		lateralExponent = 3;
		rotateSensitivity = 1;
		maxPower = .5;
		threshold = .3;
		joystickError = .3;
	}
}
