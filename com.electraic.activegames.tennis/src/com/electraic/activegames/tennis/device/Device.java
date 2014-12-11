package com.electraic.activegames.tennis.device;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalInput;

public class Device {

	short deviceId;
//	Roles role;
//	
//	public enum Roles { BUTTON, LED, SPEAKER, CONTROLLER};
	
	public enum LightColor { GREEN, RED, BLUE };
	private GpioController gpio = null;
	private GpioPinDigitalInput digitalInput = null;
	
//	final URL resource = getClass().getResource("mz_545_1.mp3");
	
}
