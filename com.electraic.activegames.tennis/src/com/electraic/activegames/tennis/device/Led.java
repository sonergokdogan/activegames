package com.electraic.activegames.tennis.device;

import com.pi4j.io.gpio.PinState;

public class Led extends Device implements DeviceAction {

	public Led() {
		// TODO Auto-generated constructor stub
		this.digitalOutput = this.getGpioController()
				.provisionDigitalOutputPin(this.getPin(), this.friendlyName, PinState.LOW);
	}

	@Override
	public void act() {
		// TODO Auto-generated method stub

		switch (this.role) {
		case WAIT:
			break;
		case SUCCESS:
			new Thread(new BlinkSuccessThread(this)).start();
			break;
		case FAILURE:
			new Thread(new BlinkFailureThread(this)).start();
			break;
		case TIMEOUT:
			new Thread(new BlinkTimeoutThread(this)).start();
			break;


		}
	}

	@Override
	public void react() {
		// TODO Auto-generated method stub

	}

	private class BlinkSuccessThread implements Runnable {

		Device d;

		public BlinkSuccessThread(Device d) {
			this.d = d;
		}

		@Override
		public void run() {
			for (int i = 0; i < 5; i++) {
				try {
					d.getDigitalOutput().toggle();
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
			this.d = d;
		}

		@Override
		public void run() {
			for (int i = 0; i < 3; i++) {
				try {
					d.getDigitalOutput().toggle();
					Thread.sleep(400);
					d.getDigitalOutput().low();
					Thread.sleep(80);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
	}

	private class BlinkTimeoutThread implements Runnable {

		Device d;

		public BlinkTimeoutThread(Device d) {
			this.d = d;
		}

		@Override
		public void run() {
			for (int i = 0; i < 1; i++) {
				try {
					d.getDigitalOutput().toggle();
					Thread.sleep(1000);
					d.getDigitalOutput().low();
					Thread.sleep(80);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
	}


}
