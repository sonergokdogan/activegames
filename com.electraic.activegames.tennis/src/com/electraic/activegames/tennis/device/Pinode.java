package com.electraic.activegames.tennis.device;

import java.util.ArrayList;

//import com.electraic.activegames.tennis.device.Device.Role;
import com.electraic.activegames.tennis.device.Device.State;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class Pinode implements DeviceListener { // implements MQTT {

//	ArrayList<Led> leds = new ArrayList<Led>();
//	ArrayList<Button> buttons = new ArrayList<Button>();
//	ArrayList<Sound> sounds = new ArrayList<Sound>();

	public Pinode() {
		// TODO Auto-generated constructor stub
		Led led = new Led(RaspiPin.GPIO_02);
		led.setRole(Device.State.stSUCCESSFULHIT.getValue());
		led.setFriendlyName("Pinode 1 Success Led");
		
		Led led2 = new Led(RaspiPin.GPIO_03);
		led2.setRole(Device.State.stHITWAIT.getValue() | Device.State.stTIMEOUT.getValue() | Device.State.stFALSEHIT.getValue());
		led2.setFriendlyName("Pinode 1 Failure Led");
		
		Button button = new Button(RaspiPin.GPIO_07);
		button.setFriendlyName("Pinode 1 Button");
		button.addObserver(this);
		
		this.devices.add(led);
		this.devices.add(led2);
		this.devices.add(button);
		
	}
	ArrayList<Device> devices = new ArrayList<Device>();
	short pinodeId;

	State state;

	public void setState(State state) {

		System.out.println("Pinode state is changing to:" + state.toString());
		this.state = state;
		for (Device device : devices) {
			device.setState(state);
		}
	}

	@Override
	public void notifyButtonChange(Pin pin, PinState pinState) {
		// TODO Auto-generated method stub
		switch(this.state) {
		case stIDLE: 
			System.out.println("notifyButtonChange:" + pinState.toString());
			this.setState(State.stFALSEHIT);
		break;
		case stFALSEHIT:
			break;
		case stHITWAIT: this.setState(State.stSUCCESSFULHIT);
			break;
		case stSUCCESSFULHIT:
			break;
		case stTIMEOUT: 
			break;
		default:
			break;
		
		}
	}

	public void feedback() {
		//This function is to feed the current status to the game management
	}
}
