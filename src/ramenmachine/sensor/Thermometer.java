package ramenmachine.sensor;

public class Thermometer implements SensorInterface{
	private boolean status = false;
	private String uid = "";
	@Override
	public void turnOn() {
		// TODO Auto-generated method stub
		if(!status){
			status = true;
			System.out.println("온도계를 켭니다. (전원이 들어옴)");
		}
	}

	@Override
	public void turnOff() {
		// TODO Auto-generated method stub
		if(status){
			status = false;
			System.out.println("온도계를 끕니다. (전원을 끔)");
		}
	}

	@Override
	public boolean isConditionSatisfied() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String getSensor() {
		// TODO Auto-generated method stub
		return uid;
	}
	
}
