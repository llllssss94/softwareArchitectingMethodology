package ramenmachine.hw;

import ramenmachine.sensor.SensorInterface;

public class WaterThread extends Thread {
	private SensorInterface waterSensor;
	private HWInterface waterTank;
	private boolean flag = false;
	
	public WaterThread(SensorInterface waterSensor, HWInterface waterTank) {
		this.waterSensor = waterSensor;
		this.waterTank = waterTank;
		this.flag = true;
	}
	
	public void run() {
		while(flag) {
			if(!waterSensor.isConditionSatisfied()) {
				waterTank.turnOn();
			} else {
				waterTank.turnOff();
			}
		}
	}
	
	public void interrupt() {
		flag = false;
		super.interrupt();
	}
}
