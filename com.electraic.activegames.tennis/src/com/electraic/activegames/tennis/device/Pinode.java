package com.electraic.activegames.tennis.device;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.electraic.activegames.tennis.device.Device.LightColor;
import com.electraic.activegames.tennis.device.Device.Role;
import com.electraic.activegames.tennis.device.Device.State;
import com.pi4j.io.gpio.GpioPin;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class Pinode implements DeviceListener { // implements MQTT {

//	ArrayList<Led> leds = new ArrayList<Led>();
//	ArrayList<Button> buttons = new ArrayList<Button>();
//	ArrayList<Sound> sounds = new ArrayList<Sound>();

	public Pinode() {
		// TODO Auto-generated constructor stub
		Led led = new Led();
		led.setRole(Role.SUCCESS);
		led.setPin(RaspiPin.GPIO_02);
		led.setFriendlyName("Pinode 1 Success Led");
		
		Button button = new Button();
		button.setPin(RaspiPin.GPIO_03);
		button.setFriendlyName("Pinode 1 Button");
		button.addObserver(this);
		
	}
	ArrayList<Device> devices = new ArrayList<Device>();
	short pinodeId;

	State state;

	public void changeState(State state) {

		this.state = state;
		for (Device device : devices) {
			device.setState(state);
		}
	}

	@Override
	public void notifyButtonChange(Pin pin, PinState pinState) {
		// TODO Auto-generated method stub
		switch(this.state) {
		case stIDLE: this.changeState(State.stFALSEHIT);
		break;
		case stFALSEHIT:
			break;
		case stHITWAIT: this.changeState(State.stSUCCESSFULHIT);
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
