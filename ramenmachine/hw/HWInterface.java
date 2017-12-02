package ramenmachine.hw;

public interface HWInterface {
	public void turnOn();
	public void turnOff();
	public boolean getStatus();
	public String getHWId();
}
