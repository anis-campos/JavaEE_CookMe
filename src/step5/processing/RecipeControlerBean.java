package step5.processing;


import step5.dao.fabric.DaoFabric;
import step5.dao.instance.RecipesDao;
import step5.model.RecipeListModelBean;
import step5.model.RecipeModel;
import step5.model.SearchRecipeBean;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Anis on 24/05/2016.
 */
@ManagedBean(name = "step5UserControlerBean")
@ApplicationScoped
public class RecipeControlerBean {

    private RecipesDao recipeDao;

    public RecipeControlerBean() {
        this.recipeDao = DaoFabric.getInstance().createRecipesDao();
    }

    public void loadAllRecipe() {
        ArrayList<RecipeModel> list = this.recipeDao.getAllRecipes();
        RecipeListModelBean recipeList = new RecipeListModelBean();
        for (RecipeModel recipe : list) {
            recipeList.addRecipeList(recipe);
        }
        //récupère l'espace de mémoire de JSF
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        //place la liste de recette dans l'espace de mémoire de JSF
        sessionMap.put("recipeList", recipeList);
    }
    
    public String addRecipe(RecipeModel recipe){
    	recipeDao.addRecipe(recipe);
    	
    	return "successfulRegister.xhtml";
    }
    
    public String searchRecipe(RecipeModel recipe){
    	recipeDao.find((SearchRecipeBean)recipe);
    	return "recipeResultList.xhtml";
    }
}
