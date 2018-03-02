package functions;

import edu.wpi.first.wpilibj.Counter;

public class HallEncoder {
	private Counter hall;
	
	public HallEncoder(Counter h) {
		hall = h;
		initEncoder();
	}
	
	private void initEncoder() {
		hall.reset();
		hall.setDistancePerPulse(1);
	}
	
	public double getPosition() {
		return hall.getDistance();
	}
	
	public double getRPM() {
		if(hall.getRate() == Double.NaN) {
			return 0;
		}
		else {
			return hall.getRate();
		}	
	}
	
	public boolean stopped() {
		return hall.getStopped();
	}

}
