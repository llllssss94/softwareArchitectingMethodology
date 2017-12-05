package ramenmachine.sensor;

public class WaterSensor implements SensorInterface{
	private String uid = "UID0011";
	private boolean status = false;
	
	@Override
	public void turnOn() {
		// TODO Auto-generated method stub
		if(!status) {
			status = true;
			System.out.println("워터 센서를 켭니다.(전원이 들어옴)");
		}
	}

	@Override
	public void turnOff() {
		// TODO Auto-generated method stub
		if(status) {
			status = false;
			System.out.println("워터 센서를 끕니다.(전원을 끔)");
		}
	}

	@Override
	public boolean isConditionSatisfied() {
		return true;
	}

	@Override
	public String getSensorId() {
		return uid;
	}
	
}
