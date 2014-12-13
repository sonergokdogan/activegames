package com.electraic.activegames.tennis.device;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;

public class Led extends Device implements DeviceAction {

	Thread activeThread = null;
	
	public Led(Pin pin) {
		super(pin);
		// TODO Auto-generated constructor stub
		this.digitalOutput = this.getGpioController()
				.provisionDigitalOutputPin(this.getPin(), this.friendlyName, PinState.LOW);
	}

	@Override
	public void setState(State state) {
		System.out.println("setState method of Led works!");
		super.setState(state);
		this.act();
	};
	
	@Override
	public void act() {
		// TODO Auto-generated method stub

		if (activeThread != null) {
			activeThread.interrupt();
			try {
				activeThread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				activeThread = null;
			}
		}
		System.out.println("this.role: " + this.role + " this.state:" + this.state.getValue());
		
		if ((this.role & this.state.getValue()) == Device.State.stHITWAIT.getValue())
			activeThread = new Thread(new BlinkWaithitThread(this));
		else if ((this.role & this.state.getValue()) == Device.State.stSUCCESSFULHIT.getValue())
			activeThread = new Thread(new BlinkSuccessThread(this));
		else if ((this.role & this.state.getValue()) == Device.State.stFALSEHIT.getValue())
			activeThread = new Thread(new BlinkFailureThread(this));
		else if ((this.role & this.state.getValue()) == Device.State.stTIMEOUT.getValue())
			activeThread = new Thread(new BlinkTimeoutThread(this));
		
		if (activeThread != null)
			activeThread.start();
	}

	@Override
	public void react() {
		// TODO Auto-generated method stub

	}

	private class BlinkWaithitThread implements Runnable {

		Device d;

		public BlinkWaithitThread(Device d) {
			this.d = d;
			System.out.println("BlinkWaithitThread for " + d.friendlyName);
		}

		@Override
		public void run() {
			for (int i = 0; i < 5 && Thread.interrupted() != true; i++) {
				try {
					d.getDigitalOutput().toggle();
					//System.out.println("Changing pin state to" + d.getDigitalOutput().getState());
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			d.getDigitalOutput().low();
		}

	}

	private class BlinkSuccessThread implements Runnable {

		Device d;

		public BlinkSuccessThread(Device d) {
			System.out.println("BlinkSuccessThread for " + d.friendlyName);
			this.d = d;
		}

		@Override
		public void run() {
			for (int i = 0; i < 5 && Thread.interrupted() != true; i++) {
				try {
					d.getDigitalOutput().toggle();
					//System.out.println("Changing pin state to" + d.getDigitalOutput().getState());
					Thread.sleep(150);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			d.getDigitalOutput().low();
		}

	}

	private class BlinkFailureThread implements Runnable {

		Device d;

		public BlinkFailureThread(Device d) {
			System.out.println("BlinkFailureThread for " + d.friendlyName);
			this.d = d;
		}

		@Override
		public void run() {
			for (int i = 0; i < 3 && Thread.interrupted() != true; i++) {
				try {
					d.getDigitalOutput().toggle();
					Thread.sleep(400);
					d.getDigitalOutput().low();
					Thread.sleep(80);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			d.getDigitalOutput().low();

		}
	}

	private class BlinkTimeoutThread implements Runnable {

		Device d;

		public BlinkTimeoutThread(Device d) {
			System.out.println("BlinkTimeoutThread for " + d.friendlyName);
			this.d = d;
		}

		@Override
		public void run() {
			for (int i = 0; i < 1 && Thread.interrupted() != true; i++) {
				try {
					d.getDigitalOutput().toggle();
					Thread.sleep(1000);
					d.getDigitalOutput().low();
					Thread.sleep(80);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			d.getDigitalOutput().low();

		}
	}


}
