package ramenmachine.sensor;

public class IngredientSensor implements SensorInterface{
	private String uid;
	private String name;
	private boolean status = false;
	
	public IngredientSensor(String uid, String name) {
		this.uid = uid;
		this.name = name;
	}
	
	@Override
	public void turnOn() {
		// TODO Auto-generated method stub
		if(!status){
			status = true;
			System.out.println("재료 센서를 켭니다. ("+name+")");
		}
	}

	@Override
	public void turnOff() {
		// TODO Auto-generated method stub
		if(status){
			status = false;
			System.out.println("재료 센서를 끕니다. ("+name+")");
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
