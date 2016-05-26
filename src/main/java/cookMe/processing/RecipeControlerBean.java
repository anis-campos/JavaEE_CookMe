package cookMe.processing;


import cookMe.dao.fabric.DaoFabric;
import cookMe.dao.instance.RecipesDao;
import cookMe.model.RecipeListModelBean;
import cookMe.model.RecipeModelBean;
import cookMe.model.SearchRecipeBean;
import cookMe.view.DataGridView;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.util.List;
import java.util.Map;

/**
 * Created by Anis on 24/05/2016.
 */
@ManagedBean
@ApplicationScoped
public class RecipeControlerBean {

    private RecipesDao recipeDao;

    public RecipeControlerBean() {
        this.recipeDao = DaoFabric.getInstance().createRecipesDao();
    }

    public void loadAllRecipe() {
        List<RecipeModelBean> list = this.recipeDao.getAllRecipes();
        RecipeListModelBean recipeList = new RecipeListModelBean();
        for (RecipeModelBean recipe : list) {
            recipeList.add(recipe);
        }
        //récupère l'espace de mémoire de JSF
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        //place la liste de recette dans l'espace de mémoire de JSF
        sessionMap.put("recipeList", recipeList);
    }
    
    public String addRecipe(RecipeModelBean recipe){
    	recipeDao.addRecipe(recipe);
    	
    	return "successfulRegister.xhtml";
    }
    
    public String searchRecipe(RecipeModelBean recipe){
        RecipeListModelBean recipeList = new RecipeListModelBean(recipeDao.find(recipe));

        DataGridView dgv = new DataGridView<RecipeListModelBean, RecipeModelBean>(recipeList);

        //récupère l'espace de mémoire de JSF
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> requestMap = externalContext.getRequestMap();
        //place la liste de recette dans l'espace de mémoire de JSF
        requestMap.put("dataGridView", dgv);

        return "resultSearch.jsf";
    }


    public String displayRecipeDetail(RecipeModelBean recipe){
        SearchRecipeBean searchRecipeBean = new SearchRecipeBean(recipe);
        List<RecipeModelBean> list =  recipeDao.find(searchRecipeBean);

        if(list.size() == 1){
            RecipeModelBean recipeBean = list.get(0);
            //récupère l'espace de mémoire de JSF
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            Map<String, Object> resquestMap = externalContext.getRequestMap();
            //place la liste de recette dans l'espace de mémoire de JSF
            resquestMap.put("recipeModelBean", recipeBean);
        }

        return "recipeDetail.jsf";
    }
}
