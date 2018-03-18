/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5468.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
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
		lemonlight = new Vision();
		auto = input.getAuto();
		robot.enableCompressor(true);
		auto.autonomousInit();
	}

	@Override
	public void autonomousPeriodic() {
		lemonlight.run();
		output.update();
		auto.autonomousPeriodic();
	}

	@Override
	public void teleopInit(){
		lemonlight = new Vision();
		teleop = input.getTeleop();
		robot.enableCompressor(true);
		teleop.teleopInit();
	}
	
	@Override
	public void teleopPeriodic() {
		lemonlight.run();
		output.update();
		teleop.teleopPeriodic();
	}
	
	@Override
	public void disabledInit() {
		lemonlight.interrupt();
	}
	
	@Override 
	public void disabledPeriodic() {
		robot.refreshConfig();
		output.update();
	}
}
