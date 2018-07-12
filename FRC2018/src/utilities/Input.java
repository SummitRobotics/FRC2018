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
	private ArrayList<AutoProgram> defaultAuto = new ArrayList<AutoProgram>();
	private ArrayList<AutoProgram> customAuto = new ArrayList<AutoProgram>();
	private ArrayList<TeleopProgram> teleop = new ArrayList<TeleopProgram>();
	
	//optional user input
	private UserInterface defaultAutoChoice;
	private UserInterface customAutoChoice;
	
	//required autonomous information
	private UserInterface position;
	private UserInterface custom;
	
	public Input(Hardware r) {
		robot = r;
		addExistingClasses();
		displayAutoInformation();
		displayAutoPrereq();
	}
	
	private void addExistingClasses() {
		//default methods
		defaultAuto.add(new CrossTheLine(robot));
		defaultAuto.add(new DoubleScale(robot));
		//defaultAuto.add(new DoubleSwitch(robot));
		//defaultAuto.add(new Hybrid(robot));
		//defaultAuto.add(new SwitchScale(robot));
		//defaultAuto.add(new ScaleSwitch(robot));
		defaultAuto.add(new SoloScale(robot));
		//defaultAuto.add(new SideSwitch(robot));
		defaultAuto.add(new CenterSwitch(robot));
		
		//custom methods
		customAuto.add(new CustomCapedCrusaders(robot));
		customAuto.add(new CustomUnderdogs(robot));
		customAuto.add(new CustomTestingEnvironment(robot));
		
;		teleop.add(new NewStandard(robot));
	}
	
	private void displayAutoInformation() {
		defaultAutoChoice = new UserInterface(convertAuto(defaultAuto), "Default Methods");
		customAutoChoice = new UserInterface(convertAuto(customAuto), "Custom Methods");
	}
	
	private void displayAutoPrereq() {
		//display position options
		ArrayList<String> pos = new ArrayList<String>();
		pos.add("CENTER");
		pos.add("RIGHT");
		pos.add("LEFT");
		
		ArrayList<String> cusEn = new ArrayList<String>();
		cusEn.add("CUSTOM DISABLED");
		cusEn.add("CUSTOM ENABLED");
		
		position = new UserInterface(pos, "POSITION");
		custom = new UserInterface(cusEn, "CUSTOM ENABLED");
	}
	
	public AutoProgram getAuto() {
		String key = getKey();
		AutoProgram path = getTargetMode();
		path.sendFMS(key);
		return path;
	}
	
	//key generator for autonomous
	private String getKey() {
		char[] key = {' ',' ',' '};
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		key[0] = getPosition();
		key[1] = gameData.charAt(0);
		key[2] = gameData.charAt(1);
		return String.valueOf(key).trim();
	}
	
	public TeleopProgram getTeleop() {
		return teleop.get(0);
	}
	
	private ArrayList<String> convertAuto(ArrayList<AutoProgram> au) {
		ArrayList<String> values = new ArrayList<String>();
		for(int a = 0; a < au.size(); ++a) {
			values.add(au.get(a).getName());
		}
		return values;
	}
	
	private char getPosition() {
		return position.getInput().charAt(0);
	}
	
	//get target class
	private AutoProgram getTargetMode() {
		if(custom.getInput().equals("CUSTOM DISABLED")) {
			for(int a = 0; a < defaultAuto.size(); ++a){
				if(defaultAuto.get(a).getName().equals(defaultAutoChoice.getInput())) {
					return defaultAuto.get(a);
				}
			}
		}
		else {
			for(int a = 0; a < customAuto.size(); ++a){
				if(customAuto.get(a).getName().equals(customAutoChoice.getInput())) {
					return customAuto.get(a);
				}
			}
		}
		return null;
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