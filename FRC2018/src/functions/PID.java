package functions;

public class PID {
	//proportional
	private double p;
	//integration
	private double i;
	//derivative
	private double d;
	
	private double lastError = 0;
	private double nonIntegratedError = 0;
	private double stackingError;
	private double noise = 0;
	private double maxOutput = 0;
	private double target = 0;
	private double input;
	
	//constructor
	public PID(double P, double I, double D) {
		p = P;
		i = I;
		d = D;
	}
	
	public PID(double pid[]) {
		p = pid[0];
		i = pid[1];
		d = pid[2];
	}
	
	//**************//
	//
	//	I/O
	//
	//**************//
	public void setNoiseLevels(double n) {
		noise = n;
	}
	
	public void setMaxOutput(double x) {
		maxOutput = x;
	}

	public void setTarget(double t) {
		target = t;
	}

	public double output(double in) {
		input = in;
		nonIntegratedError += Math.abs(error());
		return rampAcceleration(proportionalGain() + integralGain() - derivativeGain());
	}
	
	public double[] getPIDValues() {
		double[] proxy = new double[3];
		proxy[0] = p;
		proxy[1] = i;
		proxy[2] = d;
		return proxy;
	}
	
	public double getEfficiency() {
		return nonIntegratedError;
	}
	
	//**************//
	//
	//	PID output(s)
	//
	//**************//
	private double proportionalGain() {
		return p * error();
	}
	private double integralGain() {
		stackingError += error();
		return stackingError * i;
	}

	private double derivativeGain() {
		 double slope = (error() - lastError)*(50);
		 lastError = error();
		 return filterNoise(slope) * d;
	}
	
	//**************//
	//
	//	Utilities
	//
	//**************//
	private double rampAcceleration(double x) {
		if(Math.abs(x) > maxOutput) {
			return maxOutput * Math.signum(x);
		}
		return x;
	}
	
	private double filterNoise(double x) {
		if(Math.abs(x) < noise) {
			return 0;
		}
		else return x;
	}
	
	private double error() {
		return target - input;
	}

}
