package com.electraic.activegames.tennis.device;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;

import org.eclipse.paho.client.mqttv3.*;

//import com.electraic.activegames.tennis.device.Device.Role;
import com.electraic.activegames.tennis.device.Device.State;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class Pinode implements DeviceListener { // implements MQTT {

	// ArrayList<Led> leds = new ArrayList<Led>();
	// ArrayList<Button> buttons = new ArrayList<Button>();
	// ArrayList<Sound> sounds = new ArrayList<Sound>();

	MqttClient mqttClient;
	
	public Pinode() {
		
		String broker = "tcp://localhost";
		String content = "Message from " + this.pinodeId;
		String topic = "pinodeEvent";
		int qos = 2;
		try {
			mqttClient = new MqttClient(broker, String.valueOf(this.pinodeId));
			MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            System.out.println("Connecting to broker: "+broker);
            mqttClient.connect(connOpts);
            System.out.println("Connected");
            System.out.println("Publishing message: "+content);
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);
            mqttClient.publish(topic, message);
            System.out.println("Message published");
            mqttClient.disconnect();
            System.out.println("Disconnected");
            System.exit(0);
        } catch(MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        }
		
		// TODO Auto-generated constructor stub
		Led led = new Led(RaspiPin.GPIO_02);
		led.setRole(Device.State.stSUCCESSFULHIT.getValue());
		led.setFriendlyName("Pinode 1 Success Led");

		Led led2 = new Led(RaspiPin.GPIO_03);
		led2.setRole(Device.State.stHITWAIT.getValue()
				| Device.State.stTIMEOUT.getValue()
				| Device.State.stFALSEHIT.getValue());
		led2.setFriendlyName("Pinode 1 Failure Led");

		Button button = new Button(RaspiPin.GPIO_07);
		button.setFriendlyName("Pinode 1 Button");
		button.addObserver(this);

		this.devices.add(led);
		this.devices.add(led2);
		this.devices.add(button);

		
	}

	ArrayList<Device> devices = new ArrayList<Device>();
	short pinodeId;

	State state;
	Timer timer;

public void setState(State state) {
		
		
		class StateChangeTimer extends TimerTask {
			Pinode p;
			public StateChangeTimer(Pinode p) {
				// TODO Auto-generated constructor stub
				this.p = p;
			}
		    public void run() {
		      System.out.println("Time's up!");
		      p.setState(State.stTIMEOUT);
		      
		    }
		  }
		
		
		timer = new Timer();
	    
	    
		System.out.println("Pinode state is changing to:" + state.toString());

		this.state = state;
		for (Device device : devices) {
			device.setState(state);
		}

		if (state == State.stHITWAIT) {

			timer.schedule(new StateChangeTimer(this), 3 * 1000);
			
		}

	}
	@Override
	public void notifyButtonChange(Pin pin, PinState pinState) {
		// TODO Auto-generated method stub
		switch (this.state) {
		case stIDLE:
			System.out.println("notifyButtonChange:" + pinState.toString());
			this.setState(State.stFALSEHIT);
			break;
		case stFALSEHIT:
			break;
		case stHITWAIT:
			this.setState(State.stSUCCESSFULHIT);
			timer.cancel();
			break;
		case stSUCCESSFULHIT: //Raporlamayi yap, sonra state'i idle'a degistir
			this.setState(State.stIDLE);
			
			break;
		case stTIMEOUT: //Raporlamayi yap, sonra state'i idle'a degistir
			this.setState(State.stIDLE);
			break;
		default:
			break;

		}
	}

	public void feedback() {
		// This function is to feed the current status to the game management
	}
}
