package functions;

import edu.wpi.first.wpilibj.Counter;

public class HallEncoder {
	private Counter hall;
	private double maxError;
	//measured value
	private double highestPt;
	private double stallPower;
	
	public HallEncoder(Counter h) {
		hall = h;
		initEncoder();
	}
	
	private void initEncoder() {
		hall.reset();
		hall.setDistancePerPulse(1);
	}
	
	public void setError(double x) {
		maxError = x;
	}
	
	public double getPower(double target) {
		target = heightToCounts(target);
		if(Math.abs(heightToCounts(target) - hall.get()) > maxError) {
			if(hall.get() < target) {
				return Math.max(stallPower, (target - hall.get())/highestPt);
			}
			else {
				return (target - hall.get())/highestPt;
			}
		}
		return stallPower;
	}
	
	private double heightToCounts(double x) {
		return x;
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
