package ramenmachine.controller;

import java.util.ArrayList;
import ramenmachine.model.bean.Ingredient;
import ramenmachine.model.dao.IngredientDao;

public class DBController {
	IngredientDao dao = new IngredientDao();
	
	public ArrayList<Ingredient> getIngredientList(){
		return dao.getIngredientList();
	}
	
	public void register() {
		
	}
	
	public void modify() {
		
	}
	
	public void remove() {
		
	}
}
