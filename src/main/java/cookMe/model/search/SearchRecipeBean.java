package cookMe.model.search;

import cookMe.model.recipe.RecipeModelBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Created by Anis on 24/05/2016.
 */
@ManagedBean
@RequestScoped
public class SearchRecipeBean extends RecipeModelBean implements SearchCriteria<RecipeModelBean> {

    public SearchRecipeBean() {
        super(ALL_VALUES_STRING, ALL_VALUES_STRING, ALL_VALUES_INT, ALL_VALUES_INT, ALL_VALUES_INT, ALL_VALUES_STRING, ALL_VALUES_STRING, ALL_VALUES_INT);
    }

    @Override
    public String getSQLSearchQuery() {
        String sql = " SELECT * FROM recipe r WHERE 1=1 ";

        if (getDescription() != ALL_VALUES_STRING)
            sql += " AND description = '" + getDescription() + "'";
        if (getTitle() != ALL_VALUES_STRING)
            sql += " AND title = '" + getTitle() + "'";
        if (getType() != null)
            sql += " AND type = '" + getType().name() + "'";
        if (getExpertise() != ALL_VALUES_INT)
            sql += " AND expertise = " + getExpertise();
        if (getNbPeople() != ALL_VALUES_INT)
            sql += " AND nbpeople = " + getNbPeople();
        if (getDuration() != ALL_VALUES_INT)
            sql += " AND duration = " + getDuration();
        if (getId() != ALL_VALUES_INT)
            sql += " AND id = " + getId();

        return sql;

    }

}