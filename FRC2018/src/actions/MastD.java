package actions;

import org.usfirst.frc.team5468.robot.Hardware;
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
	}

	@Override
	public void actionPeriodic() {
		if(robot.mastEnabled) {
			
		}
	}

	@Override
	public boolean actionFinished() {
		//if the target has been reached or some technical problems discovered
		if(Math.abs(target - robot.mast.getSelectedSensorPosition(0)) < offset || !robot.mastEnabled) {
			//then finish action
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public String getAction() {
		return "Distance";
	}

}
