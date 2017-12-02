package ramenmachine.sensor;


public class SensorFactory {
	public SensorInterface createWaterSensor() {
		return new WaterSensor();
	}
	
	public SensorInterface createThermometer() {
		return new Thermometer();
	}
	
	public SensorInterface createPlateSensor() {
		return new PlateSensor();
	}
	
	public SensorInterface createIngredientSensor() {
		return new IngredientSensor();
	}
}
