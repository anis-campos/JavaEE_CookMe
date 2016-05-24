package step5.model;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Created by djbranbran on 24/05/16.
 */
@ManagedBean
@SessionScoped
public class RecipeListModelBean {
	
	private List<RecipeModel> recipeList;
	
	public RecipeListModelBean(){
		recipeList = new ArrayList<RecipeModel>();
	}
	
	public void addRecipeList(RecipeModel recipe){
		this.recipeList.add(recipe);
	}
	
	public List<RecipeModel> getRecipeList(){
		return recipeList;
	}
}
