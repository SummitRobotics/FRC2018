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
	//this is the list of actions that will be compiled when a new subsequence is created
	private ArrayList<Action> runningList;
	//tracking of subsequence starts and ends
	private ArrayList<Flag> flags;
	
	//**************//
	//
	//	addAction() methods
	//	These are methods that can be used for autonomous programs to add 
	//	additional actions. These methods may contain one action, or multiple such as Eject()
	//	These handle many of the parameters for the actions, making auto creation simpler.
	//
	//**************//
	
	//go forward d inches
	public void addForwardD(double distance) {
		runningList.add(new ForwardD(robot, ITE(distance)));
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
	
	//rotate to a fixed angle relative to the starting position
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
	
	//actuate clamp
	public void addClamp() {
		runningList.add(new Piston(robot, robot.clamp));
	}
	
	//actuate extender
	public void addExtend() {
		runningList.add(new Piston(robot, robot.extender));
	}
	
	//move the mast by distance
	public void addMastD(double d) {
		runningList.add(new MastD(robot, d));
	}
	
	//move the mast by time at default speed
	public void addMastT(double time) {
		runningList.add(new MastT(robot, time));
	}
	
	//move the mast by custom speed and time
	public void addMastT(double time, double power) {
		runningList.add(new MastT(robot, time, power));
	}
	
	//go to the top of the mast by time
	public void addMastTop() {
		runningList.add(new MastT(robot, 5, 1));
	}
	
	//go to the bottom of the mast by time
	public void addMastBottom() {
		runningList.add(new MastT(robot, 4, -.7));
	}
	
	//go to the required switch height by time
	public void addMastSwitch() {
		runningList.add(new MastT(robot, 4, .6));
	}
	
	//pausing between actions in a subsequence
	public void addDelay(double time) {
		runningList.add(new Delay(robot, time));
	}
	
	//TO DO: Edit to work with new intakes in eject series
	public void addTempIntake() {
		runningList.add(new Compliant(robot, 1.5, -1));
	}
	
	public void addShoot(){
		runningList.add(new CompliantToggle(robot, 1));
		addDelay(.2);
		runningList.add(new CompliantToggle(robot, 0));
	}
	
	public void toggleIntake(double x){
		runningList.add(new CompliantToggle(robot, x));
	}
	
	public void addManualIntake(){
		runningList.add(new CompliantToggle(robot, -1));
		addDelay(.2);
		runningList.add(new CompliantToggle(robot, 0));
	}
	
	//an example of a multi action method
	//extends, releases, and contracts
	//the delay is implemented so that the
	//forward motion actually throws the cube
	public void addEject() {
		addExtend();
		addDelay(.25);
		addClamp();
		addDelay(1);
		addExtend();
	}
	
	//add auto intake
	public void addAutoCube() {
		addExtend();
		runningList.add(new Compliant(robot, .5, -1));
		addClamp();
		runningList.add(new MastT(robot, .5, .6));
		addExtend();
	}
	
	public void addArc(double x, double y) {
		runningList.add(new Arc(robot, x, y));
	}
	
	public void addRotationByEncoder(double theta) {
		runningList.add(new RotateEncoder(robot, theta));
	}
	
	//inches to encoder
	private int ITE(double distance) {
		return (int) Math.round(((distance) / (Math.PI*(robot.variables.getWheelDiam())) * 4096));
	}

	//**************//
	//
	//	Subsequence handlers
	//	These methods run through, and sort the subsequences into branches 
	//	These branches are executed based upon the states of flags
	//	Meaning that complex autonomous sequences can be achieved.
	//
	//**************//
	
	//constructor
	public Sequence(Hardware r) {
		robot = r;
		branches = new ArrayList<Subsequence>();
		runningList = new ArrayList<Action>();
		//add default flag for starting subsequnces
		//this makes 0 the master flag, being thrown at the beginnning of the sequence
		//this is why all sequences begin at 0. If this changed to 1, 83, or 91293871
		//then the beginning flags would change respectively.
		flags = new ArrayList<Flag>();
		flags.add(new Flag(0, true));
	}

	//compile added runningList into a new subsequence
	//this assumes that 0 is the master flag. 
	//this would need adjusting if the master flag was changed in value
	//this subsequence only requires 1 flag, although it actually requires 2
	//it simply makes the second flag the master flag, which is always called
	public void addSequence(int prefix, int suffix) {
		int[] prefixes = {prefix, 0};
		branches.add(new Subsequence(runningList, prefixes, suffix));
		//note the unthrown flag added to the sequence
		flags.add(new Flag(suffix, false));
		runningList = new ArrayList<Action>();
	}
	
	//compile added runninglist into a subsequence requiring two flags
	//same thing as the above method, but with two explicit required flags
	public void addSequence(int prefixA, int prefixB, int suffix) {
		int[] prefix = {prefixA, prefixB};
		branches.add(new Subsequence(runningList, prefix, suffix));
		//unthrown flag added to master sequence
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
		//called by the AutoProgram.java methods in an iterative fashion
		for(int a = 0; a < branches.size(); ++a) {
			if(!branches.get(a).isFinished() && subsequenceEnabled(branches.get(a))){
				branches.get(a).run();
			}
			updateFlags();
		}
	}
	
	//find whether all subsequences have completed
	public boolean isFinished() {
		//to prevent unwritten auto programs from causing seizures
		if(branches.size() == 0) {
			return true;
		}
		//checks each subsequence
		for(int a = 0; a < branches.size(); ++a) {
			if(!branches.get(a).isFinished()) {
				return false;
			}
		}
		return true;
	}
	
	//find whether the required flags have been thrown for a given subsequence
	private boolean subsequenceEnabled(Subsequence s) {
		//this honestly could have been int a = 0; a < 2; ++a
		for(int a = 0; a < s.requiredFlags().length; ++a) {
			//checking every known flag in the entire sequence
			for(int b = 0; b < flags.size(); ++b) {
				//if a matching number is found but the flag isn't thrown
				if(flags.get(b).getIndex() == s.requiredFlags()[a] && !flags.get(b).isThrown()) {
					//then the sequence has yet to start
					return false;
				}
			}
		}
		//otherwise, run the subsequence
		return true;
	}
	
	//cycle the status of the flags
	private void updateFlags() {
		//check every subsequence added to the master sequence
		for(int a = 0; a < branches.size(); ++a) {
			//if a branch has finished
			if(branches.get(a).isFinished()) {
				//throw the respecting flag
				for(int b = 0; b < flags.size(); ++b) {
					if(branches.get(a).appendingFlag() == flags.get(b).getIndex()) {
						flags.get(b).throwFlag();
					}
				}
			}
		}
	}

}
