package ramenmachine.sensor;


public interface SensorInterface {
	public void turnOn();
	public void turnOff();
	public boolean isConditionSatisfied();
	public String getSensor();
}
