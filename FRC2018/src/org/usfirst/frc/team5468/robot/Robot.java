/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5468.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import templates.*;
import utilities.*;

public class Robot extends IterativeRobot {
	private Hardware robot;
	private Input input;
	private Output output;
	private Thread lemonlight;
	
	private TeleopProgram teleop;
	private AutoProgram auto;
	
	@Override
	public void robotInit() {
		robot = new Hardware();
		input = new Input(robot);
		output = new Output(robot);
		lemonlight = new Vision();
	}

	@Override
	public void autonomousInit() {
		SmartDashboard.putNumber("Led Mode", 1);
		lemonlight = new Vision();
		auto = input.getAuto();
		robot.enableCompressor(true);
		auto.autonomousInit();
	}

	@Override
	public void autonomousPeriodic() {
		leds.input(2);
		lemonlight.run();
		output.update();
		auto.autonomousPeriodic();
	}

	@Override
	public void teleopInit(){
		SmartDashboard.putNumber("Led Mode", 6);
		lemonlight = new Vision();
		teleop = input.getTeleop();
		robot.enableCompressor(true);
		teleop.teleopInit();
	}
	
	@Override
	public void teleopPeriodic() {
		leds.input(1);
		lemonlight.run();
		output.update();
		teleop.teleopPeriodic();
	}
	
	@Override
	public void disabledInit() {
		SmartDashboard.putNumber("Led Mode", 0);
		lemonlight.interrupt();
	}
	
	Leds leds = new Leds();
	@Override 
	public void disabledPeriodic() {
		leds.input(6);
		robot.refreshConfig();
		output.update();
	}
}
