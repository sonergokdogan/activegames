package com.electraic.activegames.tennis.game;

import com.electraic.activegames.tennis.device.Pinode;
import com.electraic.activegames.tennis.device.Device.State;

public class Scene {

	public static void main(String[] args) {
		Pinode p = new Pinode();
		p.changeState(State.stIDLE);
		try {
			Thread.sleep(5000);
			p.changeState(State.stHITWAIT);
			Thread.sleep(5000);
			p.changeState(State.stHITWAIT);
			Thread.sleep(5000);
			p.changeState(State.stHITWAIT);
			Thread.sleep(5000);
			p.changeState(State.stHITWAIT);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
