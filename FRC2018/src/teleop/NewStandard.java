package teleop;

import java.util.ArrayList;

import org.usfirst.frc.team5468.robot.Hardware;
import com.ctre.phoenix.motorcontrol.ControlMode;
import actions.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import functions.MotorControl;
import sequencing.Subsequence;
import templates.Action;
import templates.TeleopProgram;

public class NewStandard extends TeleopProgram{
	//controlling the clamp
	private Piston clampAction;
	private ButtonShift clamp;
	
	//controlling the piston
	private Piston extenderAction;
	private ButtonShift extender;
	
	//master kill switch
	private boolean resetInterrupted = true;
	private ButtonShift reset;
	
	//shooting cubes
	private boolean ejectInterrupted = true;
	private Subsequence ejectAction;
	private ButtonShift eject;
	
	//vault sequence
	private boolean vaultInterrupted = true;
	private Subsequence vaultAction;
	private ButtonShift vault;
	
	//controlling the mast
	private boolean mastInterrupted = true;
	private MastD mastAction;
	private JoyShift mast;
	
	
	public NewStandard(Hardware r) {
		super(r, "NewStandard");
		clamp = new ButtonShift(r.controller, 3);
		clampAction = new Piston(r, robot.clamp);
		
		extender = new ButtonShift(r.controller, 1);
		extenderAction = new Piston(r, robot.extender);
		
		reset = new ButtonShift(r.controller, 2);
		
		eject = new ButtonShift(r.controller, 3);
		ejectAction = newEjectSequence();
		
		mast = new JoyShift(r.controller, 5);
		mastAction = new MastD(robot, 0);
	}
	
	private Subsequence newEjectSequence() {
		ArrayList<Action> actions = new ArrayList<Action>();
		actions.add(new Delay(robot, .4));
		actions.add(clampAction);
		return new Subsequence(actions);
	}

	@Override
	public void teleopInit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void teleopPeriodic() {	
		if(clamp.toggled()) {
			ejectInterrupted = true;
			clampAction.run();
		}
		if(extender.toggled()) {
			ejectInterrupted = true;
			extenderAction.run();
		}
		reset();
		eject();
	}
	
	private void reset() {
		if(reset.toggled()) {
			resetInterrupted = false;
		}
		if(!resetInterrupted) {
			actuateReset();
		}
		resetInterrupted = true;
	}
	
	private void actuateReset() {
		robot.clamp.set(DoubleSolenoid.Value.kForward);
		robot.extender.set(DoubleSolenoid.Value.kReverse);
	}
	
	private void eject() {
		if(eject.toggled()) {
			ejectInterrupted = false;
			ejectAction = newEjectSequence();
		}
		if(!ejectInterrupted) {
			robot.extender.set(DoubleSolenoid.Value.kForward);
			ejectAction.run();
		}
	}

	@Override
	public void teleopDisabledInit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void teleopDisabledPeriodic() {
		// TODO Auto-generated method stub
		
	}

}

class ButtonShift {
	private Joystick controller;
	private boolean prevState;
	private int index;
	
	public ButtonShift(Joystick con, int i) {
		controller = con;
		index = i;
		prevState = controller.getRawButton(i);
	}
	
	public boolean toggled() {
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
	}
}

class JoyShift{
	private Joystick controller;
	private double prevPos;
	private int index;
	
	public JoyShift(Joystick con, int i) {
		controller = con;
		index = i;
	}
	
	public boolean isOverriden() {
		if(prevPos != controller.getRawAxis(index)) {
			return true;
		}
		else {
			return false;
		}
	}
}
