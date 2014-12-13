package com.electraic.activegames.tennis.game;

import com.electraic.activegames.tennis.device.Pinode;
import com.electraic.activegames.tennis.device.Device.State;

public class Scene {

	public static void main(String[] args) {
		System.out.println("Starting Scene");
		Pinode p = new Pinode();
		p.setState(State.stIDLE);
		try {
			Thread.sleep(5000);
			p.setState(State.stHITWAIT);
			Thread.sleep(5000);
			p.setState(State.stHITWAIT);
			Thread.sleep(5000);
			p.setState(State.stHITWAIT);
			Thread.sleep(5000);
			p.setState(State.stHITWAIT);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
