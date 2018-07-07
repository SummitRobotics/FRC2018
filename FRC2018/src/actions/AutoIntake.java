package actions;

import org.usfirst.frc.team5468.robot.Hardware;
import com.ctre.phoenix.motorcontrol.ControlMode;
import templates.Action;

public class AutoIntake extends Action{
	//tuning
	private double maxOutput = .7;
	
	//maximum angle offset from cube - must be nonzero
	private double maxError = 1;
	private double forwardZone = 15;
	
	//how large the cube should be present on the screen before stopping
	private double targetSize = 55;
	
	//to prevent signicicant jerkiness during auto
	private double lastLeftPower = 0;
	private double lastRightPower = 0;
	
	//constructor
	public AutoIntake(Hardware r) {
		super(r);
	}

	@Override
	public void actionInit() {
		//init
	}
	
	@Override
	public void actionPeriodic() {
		double steering = 0;
		double lateral = 0;
		
		//if vision present
		if(robot.lemonlightPresent) {
			if(robot.lemonlight.getArea() < targetSize && Math.abs(robot.lemonlight.getOffsetDegrees()) > maxError) {
				steering = robot.lemonlight.getOffsetDegrees() / 80;
			}
			if(robot.lemonlight.getArea() < targetSize && Math.abs(robot.lemonlight.getOffsetDegrees()) < forwardZone) {
				lateral = (targetSize - robot.lemonlight.getArea()) / (targetSize * 2);
			}
		}
		
		//calculate the new output
		double leftPower = clamp(lateral + steering, -maxOutput, maxOutput);
		double rightPower = clamp(lateral - steering, -maxOutput, maxOutput);
		
		//smooth changes
		leftPower = smoothPower(lastLeftPower, leftPower);
		rightPower = smoothPower(lastRightPower, rightPower);
		lastLeftPower = leftPower;
		lastRightPower = rightPower;
		
		//set power accordingly
		robot.leftDrive.set(ControlMode.PercentOutput, leftPower);
		robot.rightDrive.set(ControlMode.PercentOutput, rightPower);
	}
	
	
	public double clamp(double value, double min, double max) {
		if(value > max) {
			return max;
		}
		else if(value < min) {
			return min;
		}
		else {
			return value;
		}
	}
	
	//given the lack of PID tuning time
	//I smoothed out some of the jerky movements manually
	private double smoothPower(double past, double present) {
		int n = 0;
		return ((past*n) + present)/(1+n);
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
