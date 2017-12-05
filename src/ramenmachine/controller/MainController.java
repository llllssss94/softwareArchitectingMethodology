package ramenmachine.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Scanner;

import ramenmachine.hw.HWController;
import ramenmachine.hw.HWInterface;
import ramenmachine.model.bean.Ingredient;

import ramenmachine.payment.PaymentController;
import ramenmachine.sensor.SensorInterface;

public class MainController {
	private ArrayList<Ingredient> ingredients;
	private PaymentController paymentController = new PaymentController();
	private HWController hwController = new HWController();
	
	private HashMap<String, SensorInterface> sensors;
	private HashMap<String, HWInterface> hws;

	private String defaultNoodle = "일반 면";
	private String defaultSoup = "진라면 스프";
	private String defaultSeasoning = "건더기 스프";
	private String defaultWater = "물 (550ml)";

	private Scanner scan = new Scanner(System.in);

	public ArrayList<Ingredient> getMenu() {
		return ingredients;
	}

	public void initMachine() {
		
		ingredients = hwController.getIngredients();
		hws = hwController.getHWs();
		sensors = hwController.getSensors();

		turnOn();
	}

	public void turnOn() {
		HWInterface induction = hws.get("HWInductionHeater");
		Iterator<?> list = hws.values().iterator();
		while (list.hasNext()) {
			HWInterface hw = (HWInterface) list.next();
			if(!hw.equals(induction)) {
				(hw).turnOn();
			}
		}
		list = sensors.values().iterator();
		while (list.hasNext()) {
			((SensorInterface) list.next()).turnOn();
		}
	}

	public void turnOff() {
		Iterator<?> list = hws.values().iterator();
		while (list.hasNext()) {
			((HWInterface) list.next()).turnOff();
		}
		list = sensors.values().iterator();
		while (list.hasNext()) {
			((SensorInterface) list.next()).turnOff();
		}
	}

	public void showInitScreen() {
		String select = "";
		
		while (!select.equals("quit")) {
			System.out.println("====================================================");
			System.out.println("1.preset ramen \n" + "(" + defaultNoodle 
					+ ", " + defaultSoup  
					+ ", " + defaultSeasoning
					+ ", " + defaultWater
					+ ")\n" + "2. customizing ramen ");
			select = scan.nextLine();

			if (select.contains("1")) {
				// 1. 면종류(종류, 량), 2-1 스프(종류, 맛타입), 2-2 물(맛타입), 3.토핑(종류, 량)[]
				
				LinkedHashMap<Ingredient, Integer> menu = new LinkedHashMap<>();
				menu.put(ingredients.get(0), 1);
				menu.put(searchIngredient("일반 면"), 1);
				menu.put(searchIngredient("진라면"), 1);
				menu.put(searchIngredient("건더기"), 1);
				menu.put(searchIngredient("물(550ml)"), 550);
				
				readyPayment(menu);
				
				System.out.println("라면 제조에 들어갑니다.");
				hwController.dispense(menu);
				System.out.println("라면이 완성됐습니다.");
				
			} else if (select.contains("2")) {
				LinkedHashMap<Ingredient, Integer> menu = new LinkedHashMap<>();
				
				menu.put(ingredients.get(0), 1);
				printIngredient("면", menu);
				printIngredient("스프", menu);
				menu.put(searchIngredient("건더기"), 1);
				printIngredient("물", menu);
				printIngredient("토핑", menu);

				readyPayment(menu);
				
				System.out.println("라면 제조에 들어갑니다.");
				hwController.dispense(menu);
				System.out.println("라면이 완성됐습니다.");
			} else {
				System.out.println("reselect please");
			}
		}
	}

	public Ingredient searchIngredient(String name) {
		for (Ingredient ingr : ingredients) {
			if (ingr.getName().contains(name)) {
				return ingr;
			}
		}
		return null;
	}

	public void readyPayment(LinkedHashMap<Ingredient, Integer> menu) {
		int amount = 1000;
		Iterator<Ingredient> list = menu.keySet().iterator();
		while (list.hasNext()) {
			Ingredient ingr = list.next();
			if(ingr.getName().contains("물")) {
				System.out.println(ingr.getName() + "의 양은 " + menu.get(ingr) + "ml입니다.");
			} else {
				System.out.println(ingr.getName() + "의 갯수는 " + menu.get(ingr) + "개 입니다.");
			}
			amount += ingr.getPrice() * menu.get(ingr);
		}
		System.out.println("가격은 " + amount + "입니다.");
		String type = "";
		while (type.compareTo("card") != 0 && type.compareTo("cash") != 0) {
			System.out.println("결제 수단을 선택해 주세요.\n 1.현금, 2카드");
			type = scan.nextLine();
			if (type.equals("1")) {
				type = "cash";
			} else if (type.equals("2")) {
				type = "card";
			}
		}
		paymentController.handlePayment(type, amount);
	}
	
	public void printIngredient(String type, LinkedHashMap<Ingredient, Integer> menu) {
		boolean flag = true;
		while(flag) {
			ArrayList<Ingredient> list = new ArrayList<>();
			for(Ingredient ingr:ingredients) {
				if(ingr.getType().contains(type)) {
					list.add(ingr);
				}
			}
			System.out.println("--------------------------------------------------------");
			System.out.println(type +" 선택입니다.");

			if(type.equals("토핑")) {
				Ingredient ingredient = new Ingredient();
				ingredient.setName("추가 그만.");
				list.add(ingredient);
			}
			
			for(int i = 0; i < list.size(); i++) {
				if(i == list.size()-1) {
					System.out.print(i+1 + ". "+ list.get(i).getName());
				} else {
					System.out.print(i+1 + ". "+ list.get(i).getName() + ", ");
				}
			}
			System.out.println();
			
			String command = scan.nextLine();
			int value = 0;
			try {
				value = Integer.parseInt(command);
				list.get(value-1);
			} catch (Exception e) {
				continue;
			}
			
			if(!type.equals("토핑")) {
				flag = false;
			} else if(value == list.size()) {
				break;
			}
			
			if(type.equals("물")) {
				String name = list.get(value-1).getName();
				menu.put(list.get(value-1), Integer.parseInt(name.substring(2,5)));
			} else if(type.equals("토핑")) {
				menu.put(list.get(value-1), menu.containsKey(list.get(value-1)) ? menu.get(list.get(value-1)) + 1 : 1);
			} else {
				menu.put(list.get(value-1), 1);
			}
			
		}
	}
}
