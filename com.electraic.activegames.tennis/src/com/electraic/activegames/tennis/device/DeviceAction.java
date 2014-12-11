package com.electraic.activegames.tennis.device;

public interface DeviceAction {

	public enum Role { WAIT, SUCCESS, FAILURE};
	
	public void act();
	public void react();
}
