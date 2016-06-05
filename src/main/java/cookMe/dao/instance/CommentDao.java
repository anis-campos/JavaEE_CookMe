package cookMe.dao.instance;


import cookMe.model.EnumParser;
import cookMe.model.comment.CommentModelBean;
import cookMe.model.recipe.RecipeModelBean;
import cookMe.model.user.UserModelBean;
import cookMe.model.user.UserType;

import java.sql.*;
import java.text.SimpleDateFormat;

/**
 * Created by djbranbran on 24/05/16.
 */
public class CommentDao extends AbstractDao<CommentModelBean> {


    public CommentDao(String connectionString) {
        super(connectionString);
    }

    @Override
    protected PreparedStatement getSQLInsert(Connection con, CommentModelBean newItem) throws SQLException {
        PreparedStatement query = con.prepareStatement("INSERT INTO JAVA_ASI.comment( id_user, id_recipe, comment) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);
        query.setInt(1, newItem.getUserModelBean().getId());
        query.setInt(2, newItem.getRecipeModelBean().getId());
        query.setString(3, newItem.getComment());
        return query;
    }

    @Override
    protected PreparedStatement getSQLDelete(Connection con, CommentModelBean item) throws SQLException {
        PreparedStatement query = con.prepareStatement("DELETE FROM JAVA_ASI.comment c WHERE id_user=? AND  id_recipe=? ");
        query.setInt(1, item.getUserModelBean().getId());
        query.setInt(2, item.getRecipeModelBean().getId());
        return query;
    }

    @Override
    protected PreparedStatement getSQLUpdate(Connection con, CommentModelBean item) throws SQLException {
        PreparedStatement query = con.prepareStatement("UPDATE JAVA_ASI.comment SET comment = ? ");
        query.setString(1, item.getComment());
        return query;
    }

    @Override
    protected PreparedStatement getSQLGetAll(Connection con) throws SQLException {
        PreparedStatement query = con.prepareStatement(
                "SELECT * FROM JAVA_ASI.comment c " +
                        "JOIN JAVA_ASI.users u ON c.id_user = u.id " +
                        "JOIN JAVA_ASI.recipe r ON c.id_recipe=r.id");

        return query;

    }

    @Override
    protected PreparedStatement getSQLGetById(Connection con, int id) throws SQLException {
        //// TODO: 29/05/2016 Implementer cette méthode si nécessaire
        return null;
    }


    @Override
    protected CommentModelBean toObject(ResultSet rs) throws SQLException {
        return new CommentModelBean(
                new UserModelBean(rs.getInt("idUser"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        Integer.parseInt(rs.getString("age")),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("email"),
                        EnumParser.ParseWithDefault(UserType.class, rs.getString("user_type"))),
                new RecipeModelBean(rs.getString("title"),
                        rs.getString("description"),
                        Integer.parseInt(rs.getString("expertise")),
                        Integer.parseInt(rs.getString("nbpeople")),
                        Integer.parseInt(rs.getString("duration")),
                        rs.getString("recipe_type"),
                        rs.getString("image"),
                        rs.getInt("idRecipe")),
                rs.getString("comment"),
                new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(rs.getTimestamp("record_date")),
                rs.getInt("idComment")
        );
    }

}