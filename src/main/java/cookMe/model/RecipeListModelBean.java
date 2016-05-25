package cookMe.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by djbranbran on 24/05/16.
 */
@ManagedBean
@SessionScoped
public class RecipeListModelBean {
	
	private List<RecipeModelBean> recipeList;
	
	public RecipeListModelBean(){
		recipeList = new ArrayList<RecipeModelBean>();
	}
	
	public void addRecipeList(RecipeModelBean recipe){
		this.recipeList.add(recipe);
	}
	
	public List<RecipeModelBean> getRecipeList(){
		return recipeList;
	}
}
