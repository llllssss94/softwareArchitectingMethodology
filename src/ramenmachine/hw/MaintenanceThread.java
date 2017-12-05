package ramenmachine.hw;

import ramenmachine.sensor.SensorInterface;

public class MaintenanceThread {
	private WaterThread waterThread;
	private BoilerThread boilerThread;
	
	public MaintenanceThread(SensorInterface waterSensor, HWInterface waterTank, SensorInterface thermometer, HWInterface boiler) {
		waterThread = new WaterThread(waterSensor, waterTank);
		boilerThread = new BoilerThread(thermometer, boiler);
	}
	
	public void run() {
		waterThread.start();
		boilerThread.start();
	}
	
	public void stop() {
		waterThread.interrupt();
		boilerThread.interrupt();
	}
}
