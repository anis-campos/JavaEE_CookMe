package cookMe.processing;


import cookMe.dao.fabric.DaoFabric;
import cookMe.dao.instance.RecipesDao;
import cookMe.model.recipe.RecipeListModelBean;
import cookMe.model.recipe.RecipeModelBean;
import cookMe.model.recipe.RecipeSubmissionModelBean;
import cookMe.model.recipe.RecipeType;
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
public class RecipeControllerBean extends AbstractController<RecipeModelBean, RecipesDao, SearchRecipeBean> {


    public RecipeControllerBean() {
        super(DaoFabric.getInstance().createRecipesDao());
    }


    public void addRecipe(RecipeSubmissionModelBean recipe) {
        if (recipe.isValid()) {
            dao.create(recipe);
        }
    }

    public String searchRecipe(SearchRecipeBean recipe) {


        List<RecipeModelBean> fromCache = getFromCache(recipe);

        RecipeListModelBean recipeList;
        if (fromCache != null) {
            recipeList = new RecipeListModelBean(fromCache);
        } else {
            List<RecipeModelBean> search = dao.search(recipe);
            recipeList = new RecipeListModelBean(search);
            putIntoCache(recipe, search);
        }

        DataGridView dgv = new DataGridView<>(recipeList);

        Map<String, Object> requestMap = getSessionMap();

        //place la liste de recette dans l'espace de mémoire de JSF
        requestMap.put("dataGridView", dgv);

        return "resultSearch.jsf?faces-redirect=true";
    }

    public void update(RecipeSubmissionModelBean recipeSubmissionModelBean) {
        if (recipeSubmissionModelBean != null && recipeSubmissionModelBean.isValid())
            dao.update(recipeSubmissionModelBean);
    }

    public String displayRecipeDetail(RecipeModelBean recipe) {

        List<RecipeModelBean> listRecipe = getFromCache(lastFilter);
        Map<String, Object> requestMap = getSessionMap();
        for (RecipeModelBean bean : listRecipe) {
            if (bean.equals(recipe)) {
                requestMap.put("recipeModelBean", bean);
            }
        }

        return "recipeDetail.jsf?faces-redirect=true";
    }

    public List<String> getRecipeTypes() {
        return ParsableEnumToList(RecipeType.class);
    }

    public DataGridView<RecipeListModelBean, RecipeModelBean> getRecipeList() {
        List<RecipeModelBean> list = this.dao.getAll();
        RecipeListModelBean recipeList = new RecipeListModelBean();
        for (RecipeModelBean recipe : list) {
            recipeList.add(recipe);
        }
        return new DataGridView<>(recipeList);
    }

    public String goBack() {
        return searchRecipe(lastFilter);
    }

    public void remove(RecipeModelBean recipe) {
        dao.delete(recipe);
    }


}
