package drivers;

import templates.Driver;

public class Alex extends Driver{
	public Alex() {
		super("Alex");
		lateralExponent = .7;
		rotateSensitivity = 2;
		maxPower = 1;
		threshold = .2;
		joystickError = .3;
	}
}