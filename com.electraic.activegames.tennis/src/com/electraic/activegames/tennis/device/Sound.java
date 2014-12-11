package com.electraic.activegames.tennis.device;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;

public class Sound extends Device implements DeviceAction{

	Map<Integer, URL> musicList = new HashMap<Integer, URL>();
	private AudioInputStream audioInputStream = null;
	private Clip clip = null;

	@Override
	public void act() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void react() {
		// TODO Auto-generated method stub
		
	}

}
