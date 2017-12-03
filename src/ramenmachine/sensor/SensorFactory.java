package ramenmachine.sensor;

public class SensorFactory {
	public static SensorInterface createWaterSensor() {
		return new WaterSensor();
	}
	
	public static SensorInterface createThermometer() {
		return new Thermometer();
	}
	
	public static SensorInterface createPlateSensor() {
		return new PlateSensor();
	}
	
	public static SensorInterface createIngredientSensor(String uid, String name) {
		return new IngredientSensor(uid, name);
	}
}
