package utilities;

import java.util.ArrayList;

import org.usfirst.frc.team5468.robot.Hardware;
import autonomous.*;
import drivers.*;
import teleop.*;
import templates.*;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


//**************//
//
//	This class handles all operations related
//	to the SmartDashboard and all non-controller user input
//
//**************//
public class Input {
	private Hardware robot;
	//pre existing classes
	private ArrayList<AutoProgram> auto = new ArrayList<AutoProgram>();
	private ArrayList<TeleopProgram> teleop = new ArrayList<TeleopProgram>();
	private ArrayList<Driver> driver = new ArrayList<Driver>();
	
	//optional user input
	private UserInterface teleopChoice;
	private UserInterface autoChoice;
	private UserInterface driveChoice;
	private UserInterface encoderAuto;
	
	//required autonomous information
	private UserInterface position;
	private UserInterface target;

	public Input(Hardware r) {
		robot = r;
		addExistingClasses();
		displayAutoInformation();
		displayAutoPrereq();
		displayTeleopInformation();
		displayDrivers();
		displayEncoderAuto();
	}
	
	private void addExistingClasses() {
		//glorious encoder based auto movement
		auto.add(new Test(robot));
		auto.add(new RightRightSwitch(robot));
		auto.add(new RightRightScale(robot));
		auto.add(new RightLeftSwitch(robot));
		auto.add(new RightLeftScale(robot));
		auto.add(new LeftRightSwitch(robot));
		auto.add(new LeftRightScale(robot));
		auto.add(new LeftLeftSwitch(robot));
		auto.add(new LeftLeftScale(robot));
		auto.add(new CenterLeftSwitch(robot));
		auto.add(new CenterRightSwitch(robot));
		
		//filthy power based auto methods
		auto.add(new RightRightSwitchPower(robot));
		auto.add(new RightLeftSwitchPower(robot));
		auto.add(new LeftRightSwitchPower(robot));
		auto.add(new LeftLeftSwitchPower(robot));
		auto.add(new CenterLeftSwitchPower(robot));
		auto.add(new CenterRightSwitchPower(robot));
		
		//teleop methods
		teleop.add(new Standard(robot));
		teleop.add(new Testing(robot));
		
		//different drivers
		driver.add(new Programming());
	}
	
	//**************//
	//
	//	Autonomous information:
	//  Targets, Position, and Override
	// 	TO DO: Auto choosing program
	//
	//**************//
	private void displayAutoInformation() {
		autoChoice = new UserInterface(convertAuto(auto), "Autonomous");
	}
	
	private void displayAutoPrereq() {
		ArrayList<String> pos = new ArrayList<String>();
		pos.add("LEFT");
		pos.add("RIGHT");
		pos.add("CENTER");
		
		ArrayList<String> tar = new ArrayList<String>();
		tar.add("SCALE");
		tar.add("SWITCH");
		
		position = new UserInterface(pos, "Position");
		target = new UserInterface(tar, "Target");
	}
	
	private void displayEncoderAuto() {
		ArrayList<String> enc = new ArrayList<String>();
		enc.add("false");
		enc.add("true");
		encoderAuto = new UserInterface(convertEnc(enc), "Encoder Status");
	}
	
	public AutoProgram getAuto() {
		return getAuto(autoChoice.getInput());
	}
	
	//**************//
	//
	//	Teleop Information:
	//	Teleop type and Driver
	//
	//**************//
	private void displayTeleopInformation() {
		teleopChoice = new UserInterface(convertTeleop(teleop), "Teleop");
	}
	
	private void displayDrivers() {
		driveChoice = new UserInterface(convertDriver(driver), "Driver");
	}
	
	public TeleopProgram getTeleop() {
		return getTeleop(teleopChoice.getInput());
	}
	
	public Driver getDriver() {
		return getDriver(driveChoice.getInput());
	}
	
	//**************//
	//
	//	Utility methods:
	//	UserInterface Conversion
	//
	//**************//
	private ArrayList<String> convertDriver(ArrayList<Driver> dr) {
		ArrayList<String> values = new ArrayList<String>();
		for(int a = 0; a < dr.size(); ++a) {
			values.add(dr.get(a).getName());
		}
		return values;
	}
	
	private ArrayList<String> convertAuto(ArrayList<AutoProgram> au) {
		ArrayList<String> values = new ArrayList<String>();
		for(int a = 0; a < au.size(); ++a) {
			values.add(au.get(a).getName());
		}
		return values;
	}
	
	private ArrayList<String> convertTeleop(ArrayList<TeleopProgram> te) {
		ArrayList<String> values = new ArrayList<String>();
		for(int a = 0; a < te.size(); ++a) {
			values.add(te.get(a).getName());
		}
		return values;
	}
	
	private ArrayList<String> convertEnc(ArrayList<String> en) {
		ArrayList<String> values = new ArrayList<String>();
		for(int a = 0; a < en.size(); ++a) {
			values.add(en.get(a));
		}
		return values;
	}
	
	private Driver getDriver(String dr) {
		for(int a = 0; a < driver.size(); ++a) {
			if(driver.get(a).getName() == dr) {
				return driver.get(a);
			}
		}
		return null;
	}
	
	private TeleopProgram getTeleop(String te) {
		for(int a = 0; a < driver.size(); ++a) {
			if(teleop.get(a).getName() == te) {
				return teleop.get(a);
			}
		}
		return null;
	}
	
	private AutoProgram getAuto(String au) {
		for(int a = 0; a < auto.size(); ++a) {
			if(auto.get(a).getName() == au) {
				return auto.get(a);
			}
		}
		return null;
	}
	
	private boolean getEncoder() {
		if(encoderAuto.getInput() == "true") {
			return true;
		}
		else {
			return false;
		}
	}
	
	private char getPosition() {
		return position.getInput().charAt(0);
	}
	
	private char getTarget() {
		return target.getInput().charAt(1);
	}
}

//**************//
//
//	Systematic Input
//
//**************//
class UserInterface{
	String key;
	SendableChooser<String> element;
	ArrayList<String> options;
	
	public UserInterface(ArrayList<String> input, String name) {
		options = input;
		key = name;
		display();
	}
	
	private void display() {
		element = new SendableChooser<String>();
		element.addDefault(options.get(0), options.get(0));
		for(int a = 1; a < options.size(); ++a) {
			element.addObject(options.get(a), options.get(a));
		}
		SmartDashboard.putData(key, element);
	}
	
	public String getInput() {
		return element.getSelected();
	}
}