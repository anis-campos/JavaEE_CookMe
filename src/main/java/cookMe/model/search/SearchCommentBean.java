package cookMe.model.search;

import cookMe.model.comment.CommentModelBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Created by Anis on 24/05/2016.
 */
@ManagedBean
@RequestScoped
public class SearchCommentBean extends CommentModelBean implements SearchCriteria {
    private static final int ALL_VALUES_INT = -2;

    public SearchCommentBean() {
        this.setRecipeId(ALL_VALUES_INT);
    }

    public SearchCommentBean(CommentModelBean commentModelBean) {
        this.setComment(commentModelBean.getComment());
        this.setRecipeModelBean(commentModelBean.getRecipeModelBean());
        this.setUserModelBean(commentModelBean.getUserModelBean());
    }

    @Override
    public String getSQLSearchQuery() {

        String sql = " SELECT c.id_user idUser, u.firstname firstname, u.lastname lastname," +
                " u.age age, u.login login, u.password password, u.email email, u.type user_type," +
                " r.title title, r.description description, r.expertise expertise, r.nbpeople nbpeople," +
                " r.duration duration, r.type recipe_type, r.image image, r.id idRecipe, c.comment comment," +
                " CONVERT_TZ(c.record_date,'+00:00','+02:00') record_date" +
                " FROM comment c" +
                " INNER JOIN recipe r ON r.id = c.id_recipe" +
                " INNER JOIN users u ON u.id = c.id_user" +
                " WHERE 1=1 ";

        if (getRecipeModelBean().getId() != ALL_VALUES_INT)
            sql += "AND c.id_recipe =" + getRecipeModelBean().getId();

        sql += " ORDER BY record_date DESC";
        return sql;


    }
}