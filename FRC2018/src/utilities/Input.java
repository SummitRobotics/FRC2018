package utilities;

import java.util.ArrayList;
import org.usfirst.frc.team5468.robot.Hardware;
import autonomous.*;
import teleop.*;
import templates.*;
import edu.wpi.first.wpilibj.DriverStation;
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
	
	//optional user input
	private UserInterface teleopChoice;
	private UserInterface autoChoice;
	
	//required autonomous information
	private UserInterface position;
	private UserInterface target;
	private UserInterface multiCubeEnabled;
	private UserInterface automaticEnabled;
	
	public Input(Hardware r) {
		robot = r;
		addExistingClasses();
		displayAutoInformation();
		displayAutoPrereq();
		displayTeleopInformation();
	}
	
	private void addExistingClasses() {
		//glorious encoder based auto movement
		auto.add(new Test(robot));
		auto.add(new AutoForward(robot));
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
		
		//divine multi cube methods of ascension
		auto.add(new LeftLeftScaleLeftSwitch(robot));
		auto.add(new LeftLeftScaleRightSwitch(robot));
		auto.add(new LeftRightScaleRightSwitch(robot));
		auto.add(new LeftRightScaleLeftSwitch(robot));
		auto.add(new RightRightScaleRightSwitch(robot));
		auto.add(new RightLeftScaleLeftSwitch(robot));
		auto.add(new RightLeftScaleRightSwitch(robot));
		auto.add(new RightRightScaleLeftSwitch(robot));
		
		//filthy power based auto methods
		//auto.add(new RightRightSwitchPower(robot));
		//auto.add(new RightLeftSwitchPower(robot));
		//auto.add(new LeftRightSwitchPower(robot));
		//auto.add(new LeftLeftSwitchPower(robot));
		//auto.add(new CenterLeftSwitchPower(robot));
		//auto.add(new CenterRightSwitchPower(robot));
		
		//teleop methods
		teleop.add(new NewStandard(robot));
		teleop.add(new Testing(robot));
		teleop.add(new Standard(robot));
		
	}
	
	//**************//
	//
	//	Autonomous information:
	//  Targets, Position, and Override
	// 	TO DO: Auto choosing program
	//
	//**************//
	private void displayAutoInformation() {
		autoChoice = new UserInterface(convertAuto(auto), "AUTONOMOUS");
	}
	
	private void displayAutoPrereq() {
		ArrayList<String> pos = new ArrayList<String>();
		pos.add("CENTER");
		pos.add("RIGHT");
		pos.add("LEFT");
		
		ArrayList<String> tar = new ArrayList<String>();
		tar.add("SWITCH");
		tar.add("SCALE");
		tar.add("LINE");
		
		ArrayList<String> mc = new ArrayList<String>();
		mc.add("MULTI CUBE DISABLED");
		mc.add("MULTI CUBE ENABLED");
		
		ArrayList<String> enc = new ArrayList<String>();
		enc.add("AUTOMATIC");
		enc.add("MANUAL");
		position = new UserInterface(pos, "POSITION");
		target = new UserInterface(tar, "TARGET");
		multiCubeEnabled = new UserInterface(mc, "MULTI CUBE STATUS:");
		automaticEnabled = new UserInterface(convertEnc(enc), "AUTO DETERMINATION:");
	}
	
	public AutoProgram getAuto() {
		if(getAutomatic() == true){
			for(int a = 0; a < auto.size(); ++a) {
				SmartDashboard.putString("AutoKEY", getKey());
				if(auto.get(a).getName().equals(getKey())) {
					return auto.get(a);
				}
			}
			
			for(int a = 0; a < auto.size(); ++a) {
				if(auto.get(a).getName() == "FOR") {
					return auto.get(a);
				}
			}
			return null;
		}else{
			return getAuto(autoChoice.getInput());
		}
	}
	
	//key generator for autonomous
	private String getKey() {
		char[] key = {' ',' ',' '};
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		if(!getMultiCube()) {
			key[0] = getTarget();
			if(getTarget() == 'W') {
				key[1] = gameData.charAt(0);
			}
			else if(getTarget() == 'I'){
				return "FOR";
			}
			else {
				key[1] = gameData.charAt(1);
			}
			key[2] = getPosition();
		}
		else {
			key[0] = getPosition();
			key[1] = gameData.charAt(1);
			key[2] = gameData.charAt(2);
		}
		return String.valueOf(key).trim();
	}
	
	//**************//
	//
	//	Teleop Information:
	//	Teleop type and Driver
	//
	//**************//
	private void displayTeleopInformation() {
		//teleopChoice = new UserInterface(convertTeleop(teleop), "TELEOP");
	}
	
	public TeleopProgram getTeleop() {
		return teleop.get(0);
	}
	
	//**************//
	//
	//	Utility methods:
	//	UserInterface Conversion
	//
	//**************//
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
	
	private TeleopProgram getTeleop(String te) {
		for(int a = 0; a < teleop.size(); ++a) {
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
	
	private boolean getAutomatic() {
		try {
			if(automaticEnabled.getInput() == "AUTOMATIC") {
				return true;
			}
			else {
				return false;
			}
		}catch(Exception e) {
			return true;
		}
	}
	
	private boolean getMultiCube() {
		if(multiCubeEnabled.getInput() == "MULTI CUBE ENABLED") {
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
		if(target.getInput() == "SWITCH"){
			return 'W';
		}
		else if(target.getInput() == "SCALE"){
			return 'C';
		}
		else{
			return 'I';
		}
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