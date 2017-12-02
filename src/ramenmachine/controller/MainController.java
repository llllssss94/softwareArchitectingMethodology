package ramenmachine.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Scanner;

import ramenmachine.hw.Dispensor;
import ramenmachine.hw.HWController;
import ramenmachine.hw.HWFactory;
import ramenmachine.hw.HWInterface;
import ramenmachine.model.bean.Ingredient;

import ramenmachine.model.dao.IngredientDao;
import ramenmachine.sensor.SensorFactory;
import ramenmachine.sensor.SensorInterface;


public class MainController {
	private ArrayList<Ingredient> ingredientList;
	/*private PaymentController paymentController = new PaymentController();
	private DBController dbController = new DBController();
	private HWController hwController = new HWController();*/
	
	private HashMap<String, SensorInterface> sensors;
	private HashMap<String, HWInterface> hws;
	private HashMap<String, Dispensor> dispensors;

	private HWController hwController;
	private String defaultNoodle = "진라면";
	private String defaultSoup = "보통맛보통양";
	private String defaultWater = "보통물";
	private String defaultSeasoning = "기본건더기스프";

	private String noodle = "";
	private String soup = "";
	private String water = "";
	private String seasoning = "";

	private int price;

	public ArrayList<Ingredient> getMenu(){
		return ingredientList;
	}
	
	public void initMachine() {
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
		
		ArrayList<Ingredient> ingredients = new IngredientDao().getIngredientList();
		for(Ingredient ingr : ingredients) {
			Object obj = HWFactory.createHWIngredient(ingr.getHwId(),ingr.getName());
			hws.put(ingr.getName(), (HWInterface)obj);
			dispensors.put(ingr.getName(), (Dispensor)obj);
			sensors.put(ingr.getName(), SensorFactory.createIngredientSensor(ingr.getSensorId(), ingr.getName()));
		}
		
		hwController = new HWController(sensors, hws, dispensors);
		
		turnOn();
	}
	
	
	public void turnOn() {
		Iterator<?> list = hws.values().iterator();
		while(list.hasNext()) {
			((HWInterface)list.next()).turnOn();
		}
		list = sensors.values().iterator();
		while(list.hasNext()) {
			((SensorInterface)list.next()).turnOn();
		}
	}
	
	public void turnOff() {
		Iterator<?> list = hws.values().iterator();
		while(list.hasNext()) {
			((HWInterface)list.next()).turnOff();
		}
		list = sensors.values().iterator();
		while(list.hasNext()) {
			((SensorInterface)list.next()).turnOff();
		}
	}


	public void showInitScreen()
	{
		Scanner scan = new Scanner(System.in);
		String select = "";
		ingredientList = new IngredientDao().getIngredientList();
		
		while(!select.equals("quit"))
		{
			System.out.println("====================================================");
			System.out.println("1.preset ramen \n"
					+ "(" + defaultNoodle + defaultSoup + defaultWater + defaultSeasoning + ")\n"
					+ "2. customizing ramen ");
			select = scan.nextLine();

			if(select.contains("1"))
			{
				//1. 면종류(종류, 량), 2-1 스프(종류, 맛타입), 2-2 물(맛타입), 3.토핑(종류, 량)[]
				price = 1000;
				System.out.println(defaultNoodle + defaultSoup + defaultWater + defaultSeasoning);
				System.out.println("Price : " + price);
				System.out.println("====================================================");
			}
			else if(select.contains("2"))
			{
				LinkedHashMap<Ingredient, Integer> menu = new LinkedHashMap<>();
				
				Loop1 : while(true)
				{
					System.out.println("select noodle type : "
							+ "(1.굵은 2.일반 3.얇은 4.우동)");
					noodle = scan.nextLine();
	
					switch(noodle)
					{
					case "1":
						menu.put(searchIngredient("굵은 면"), 1);
						break Loop1;
					case "2":
						menu.put(searchIngredient("일반 면"), 1);
						break Loop1;
					case "3":
						menu.put(searchIngredient("얇은 면"), 1);
						break Loop1;
					default:
						break;
					}
				}

				Loop2 : while(true)
				{
					System.out.println("select soup type : "
							+ "(1.신라면 2.안성탕면 3.진라면 4.우동)");
					soup = scan.nextLine();
					
					switch(soup)
					{
					case "1":
						menu.put(searchIngredient("신라면"), 1);
						break Loop2;
					case "2":
						menu.put(searchIngredient("안성탕면"), 1);
						break Loop2;
					case "3":
						menu.put(searchIngredient("진라면"), 1);
						break Loop2;
					case "4":
						menu.put(searchIngredient("우동"), 1);
						break Loop2;
					default:
						break;
					}
				}
				
				Loop3 : while(true)
				{
					System.out.println("select water type : "
							+ "(1.많이 2.일반 3.적게");
					water = scan.nextLine();
					
					switch(water)
					{
					case "1":
						menu.put(searchIngredient("물"), 3);
						break Loop3;
					case "2":
						menu.put(searchIngredient("물"), 2);
						break Loop3;
					case "3":
						menu.put(searchIngredient("물"), 1);
						break Loop3;
					default:
						break;
					}
				}
				
				Loop4 : while(true)
				{
					System.out.println("select additional seasoning : "
							+ "1.계란 2.대파 3.마늘 4.치즈 5.소시지 6.스팸 7.그만");
					seasoning = scan.nextLine();
					
					switch(seasoning)
					{
					case "1":
						menu.put(searchIngredient("계란"), menu.containsKey(searchIngredient("계란"))? menu.get(searchIngredient("계란"))+1 : 1);
						break;
					case "2":
						menu.put(searchIngredient("대파"), menu.containsKey(searchIngredient("대파"))? menu.get(searchIngredient("대파"))+1 : 1);
						break;
					case "3":
						menu.put(searchIngredient("마늘"), menu.containsKey(searchIngredient("마늘"))? menu.get(searchIngredient("마늘"))+1 : 1);
						break;
					case "4":
						menu.put(searchIngredient("치즈"), menu.containsKey(searchIngredient("치즈"))? menu.get(searchIngredient("치즈"))+1 : 1);
						break;
					case "5":
						menu.put(searchIngredient("소시지"), menu.containsKey(searchIngredient("소시지"))? menu.get(searchIngredient("소시지"))+1 : 1);
						break;
					case "6":
						menu.put(searchIngredient("스팸"), menu.containsKey(searchIngredient("스팸"))? menu.get(searchIngredient("스팸"))+1 : 1);
						break;
					case "7":
						break Loop4;
					default:
						break;
					}
				}
				Iterator<Ingredient>list = menu.keySet().iterator();
				while(list.hasNext()) {
					Ingredient ingr = list.next();
					System.out.println(ingr.getName()+"의 갯수는 "+menu.get(ingr)+"개 입니다.");
				}
				hwController.dispense(menu);
			}
			else
			{
				System.out.println("reselect please");
			}
		}
	}

	public Ingredient searchIngredient(String name)
	{
		for(Ingredient ingr: ingredientList) 
		{
			if(ingr.getName().contains(name)) 
			{
				return ingr;
			}
		}
		return null;
	}

	public void cookRamen() 
	{
		//메인메뉴짜자
	}
}
