package ramenmachine.hw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

import ramenmachine.model.bean.Ingredient;
import ramenmachine.model.dao.IngredientDao;
import ramenmachine.sensor.SensorFactory;
import ramenmachine.sensor.SensorInterface;

public class HWController {
	private HashMap<String, SensorInterface> sensors;
	private HashMap<String, HWInterface> hws;
	private HashMap<String, Dispensor> dispensors;
	
	private MaintenanceThread maintenanceThread;
	
	public HWController(HashMap<String, SensorInterface> sensors, HashMap<String, HWInterface> hws, HashMap<String, Dispensor> dispensors) {
		this.sensors = sensors;
		this.hws = hws;
		this.dispensors = dispensors;
		
		maintenanceThread = new MaintenanceThread(sensors.get("WaterSensor"), hws.get("물"), sensors.get("Thermometer"), hws.get("HWBoiler"));
		maintainStatus();
	}
	
	public void maintainStatus() {
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
		
		while(list.hasNext()) {
			Ingredient ingr = list.next();
			dispensors.get(ingr.getName()).dispense(menu.get(ingr));
			System.out.println(ingr.getName() + "에서 " + menu.get(ingr) + " 개 출하합니다.");
		}
	}
	public static void main(String[] args) {
		HWController con = null;
		con.print();
		
		ArrayList<Ingredient> ingredients = new IngredientDao().getIngredientList();
		LinkedHashMap<Ingredient, Integer> menu = new LinkedHashMap<>(); 
		
		for(Ingredient ingr : ingredients) {
			if(ingr.getName().equals("일반면")) {
				menu.put(ingr, 1);
			} else if(ingr.getName().equals("진라면 스프")) {
				menu.put(ingr, 2);
			} else if(ingr.getName().equals("물 일반(550ml)")) {
				menu.put(ingr, 1);
			} else if(ingr.getName().equals("계란")) {
				menu.put(ingr, 1);
			} else if(ingr.getName().equals("대파")) {
				menu.put(ingr, 1);
			} else if(ingr.getName().equals("소시지")) {
				menu.put(ingr, 2);
			} else if(ingr.getName().equals("치즈")) {
				menu.put(ingr, 1);
			} 
		}
		System.out.println("-----------------------------------------------------");
		System.out.println("주문에 들어갑니다.");
		con.dispense(menu);
	}
}
