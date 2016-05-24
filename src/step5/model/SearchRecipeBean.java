package step5.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Created by Anis on 24/05/2016.
 */
@ManagedBean
@RequestScoped
public class SearchRecipeBean extends RecipeModel {
    public static final String ALL_VALUES_STRING = "[ALL]";
    public static final int ALL_VALUES_INT = -2;

    public SearchRecipeBean() {
        this.setDescription(ALL_VALUES_STRING);
        this.setTitle(ALL_VALUES_STRING);
        this.setType(ALL_VALUES_STRING);
        this.setExpertise(ALL_VALUES_INT);
        this.setNbpeople(ALL_VALUES_INT);
        this.setDuration(ALL_VALUES_INT);
    }


    public String getSQLSearchQuery() {

        String sql = " SELECT * FROM recipe r WHERE 1=1 ";

        if (getDescription() != ALL_VALUES_STRING)
            sql += " AND description = '" + getDescription() +"'";
        if (getTitle() != ALL_VALUES_STRING)
            sql += " AND title = '" + getTitle() + "'";
        if (getType() != ALL_VALUES_STRING)
            sql += " AND type = '" + getType() +"'";
        if (getExpertise() != ALL_VALUES_INT)
            sql += " AND expertise = " + getExpertise();
        if (getNbpeople() != ALL_VALUES_INT)
            sql += " AND nbpeople = " + getNbpeople();
        if (getDuration() != ALL_VALUES_INT)
            sql += " AND duration = " + getDuration();

        return sql;


    }
}