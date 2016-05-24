package step3.main;

import step3.dao.fabric.DaoFabric;
import step3.model.RecipeModelBean;
import step3.model.UserModelBean;

/**
 * Created by djbranbran on 24/05/16.
 */
public class Launcher {
	public static void main(String[] args){
		public static void main(String[] args) {
			UserDAO userDao=DaoFabric.getInstance().createUserDao();
			RecipesDao recipesDao=DaoFabric.getInstance().createRecipesDao();
			UserModelBean user1=new UserModelBean("Doe", "John",55, "jdoe", "pwd");
			
			RecipeModelBean recipe1=new RecipeModelBean("Fish Salad", "bla bla bal bla",5, 180, 10, "salad");
			RecipeModelBean recipe2=new RecipeModelBean("Fresh Meat", "bla bla bal bla",1, 20, 1, "meat");
			
			userDao.addUser(user1);
			recipesDao.addRecipe(recipe1);
			recipesDao.addRecipe(recipe2);
			
			List<UserModelBean> userList=userDao.getAllUser();
			List<RecipeModelBean> recipeList=recipesDao.getAllRecipes();
			
			for(UserModelBean userTmp:userList){
				System.out.println("User added:"+userTmp);
			}
			for
			(RecipeModelBean recipeTmp:recipeList){
				System.out.println("User added:"+recipeTmp);
			}
		}
	}
}
