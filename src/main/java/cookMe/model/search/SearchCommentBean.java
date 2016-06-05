package cookMe.model.search;

import cookMe.model.comment.CommentModelBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Anis on 24/05/2016.
 */
@ManagedBean
@RequestScoped
public class SearchCommentBean extends CommentModelBean implements SearchCriteria<CommentModelBean> {

    public SearchCommentBean() {
        super(ALL_VALUES_INT, ALL_VALUES_INT, ALL_VALUES_STRING, ALL_VALUES_STRING);
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
            sql += " AND c.id_recipe =" + getRecipeModelBean().getId();
        if (getUserModelBean().getId() != ALL_VALUES_INT)
            sql += " AND c.id_user =" + getUserModelBean().getId();
        if (getComment() != ALL_VALUES_STRING)
            sql += " AND c.comment LIKE '" + getComment() + "'";
        try {
            Date parse = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(getDate());
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(parse);
            if (getDate() != ALL_VALUES_STRING)
                sql += " HAVING record_date = '" + parse + "'";
        } catch (ParseException e) {
            e.printStackTrace();
        }
        sql += " ORDER BY record_date DESC";
        return sql;


    }
}