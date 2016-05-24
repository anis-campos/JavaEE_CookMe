package step3.main;

import step3.dao.fabric.DaoFabric;
import step3.model.UserModelBean;

/**
 * Created by djbranbran on 24/05/16.
 */
public class Launcher {
	public static void main(String[] args){
		UserDAO userDao=DaoFabric.getInstance().createUserDao();
		RecipesDao recipesDao=DaoFabric.getInstance().createRecipesDao();
		UserModelBean user1=new UserModelBean("Doe", "John", 55, "jdoe", "pwd");
	}
}
