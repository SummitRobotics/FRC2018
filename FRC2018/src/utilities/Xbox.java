package utilities;

import edu.wpi.first.wpilibj.Joystick;

//a easy class to use in teleop
//which handles controller disconnects
//and streamlines toggle and DPAD input
//and reversed joysticks to reflect cartesian systems
public class Xbox {
	//the controller we are recieving input from
	private Joystick controller;
	
	//button implementation that tracks toggles
	private Button A;
	private Button B;
	private Button X;
	private Button Y;
	private Button LeftBumper;
	private Button RightBumper;
	private Button Start;
	private Button Back;
	
	//making the DPAD act like buttons
	private DPAD up;
	private DPAD down;
	private DPAD left;
	private DPAD right;
	
	//key mapping and controller input is configured here
	public Xbox(Joystick c) {
		controller = c;
		A = new Button(c, 1);
		B = new Button(c, 2);
		X = new Button(c, 3);
		Y = new Button(c, 4);
		LeftBumper = new Button(c, 5);
		RightBumper = new Button(c, 6);
		Back = new Button(c, 8);
		Start = new Button(c, 7);
		
		//note that the assignments of the DPAD buttons 
		//are akin to a cartesian system zeroed at 90
		//more DPAD buttons could be added at 45 degree multiples too
		up = new DPAD(c, 0);
		down = new DPAD(c, 180);
		left = new DPAD(c, 270);
		right = new DPAD(c, 90);
	}
	
	public double getLeftTrigger() {
		try {
			return controller.getRawAxis(2);
		}catch(Exception e) {
			return 0;
		}
	}
	
	public double getRightTrigger() {
		try {
			return controller.getRawAxis(3);
		}catch(Exception e) {
			return 0;
		}
	}
	
	public double leftJoystickX() {
		try{
			return controller.getRawAxis(0);
		}catch(Exception e) {
			return 0;
		}
	}
	
	public double leftJoystickY() {
		try{
			return -controller.getRawAxis(1);
		}catch(Exception e) {
			return 0;
		}
	}
	
	public double rightJoystickX() {
		try {
			return -controller.getRawAxis(4);
		}catch(Exception e){
			return 0;
		}
	}
	
	public double rightJoystickY() {
		return -controller.getRawAxis(5);
	}
	
	public boolean aIsToggled() {
		return A.toggled();
	}
	
	public boolean a() {
		return A.isPressed();
	}
	
	public boolean bIsToggled() {
		return B.toggled();
	}
	
	public boolean b() {
		return B.isPressed();
	}
	
	public boolean xIsToggled() {
		return X.toggled();
	}
	
	public boolean x() {
		return X.isPressed();
	}
	
	public boolean yIsToggled() {
		return Y.toggled();
	}
	
	public boolean y() {
		return Y.isPressed();
	}
	
	public boolean leftBumperIsToggled() {
		return LeftBumper.toggled();
	}
	
	public boolean leftBumper() {
		return LeftBumper.isPressed();
	}
	
	public boolean rightBumperIsToggled() {
		return RightBumper.toggled();
	}
	
	public boolean rightBumper() {
		return RightBumper.isPressed();
	}
	
	public boolean startIsToggled() {
		return Start.toggled();
	}
	
	public boolean start() {
		return Start.isPressed();
	}
	
	public boolean backIsToggled() {
		return Back.toggled();
	}
	
	public boolean back() {
		return Back.isPressed();
	}
	
	public boolean upperDPADIsToggled() {
		return up.toggled();
	}
	
	public boolean upperDPAD() {
		return up.isPressed();
	}
	
	public boolean leftDPADIsToggled() {
		return left.toggled();
	}
	
	public boolean leftDPAD() {
		return left.isPressed();
	}
	
	public boolean lowerDPADIsToggled() {
		return down.toggled();
	}
	
	public boolean lowerDPAD() {
		return down.isPressed();
	}
	
	public boolean rightDPADIsToggled() {
		return right.toggled();
	}
	
	public boolean rightDPAD() {
		return right.isPressed();
	}
}


class Button {
	private Joystick controller;
	private boolean prevState;
	private int index;
	
	public Button(Joystick con, int i) {
		controller = con;
		index = i;
		prevState = true;
	}
	
	public boolean toggled(){
		try{
			if(controller.getRawButton(index) != prevState) {
				if(controller.getRawButton(index) == true) {
					prevState = true;
					return true;
				}
				else {
					prevState = false;
					return false;
				}
			}
			else {
				return false;
			}
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean isPressed() {
		try{
			return controller.getRawButton(index);
		}catch(Exception e){
			return false;
		}
	}
}

class DPAD{
	private Joystick controller;
	private boolean prevState;
	private int val;
	
	public DPAD(Joystick con, int v) {
		controller = con;
		val = v;
		if(val == con.getPOV()) {
			prevState = true;
		}
		else {
			prevState = false;
		}
	}
	
	public boolean toggled() {
		try{
			if(!prevState) {
				if(controller.getPOV() == val) {
					prevState = true;
					return true;
				}
				else {
					prevState = false;
					return false;
				}
			}
			else {
				if(controller.getPOV() == val) {
					prevState = true;
					return false;
				}
				else {
					prevState = false;
					return false;
				}
			}
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean isPressed() {
		try{
			if(controller.getPOV() == val) {
				return true;
			}
			else {
				return false;
			}
		}catch(Exception e){
			return false;
		}
	}
}
