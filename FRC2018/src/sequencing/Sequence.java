package sequencing;

import java.util.ArrayList;
import org.usfirst.frc.team5468.robot.Hardware;
import actions.*;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import templates.Action;

public class Sequence {
	//hardware components
	private Hardware robot;
	//iteration of the subsequences controlled by flags
	private ArrayList<Subsequence> branches;
	private ArrayList<Action> runningList;
	private ArrayList<Flag> flags;
	
	//constructor
	public Sequence(Hardware r) {
		robot = r;
		branches = new ArrayList<Subsequence>();
		//add default flag for starting subsequnces
		flags = new ArrayList<Flag>();
		flags.add(new Flag(0, true));
	}
	
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
	
	public void addDelay(double time) {
		runningList.add(new Delay(robot, time));
	}
	
	public void addTempIntake() {
		runningList.add(new Compliant(robot, 2, -.8));
	}
	
	public void addEject() {
		runningList.add(new Compliant(robot, 2, 1));
	}
	
	public void addAutoCube() {
		runningList.add(new AutoIntake(robot));
	}
	
	//inches to encoder
	private int ITE(double distance) {
		return (int) Math.round(((distance) / (Math.PI*(robot.variables.getWheelDiam())) * 4096));
	}

	//compile added runningList into a new subsequence
	public void addSequence(int prefix, int suffix) {
		//compile the aforementioned runningList with ending and closing flags
		int[] prefixes = {prefix};
		branches.add(new Subsequence(runningList, prefixes, suffix));
		//clear the list of runningList
		runningList = new ArrayList<Action>();
	}
	
	public void addSequence(int[] prefix, int suffix) {
		//compile the aforementioned runningList with ending and closing flags
		branches.add(new Subsequence(runningList, prefix, suffix));
		//clear the list of runningList
		runningList = new ArrayList<Action>();
	}
	
	
	//**************//
	//
	//	For use by AutoProgram.java
	//	This runs in the background
	//
	//**************//
	public void run() {
		//run through each subsequence
		for(int a = 0; a < branches.size(); ++a) {
			if(!branches.get(a).isFinished()) {
				//update each subsequence with current flags
				branches.get(a).updateFlags(flags);
				//if flags are adequate, sequence will run (interal regulation)
				branches.get(a).run();
			}
			else {
				addFlag(branches.get(a).appendingFlag());
			}
		}
	}
	
	//find whether all subsequences have completed
	public boolean isFinished() {
		for(int a = 0; a < branches.size(); ++a) {
			if(!branches.get(a).isFinished()) {
				return false;
			}
		}
		return true;
	}
	
	//prevent flags from repeating in sequence
	private void addFlag(Flag suffix) {
		//if the index of the flag is detected
		for(int a = 0; a < flags.size(); ++a) {
			//then add nothing
			if(flags.get(a).getFlag() == suffix.getFlag()) {
				return;
			}
		}
		//otherwise add new flag
		flags.add(suffix);
	}

}
