package cookMe.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Created by Anis on 24/05/2016.
 */
@ManagedBean
@RequestScoped
public class SearchCommentBean extends CommentModelBean implements SearchCriteria {
    public static final String ALL_VALUES_STRING = "[ALL]";
    public static final int ALL_VALUES_INT = -2;

    public SearchCommentBean() {
        this.setRecipeId(ALL_VALUES_INT);
    }

    @Override
    public String getSQLSearchQuery() {

        String sql = " SELECT * FROM recipe r WHERE 1=1 ";

        if (getRecipeModelBean().getId() != ALL_VALUES_INT)
            sql += " AND r.id = " + getRecipeModelBean().getId();

        return sql;


    }
}