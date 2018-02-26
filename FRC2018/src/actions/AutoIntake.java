package actions;

import org.usfirst.frc.team5468.robot.Hardware;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import regulation.PID;
import templates.Action;
import utilities.Vision;

public class AutoIntake extends Action{
	private PID visionPID = new PID(.02, 0, 0);
	private PID lateralPID = new PID(.1, 0, 0);
	private double maxOutput = .4;
	private double maxError = 1;
	private double targetSize = 10;
	
	private double lastSteering = 0;
	
	public AutoIntake(Hardware r) {
		super(r);
	}
	
	public AutoIntake(Hardware r, PID p) {
		super(r);
		visionPID = p;
	}

	@Override
	public void run() {
		double steering = 0;
		double lateral = 0;
		
		if(!started) {
			visionPID.setMaxOutput(maxOutput);
			visionPID.setTarget(0);
			visionPID.setNoiseLevels(.01);
			
			lateralPID.setMaxOutput(maxOutput);
			lateralPID.setTarget(targetSize);
		}
		
		started = true;
		
		if(robot.lemonlightPresent) {
			if(Math.abs(robot.lemonlight.getOffsetDegrees()) > maxError) {
				steering = visionPID.output(robot.lemonlight.getOffsetDegrees());
				steering = smoothSteering(steering);
			}
			if(robot.lemonlight.getArea() < targetSize) {
				lateral = lateralPID.output(robot.lemonlight.getArea());
			}
		}

		robot.leftDrive.set(ControlMode.PercentOutput, lateral - steering);
		robot.rightDrive.set(ControlMode.PercentOutput, lateral + steering);
		
		update();
	}
	
	private double smoothSteering(double x) {
		int ecc = 2;
		double newSteering = (x + (lastSteering*ecc)) / (1 + ecc);
		lastSteering = x;
		return newSteering;
	}

	@Override
	public String getAction() {
		return "Seeking action";
	}

	@Override
	public void update() {
		if(!robot.lemonlightPresent || robot.lemonlight.getTargetPresent() == 0) {
			finished = true;
			robot.leftDrive.set(ControlMode.PercentOutput, 0);
			robot.rightDrive.set(ControlMode.PercentOutput, 0);
		}
	}
	
	public PID getPID() {
		return visionPID;
	}

}
