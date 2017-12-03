package ramenmachine.hw;

import ramenmachine.sensor.SensorInterface;

public class BoilerThread extends Thread {
	private SensorInterface thermometer;
	private HWInterface boiler;
	private boolean flag = false;
	
	public BoilerThread(SensorInterface thermometer, HWInterface boiler) {
		this.thermometer = thermometer;
		this.boiler = boiler;
		this.flag = true;
	}
	
	public void run() {
		while(flag) {
			if(!thermometer.isConditionSatisfied()) {
				boiler.turnOn();
			} else {
				boiler.turnOff();
			}
		}
	}
	
	public void interrupt() {
		flag = false;
		super.interrupt();
	}
}
