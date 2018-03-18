package sequencing;

import java.util.ArrayList;
import org.usfirst.frc.team5468.robot.Hardware;
import actions.*;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import templates.Action;

public class Sequence {
	//hardware components
	private Hardware robot;
	//iteration of the subsequences controlled by flags
	private ArrayList<Subsequence> branches;
	private ArrayList<Action> runningList;
	private ArrayList<Flag> flags;
	
	//**************//
	//
	//	addAction() methods
	//
	//**************//
	
	//go forward d inches
	public void addForwardD(double d) {
		runningList.add(new ForwardD(robot, ITE(d)));
	}
	
	//go forward for time seconds with default power
	public void addForwardT(double time) {
		runningList.add(new ForwardT(robot, time));
	}
	
	//go forward for time seconds custom power
	public void addForwardT(double time, double pow) {
		runningList.add(new ForwardT(robot, time, pow));
	}
	
	//go forward for time seconds custom power with gyro fixture
	public void addForwardTGyro(double time, double pow) {
		runningList.add(new ForwardTGyro(robot, time, pow));
	}
	
	//rotate by x degrees with gyro
	public void addRotationGyro(double theta) {
		runningList.add(new RotateGyro(robot, theta));
	}
	
	public void addRotationToX(double theta) {
		theta = (robot.getAbsoluteYaw() % 360) - theta;
		runningList.add(new RotateGyro(robot, theta));
	}
	
	//rotate by x degrees with gyro with custom power
	public void addRotationGyroCustomPower(double theta, double power) {
		runningList.add(new RotateGyro(robot, theta, power));
	}
	
	//controlling pistons
	public void addPiston(DoubleSolenoid x) {
		runningList.add(new Piston(robot, x));
	}
	
	public void addClamp() {
		runningList.add(new Piston(robot, robot.clamp));
	}
	
	public void addExtend() {
		runningList.add(new Piston(robot, robot.extender));
	}
	
	public void addMastD(double d) {
		runningList.add(new MastD(robot, d));
	}
	
	public void addMastT(double time) {
		runningList.add(new MastT(robot, time));
	}
	
	public void addMastT(double time, double power) {
		runningList.add(new MastT(robot, time, power));
	}
	
	public void addMastTop() {
		runningList.add(new MastT(robot, 5, .7));
	}
	
	public void addMastBottom() {
		runningList.add(new MastT(robot, 4, -.4));
	}
	
	public void addMastSwitch() {
		runningList.add(new MastT(robot, 3, .6));
	}
	
	public void addDelay(double time) {
		runningList.add(new Delay(robot, time));
	}
	
	public void addTempIntake() {
		runningList.add(new Compliant(robot, 2, -.8));
	}
	
	public void addEject() {
		addExtend();
		addDelay(.25);
		addClamp();
		addDelay(1);
		addExtend();
	}
	
	public void addAutoCube() {
		runningList.add(new AutoIntake(robot));
		addClamp();
	}
	
	//inches to encoder
	private int ITE(double distance) {
		return (int) Math.round(((distance) / (Math.PI*(robot.variables.getWheelDiam())) * 4096));
	}

	//constructor
	public Sequence(Hardware r) {
		robot = r;
		branches = new ArrayList<Subsequence>();
		runningList = new ArrayList<Action>();
		//add default flag for starting subsequnces
		flags = new ArrayList<Flag>();
		flags.add(new Flag(0, true));
	}

	//compile added runningList into a new subsequence
	public void addSequence(int prefix, int suffix) {
		int[] prefixes = {prefix, 0};
		branches.add(new Subsequence(runningList, prefixes, suffix));
		flags.add(new Flag(suffix, false));
		runningList = new ArrayList<Action>();
	}
	
	//compile added runninglist into a subsequence requiring two flags
	public void addSequence(int prefixA, int prefixB, int suffix) {
		int[] prefix = {prefixA, prefixB};
		branches.add(new Subsequence(runningList, prefix, suffix));
		flags.add(new Flag(suffix, false));
		runningList = new ArrayList<Action>();
	}
	
	//**************//
	//
	//	For use by AutoProgram.java
	//	This runs in the background
	//
	//**************//
	public void run(){
		for(int a = 0; a < branches.size(); ++a) {
			if(!branches.get(a).isFinished() && subsequenceEnabled(branches.get(a))){
				branches.get(a).run();
			}
			updateFlags();
		}
	}
	
	//find whether all subsequences have completed
	public boolean isFinished() {
		if(branches.size() == 0) {
			return true;
		}
		for(int a = 0; a < branches.size(); ++a) {
			if(!branches.get(a).isFinished()) {
				return false;
			}
		}
		return true;
	}
	
	//find whether the required flags have been thrown
	private boolean subsequenceEnabled(Subsequence s) {
		for(int a = 0; a < s.requiredFlags().length; ++a) {
			for(int b = 0; b < flags.size(); ++b) {
				if(flags.get(b).getIndex() == s.requiredFlags()[a] && !flags.get(b).isThrown()) {
						return false;
				}
			}
		}
		SmartDashboard.putBoolean("Enabled:", true);
		return true;
	}
	
	//cycle the status of the flags
	private void updateFlags() {
		for(int a = 0; a < branches.size(); ++a) {
			if(branches.get(a).isFinished()) {
				for(int b = 0; b < flags.size(); ++b) {
					if(branches.get(a).appendingFlag() == flags.get(b).getIndex()) {
						flags.get(b).throwFlag();
					}
				}
			}
		}
	}

}
