package ramenmachine.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import ramenmachine.model.bean.Ingredient;

/**
 * 
 * @author Park SangHee
 *
 */
public class IngredientDao {
    private SqlSessionFactory sqlSessionFactory = SqlSessionManager.getSqlSession();
    
    public ArrayList<Ingredient> getIngredientList() {
    	SqlSession session = sqlSessionFactory.openSession();
    	ArrayList<Ingredient> list = null;
    	
    	try {
    		list  = new ArrayList<>(session.selectList("ramenmachine.model.sqlmap.IngredientMapper.getIngredientList"));
    		session.commit();
    	} catch (Exception e) {
    		e.printStackTrace();
		} finally {
    		session.close();
    	}

		return list;
    }
    
    public void registerIngredient(Ingredient ingredient) {
    	SqlSession session = sqlSessionFactory.openSession();
    	try {
    		session.insert("ramenmachine.model.sqlmap.IngredientMapper.registerIngredient", ingredient);
    		session.commit();
    	} catch (Exception e) {
    		e.printStackTrace();
		}	
    }
    
    public void modifyIngredient(Ingredient ingredient) {
    	SqlSession session = sqlSessionFactory.openSession();
    	try {
    		session.update("ramenmachine.model.sqlmap.IngredientMapper.modifyIngredient", ingredient);
    		session.commit();
    	} catch (Exception e) { 
    		e.printStackTrace();
		}	
    }
    
    public void removeIngredient(int ingredientNum) {
    	SqlSession session = sqlSessionFactory.openSession();
    	try {
    		session.delete("ramenmachine.model.sqlmap.IngredientMapper.removeIngredient", ingredientNum);
    		session.commit();
    	} catch (Exception e) {
    		e.printStackTrace();
		}	
    }
}
