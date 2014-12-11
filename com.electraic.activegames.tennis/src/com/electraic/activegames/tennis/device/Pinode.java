package com.electraic.activegames.tennis.device;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.electraic.activegames.tennis.device.Device.LightColor;

public class Pinode { // implements MQTT {

	ArrayList<Led> leds = new ArrayList<Led>();
	ArrayList<Button> buttons = new ArrayList<Button>();
	ArrayList<Sound> sounds = new ArrayList<Sound>();

	short pinodeId;

	public enum States {
		stIDLE, stHITWAIT, stSUCCESSFULHIT, stFALSEHIT, stTIMEOUT
	};

	public void changeState(States state) {

		setStateOfLeds(state);
		setStateOfButtons(state);
		setStateOfSound(state);
	}

	private void setStateOfLeds(States state) {

		for (Led led : this.leds) {

			switch (state) {
			case stIDLE:
				break;
			case stFALSEHIT:
				break;
			case stHITWAIT:
				break;
			case stSUCCESSFULHIT:
				break;
			case stTIMEOUT:
				break;
			default:
				break;

			}
		}
	}

	private void setStateOfButtons(States state) {

		for (Button button : this.buttons) {

			switch (state) {
			case stIDLE:
				break;
			case stFALSEHIT:
				break;
			case stHITWAIT:
				break;
			case stSUCCESSFULHIT:
				break;
			case stTIMEOUT:
				break;
			default:
				break;

			}
		}
	}
	
	private void setStateOfSound(States state) {

		for (Sound sound : this.sounds) {

			switch (state) {
			case stIDLE:
				break;
			case stFALSEHIT:
				break;
			case stHITWAIT:
				break;
			case stSUCCESSFULHIT:
				break;
			case stTIMEOUT:
				break;
			default:
				break;

			}
		}
	}
}
