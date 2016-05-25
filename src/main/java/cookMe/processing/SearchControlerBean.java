package cookMe.processing;

import cookMe.dao.fabric.DaoFabric;
import cookMe.dao.instance.RecipesDao;
import cookMe.model.RecipeModelBean;
import cookMe.model.RecipeSubmissionModelBean;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by djbranbran on 25/05/16.
 */
@ManagedBean
@ApplicationScoped
public class SearchControlerBean {


    private RecipeSubmissionModelBean recipeSearch;
    private List<String> listType;

    private RecipesDao recipeDao;

    public SearchControlerBean() {
        this.recipeDao = DaoFabric.getInstance().createRecipesDao();
        List<RecipeModelBean>list = this.recipeDao.getAllRecipes();
        for(RecipeModelBean rmb : list){
            if(rmb.getType() != null) {
                this.listType.add(rmb.getType());
            }
        }

    }

    public List<String> getListType() {
        return listType;
    }

    public void setListType(List<String> listType) {
        this.listType = listType;
    }

    public RecipeSubmissionModelBean getRecipeSearch() {
        return recipeSearch;
    }

    public void setRecipeSearch(RecipeSubmissionModelBean recipeSearch) {
        this.recipeSearch = recipeSearch;
    }
}
