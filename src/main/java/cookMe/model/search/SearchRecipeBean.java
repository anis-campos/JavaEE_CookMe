package cookMe.model.search;

import cookMe.model.recipe.RecipeModelBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Created by Anis on 24/05/2016.
 */
@ManagedBean
@RequestScoped
public class SearchRecipeBean extends RecipeModelBean implements SearchCriteria {
    private static final String ALL_VALUES_STRING = null;
    private static final int ALL_VALUES_INT = 0;

    public SearchRecipeBean(RecipeModelBean recipeModelBean) {
        this.setDescription(recipeModelBean.getDescription());
        this.setTitle(recipeModelBean.getTitle());
        this.setType(recipeModelBean.getType());
        this.setExpertise(recipeModelBean.getExpertise());
        this.setNbpeople(recipeModelBean.getNbpeople());
        this.setDuration(recipeModelBean.getDuration());
    }

    public SearchRecipeBean() {
        super(ALL_VALUES_STRING, ALL_VALUES_STRING, ALL_VALUES_INT, ALL_VALUES_INT, ALL_VALUES_INT, ALL_VALUES_STRING, ALL_VALUES_STRING, ALL_VALUES_INT);
    }

    @Override
    public String getSQLSearchQuery() {
        String sql = " SELECT * FROM recipe r WHERE 1=1 ";

        if (getDescription() != ALL_VALUES_STRING && getDescription() != "null")
            sql += " AND description = '" + getDescription() + "'";
        if (getTitle() != ALL_VALUES_STRING && getTitle() != "null")
            sql += " AND title = '" + getTitle() + "'";
        if (getType() != ALL_VALUES_STRING && getType() != "")
            sql += " AND type = '" + getType() + "'";
        if (getExpertise() != ALL_VALUES_INT)
            sql += " AND expertise = " + getExpertise();
        if (getNbpeople() != ALL_VALUES_INT)
            sql += " AND nbpeople = " + getNbpeople();
        if (getDuration() != ALL_VALUES_INT)
            sql += " AND duration = " + getDuration();
        if (getId() != ALL_VALUES_INT)
            sql += " AND id = " + getId();

        return sql;

    }

}