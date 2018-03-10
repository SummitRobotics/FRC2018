package actions;

import org.usfirst.frc.team5468.robot.Hardware;

import com.ctre.phoenix.motorcontrol.ControlMode;

import functions.HallEncoder;
import templates.Action;

public class MastD extends Action {
	//distances
	private double target;
	private double offset;
	private double maxPower = 1;
	private double minPower = .1;
	
	private HallEncoder encoder;
	
	//constructor
	public MastD(Hardware r, double distance) {
		super(r);
		target = distance;
	}

	@Override
	public void actionInit() {
		encoder = new HallEncoder(robot.hall);
		encoder.setError(500);
	}

	@Override
	public void actionPeriodic() {
		if(robot.mastEnabled) {
			robot.mast.set(ControlMode.PercentOutput, encoder.getPower(target));
		}
	}

	@Override
	public boolean actionFinished() {
		if(encoder.)
	}
	
	@Override
	public String getAction() {
		return "Distance";
	}

}
