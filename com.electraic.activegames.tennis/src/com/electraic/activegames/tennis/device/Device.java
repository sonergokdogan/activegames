package com.electraic.activegames.tennis.device;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;

public class Device {

	//public enum Role { WAIT, SUCCESS, FAILURE, TIMEOUT};
//	public final short WAIT = 1;
//	public final short SUCCESS = 2;
//	public final short FAILURE = 4;
//	public final short TIMEOUT = 8;
	
	public enum State {
		stIDLE(0), stHITWAIT(1), stSUCCESSFULHIT(2), stFALSEHIT(4), stTIMEOUT(8);
		
		private final int value;
		public int getValue() { return value;}
		State(int v) { value =v;} 
	};
	
	State state;
	short deviceId;
	protected String friendlyName="";
	Pin pin;
	//Role role;
	int role;
	//public enum LightColor { GREEN, RED, BLUE };
	private GpioController gpioController = GpioFactory.getInstance();
	protected GpioPinDigitalInput digitalInput = null;
	protected GpioPinDigitalOutput digitalOutput = null;
		
	public Device(Pin pin) {
		this.pin = pin;
	}
	
	public State getState() {
		return state;
	}
	
	public void setState(State state) {
		System.out.println("This is " + this.friendlyName + " and state is changing to " + state.toString());
		this.state = state;
		
	}
	public String getFriendlyName() {
		return friendlyName;
	}
	public void setFriendlyName(String friendlyName) {
		this.friendlyName = friendlyName;
	}
//	public Role getRole() {
//		return role;
//	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public Pin getPin() {
		return pin;
	}
	public void setPin(Pin pin) {
		this.pin = pin;
	}
	
	public short getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(short deviceId) {
		this.deviceId = deviceId;
	}
	public GpioController getGpioController() {
		return gpioController;
	}
	public void setGpioController(GpioController gpioController) {
		this.gpioController = gpioController;
	}
	public GpioPinDigitalInput getDigitalInput() {
		return digitalInput;
	}
	public void setDigitalInput(GpioPinDigitalInput digitalInput) {
		this.digitalInput = digitalInput;
	}
	public GpioPinDigitalOutput getDigitalOutput() {
		return digitalOutput;
	}
	public void setDigitalOutput(GpioPinDigitalOutput digitalOutput) {
		this.digitalOutput = digitalOutput;
	}
	
	
}
