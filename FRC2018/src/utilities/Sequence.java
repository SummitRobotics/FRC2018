package utilities;

import java.util.ArrayList;
import org.usfirst.frc.team5468.robot.Hardware;

import actions.*;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import templates.Action;

public class Sequence {
	private int index = 0;
	private ArrayList<Action> actions = new ArrayList<Action>();
	Hardware robot;
	
	//constructor
	public Sequence(Hardware r) {
		robot = r;
	}
	
	public void clearActions() {
		actions.clear();
	}
	
	//go forward d inches
	public void addForwardD(double d) {
		actions.add(new ForwardD(robot, ITE(d)));
	}
	
	//go forward for time seconds with default power
	public void addForwardT(double time) {
		actions.add(new ForwardT(robot, time));
	}
	
	//go forward for time seconds custom power
	public void addForwardT(double time, double pow) {
		actions.add(new ForwardT(robot, time, pow));
	}
	
	//rotate by x degrees with gyro
	public void addRotationGyro(double theta) {
		actions.add(new RotateGyro(robot, theta));
	}
	
	//rotate by x degrees with gyro with custom power
		public void addRotationGyroCustomPower(double theta, double power) {
			actions.add(new RotateGyro(robot, theta, power));
		}
	
	//controlling pistons
	public void addPiston(DoubleSolenoid x) {
		actions.add(new Piston(robot, x));
	}
	
	public void addAutoClamp() {
		actions.add(new Piston(robot, robot.clamp));
	}
	
	public void addMastD(double d) {
		actions.add(new MastD(robot, HTE(d)));
	}
	
	public void addMastT(double time) {
		actions.add(new MastT(robot, time));
	}
	
	public void addDelay(double time) {
		actions.add(new Delay(robot, time));
	}
	
	//inches to encoder
	private int ITE(double distance) {
		return (int) Math.round(((distance) / (Math.PI*(robot.variables.getWheelDiam())) * 4096));
	}
	
	//inches to encoder, but for the mast
	private int HTE(double x) {
		return 0;
	}
		
	//iterate through the actions
	public void run() {
		if(!done()) {
			//find if previous action has been called
			if(!actions.get(index).finished()) {
				actions.get(index).run();
				SmartDashboard.putString("Action Running: ", actions.get(index).getName() + ", " + index);
			}
			else {
				index++;
			}
		}
	}
	
	public void interuptActions() {
		for(int a = 0; a < actions.size(); ++a) {
			actions.get(a).interrupt();
		}
	}
	
	public boolean done() {
		if(index < actions.size()) {
			return false;
		}
		else {
			SmartDashboard.putString("Action Running: ", "Done");
			return true;
		}
	}

}
