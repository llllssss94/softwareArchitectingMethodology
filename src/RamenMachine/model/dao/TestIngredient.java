package ramenmachine.model.dao;

import java.util.ArrayList;

import ramenmachine.model.bean.Ingredient;

public class TestIngredient {
	public static void main(String[] args) {
		IngredientDao dao = new IngredientDao();
		ArrayList<Ingredient> list = dao.getIngredientList();
		
		for(Ingredient ingr : list) {
			System.out.println(ingr.getName() +"의 가격은 " + ingr.getPrice() + "입니다. 유형은" + ingr.getType() + "입니다.");
		}
		
		Ingredient ingredient = new Ingredient();
		ingredient.setName("Test");
		ingredient.setPrice(2000);
		ingredient.setType("토핑");
		
		dao.registerIngredient(ingredient);
		dao.registerIngredient(ingredient);
		
		ingredient.setNum(19);
		ingredient.setPrice(1000);
		dao.modifyIngredient(ingredient);
		
		dao.removeIngredient(21);
	}
}
