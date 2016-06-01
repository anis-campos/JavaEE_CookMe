package cookMe.processing;


import cookMe.dao.fabric.DaoFabric;
import cookMe.dao.instance.RecipesDao;
import cookMe.model.CommentModelBean;
import cookMe.model.recipe.RecipeListModelBean;
import cookMe.model.recipe.RecipeModelBean;
import cookMe.model.search.SearchRecipeBean;
import cookMe.view.DataGridView;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.List;
import java.util.Map;

/**
 * Created by Anis on 24/05/2016.
 */
@ManagedBean
@ApplicationScoped
public class RecipeControlerBean extends AbstractControler<RecipeModelBean, RecipesDao, SearchRecipeBean> {


    private Object recipeList;

    public RecipeControlerBean() {
        super(DaoFabric.getInstance().createRecipesDao());
    }

    public void loadAllRecipe() {
        List<RecipeModelBean> list = this.dao.getAll();
        RecipeListModelBean recipeList = new RecipeListModelBean();
        for (RecipeModelBean recipe : list) {
            recipeList.add(recipe);
        }
        Map<String, Object> sessionMap = getSessionMap();
        sessionMap.put("recipeList", recipeList);
    }

    public String addRecipe(RecipeModelBean recipe) {
        //TODO: 29/05/2016 :  controler les valeurs de la recette.
        dao.create(recipe);
        return "successfulRegister.xhtml";
    }

    public String searchRecipe(RecipeModelBean recipe) {
        SearchRecipeBean searchRecipeBean = new SearchRecipeBean(recipe);
        List<RecipeModelBean> search = dao.search(searchRecipeBean);
        RecipeListModelBean recipeList = new RecipeListModelBean(search);

        DataGridView dgv = new DataGridView<>(recipeList);

        Map<String, Object> requestMap = getSessionMap();

        putIntoCache(searchRecipeBean, search);

        //place la liste de recette dans l'espace de mémoire de JSF
        requestMap.put("dataGridView", dgv);

        return "resultSearch.jsf";
    }


    public String displayRecipeDetail(RecipeModelBean recipe) {
        SearchRecipeBean searchRecipeBean = new SearchRecipeBean(recipe);

        List<RecipeModelBean> listRecipe = getFromCache(lastFilter);
        Map<String, Object> requestMap = getSessionMap();
        for (RecipeModelBean bean : listRecipe) {
            if (bean.equals(recipe)) {
                requestMap.put("recipeModelBean", bean);
            }
        }

        return "recipeDetail.jsf?faces-redirect=true";
    }


    public DataGridView<RecipeListModelBean, RecipeModelBean> getRecipeList() {
        List<RecipeModelBean> list = this.dao.getAll();
        RecipeListModelBean recipeList = new RecipeListModelBean();
        for (RecipeModelBean recipe : list) {
            recipeList.add(recipe);
        }
        return new DataGridView<>(recipeList);
    }

    public void remove(RecipeModelBean recipe) {
        dao.delete(recipe);
    }
}
