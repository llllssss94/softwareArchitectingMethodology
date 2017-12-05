package ramenmachine.hw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import ramenmachine.model.bean.Ingredient;
import ramenmachine.model.dao.IngredientDao;
import ramenmachine.sensor.SensorFactory;
import ramenmachine.sensor.SensorInterface;

public class HWController {
	private HashMap<String, SensorInterface> sensors;
	private HashMap<String, HWInterface> hws;
	private HashMap<String, Dispensor> dispensors;
	private ArrayList<Ingredient> ingredients;
	
	private MaintenanceThread maintenanceThread;
	
	public HWController() {
		hws = new HashMap<>();
		sensors = new HashMap<>();
		dispensors = new HashMap<>();

		Collections.synchronizedMap(sensors);
		Collections.synchronizedMap(hws);
		Collections.synchronizedMap(dispensors);

		hws.put("HWInductionHeater", HWFactory.createHWInductionHeater());
		hws.put("HWBoiler", HWFactory.createHWBoiler());

		sensors.put("WaterSensor", SensorFactory.createWaterSensor());
		sensors.put("Thermometer", SensorFactory.createThermometer());
		sensors.put("PlateSensor", SensorFactory.createPlateSensor());
		
		ingredients = new IngredientDao().getIngredientList();
		for (Ingredient ingr : ingredients) {
			String name = ingr.getName();
			if(ingr.getType().equals("물")) {
				name = "물";
				if(!hws.containsKey("물")){
					Object obj = HWFactory.createHWWaterTank();
					hws.put(name, (HWInterface) obj);
					dispensors.put(name, (Dispensor) obj);
					sensors.put(name, SensorFactory.createIngredientSensor(ingr.getSensorId(), name));
				}
			} else {
				Object obj = HWFactory.createHWIngredient(ingr.getHwId(), name);
				hws.put(name, (HWInterface) obj);
				dispensors.put(name, (Dispensor) obj);
				sensors.put(name, SensorFactory.createIngredientSensor(ingr.getSensorId(), name));
			}
		}
		
		maintenanceThread = new MaintenanceThread(sensors.get("WaterSensor"), hws.get("물"), sensors.get("Thermometer"), hws.get("HWBoiler"));
		run();
	}
	
	public ArrayList<Ingredient> getIngredients() {
		return ingredients;
	}
	
	public HashMap<String, HWInterface> getHWs() {
		return hws;
	}
	
	public HashMap<String, SensorInterface> getSensors() {
		return sensors;
	}
	
	public void run() {
		maintenanceThread.run();
	}
	
	public void stop() {
		maintenanceThread.stop();
	}
	
	public void print() {
		System.out.println("HW 목록을 출력합니다.");
		Iterator<String> list = hws.keySet().iterator();
		while(list.hasNext()) {
			System.out.println(list.next()+" HW 입니다.");
		}
		
		System.out.println("-----------------------------------------");
		System.out.println("Sensor 목록을 출력합니다.");
		list = sensors.keySet().iterator();
		while(list.hasNext()) {
			System.out.println(list.next()+" Sensor 입니다.");
		}
		
		System.out.println("-----------------------------------------");
		System.out.println("Dispensor 목록을 출력합니다.");
		list = dispensors.keySet().iterator();
		while(list.hasNext()) {
			System.out.println(list.next()+" Dispensor 입니다.");
		}
		
	}
	
	public void dispense(HashMap<Ingredient, Integer> menu) {
		Iterator<Ingredient> list = menu.keySet().iterator();
		boolean boil = false;
		while(list.hasNext()) {
			Ingredient ingr = list.next();
			if(ingr.getType().equals("그릇")) {
				dispensors.get(ingr.getName()).dispense(menu.get(ingr));
			} else if(ingr.getType().equals("면")) {
				while(!sensors.get("PlateSensor").isConditionSatisfied()){
					System.out.println("그릇이 Plate 위에 없습니다.");
				}
				dispensors.get(ingr.getName()).dispense(menu.get(ingr));
			} else if(ingr.getType().equals("물")){
				dispensors.get("물").dispense(menu.get(ingr));
			} else if(ingr.getType().equals("토핑") && !boil) {
				boil = true;
				System.out.println("1분이 지나 물이 끓기 시작합니다.");
				dispensors.get(ingr.getName()).dispense(menu.get(ingr));
			} else {
				dispensors.get(ingr.getName()).dispense(menu.get(ingr));
			}
		}
	}
}
