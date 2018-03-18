package actions;

import org.usfirst.frc.team5468.robot.Hardware;
import com.ctre.phoenix.motorcontrol.ControlMode;
import functions.PID;
import templates.Action;

public class AutoIntake extends Action{
	//PID system for vision
	private PID visionPID = new PID(.02, 0, 0);
	private PID lateralPID = new PID(.1, 0, 0);
	
	//tuning
	private double maxOutput = .4;
	//maximum angle offset from cube - must be nonzero
	private double maxError = 2;
	//how large the cube should be present on the screen before stopping
	private double targetSize = 20;
	//to prevent signicicant jerkiness during auto
	private double lastSteering = 0;
	
	//constructor
	public AutoIntake(Hardware r) {
		super(r);
	}

	@Override
	public void actionInit() {
		//setup PID subsystems of both lateral and yaw movement independently
		visionPID.setMaxOutput(maxOutput);
		visionPID.setTarget(0);
		visionPID.setNoiseLevels(.01);
		
		lateralPID.setMaxOutput(maxOutput);
		lateralPID.setTarget(targetSize);
	}
	
	@Override
	public void actionPeriodic() {
		double steering = 0;
		double lateral = 0;
		
		//if vision present
		if(robot.lemonlightPresent) {
			//and if the target is skewed and actually the cube
			if(Math.abs(robot.lemonlight.getOffsetDegrees()) > maxError && robot.lemonlight.getArea() > 1) {
				//then rotate the robot to the target
				steering = visionPID.output(robot.lemonlight.getOffsetDegrees());
				steering = smoothSteering(steering);
			}
			//and if the target is not close to the lemonlight
			if(robot.lemonlight.getArea() < targetSize) {
				//then move forward
				lateral = lateralPID.output(robot.lemonlight.getArea());
			}
		}

		//set power accordingly
		robot.leftDrive.set(ControlMode.PercentOutput, lateral - steering);
		robot.rightDrive.set(ControlMode.PercentOutput, lateral + steering);
	}
	
	//given the lack of PID tuning time
	//I smoothed out some of the jerky movements manually
	private double smoothSteering(double x) {
		int ecc = 2;
		double newSteering = (x + (lastSteering*ecc)) / (1 + ecc);
		lastSteering = x;
		return newSteering;
	}

	@Override
	public boolean actionFinished() {
		//if the lemonlight is disconnected, no targets are found, or the target size is aligned and close
		if(!robot.lemonlightPresent || (robot.lemonlight.getTargetPresent() == 1 && robot.lemonlight.getArea() >= targetSize) || robot.lemonlight.getTargetPresent() == 0) {
			robot.leftDrive.set(ControlMode.PercentOutput, 0);
			robot.rightDrive.set(ControlMode.PercentOutput, 0);
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
		public String getAction() {
			return "Seeking action";
	}
}
