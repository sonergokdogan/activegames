package com.electraic.activegames.tennis.device;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;

public class Device {

	public enum Role { WAIT, SUCCESS, FAILURE, TIMEOUT};
	public enum State {
		stIDLE, stHITWAIT, stSUCCESSFULHIT, stFALSEHIT, stTIMEOUT
	};
	
	State state;
	short deviceId;
	String friendlyName="";
	Pin pin;
	Role role;
	public enum LightColor { GREEN, RED, BLUE };
	private GpioController gpioController = null;
	protected GpioPinDigitalInput digitalInput = null;
	protected GpioPinDigitalOutput digitalOutput = null;
		
	
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public String getFriendlyName() {
		return friendlyName;
	}
	public void setFriendlyName(String friendlyName) {
		this.friendlyName = friendlyName;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
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
