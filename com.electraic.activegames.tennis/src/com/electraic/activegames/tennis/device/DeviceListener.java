package com.electraic.activegames.tennis.device;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;

public interface DeviceListener {
	
	public void notifyButtonChange(Pin pin,PinState pinState);

}
