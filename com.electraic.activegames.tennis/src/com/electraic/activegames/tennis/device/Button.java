package com.electraic.activegames.tennis.device;

import java.util.Vector;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class Button extends Device implements DeviceAction, GpioPinListenerDigital, DeviceNotifier{

	PinState buttonState;
	Vector<DeviceListener> listeners = new Vector<DeviceListener>();
	
	public Button(Pin pin) {
		super(pin);
		// TODO Auto-generated constructor stub
		this.digitalInput = this.getGpioController().provisionDigitalInputPin(this.pin, PinPullResistance.PULL_DOWN);
		this.digitalInput.addListener(this);
	}
	@Override
	public void act() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void react() {
		// TODO Auto-generated method stub
		for (DeviceListener deviceListener : listeners) {
			deviceListener.notifyButtonChange(this.pin, this.buttonState);
		}
	}

	@Override
	public void handleGpioPinDigitalStateChangeEvent(
			GpioPinDigitalStateChangeEvent e) {
		// TODO Auto-generated method stub
		System.out.println(" --> GPIO PIN STATE CHANGE: " + e.getPin() + " = " + e.getState());
		this.buttonState = e.getState();
		react();
	}

	@Override
	public void addObserver(DeviceListener dl) {
		this.listeners.add(dl);
		
	}

}
