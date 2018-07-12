package utilities;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.FlowControl;

public class Leds {
	//communication is the key to success
	private SerialPort serial;
	
	//preventing leds from crashing the robot
	boolean ledsEnabled = false;
	
	//serial configuration
	private final int baudRate = 9600;
	private final int packetSizeBits = 8;
	
	//which port the serial communication will occur upon
	private final SerialPort.Port port = SerialPort.Port.kUSB1;
	//verification regarding packet quality
	private final SerialPort.Parity parity = SerialPort.Parity.kOdd;
	//the standard in electronic applications
	private final SerialPort.StopBits stopBits = SerialPort.StopBits.kOne;
	
	//constructor handling inital status of LEDs
	public Leds() {
		try {
			serial = new SerialPort(baudRate, port, packetSizeBits, parity, stopBits);
			serial.setFlowControl(FlowControl.kXonXoff);
			//serial.setReadBufferSize(bufferSize);
			//serial.setWriteBufferMode(WriteBufferMode.kFlushOnAccess);
			ledsEnabled = true;
			
		}catch(Exception e) {
			ledsEnabled = false;
		}
	}
	
	
	//input method handling potential disconnects during matches
	public void input(int x) {
		if(ledsEnabled) {
			//byte[] info = {(byte) x};
			String a = Integer.toString(x);
			try {
				//serial.write(info, 1);
				serial.writeString(a);
			}catch(Exception e) {
				ledsEnabled = false;
			}
		}
	}

}
