package cookMe.dao.instance;


import cookMe.model.recipe.RecipeModelBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by djbranbran on 24/05/16.
 */
public class RecipesDao extends AbstractDao<RecipeModelBean> {

    public RecipesDao(String connectionString) {
        super(connectionString);
    }

    @Override
    protected PreparedStatement getSQLInsert(Connection con, RecipeModelBean newItem) throws SQLException {
        PreparedStatement query = con.prepareStatement("INSERT INTO JAVA_ASI.recipe(title ,description , expertise, nbpeople, duration, type) VALUES(?,?,?,?,?,?)");
        query.setString(1, newItem.getTitle());
        query.setString(2, newItem.getDescription());
        query.setInt(3, newItem.getExpertise());
        query.setInt(4, newItem.getNbpeople());
        query.setInt(5, newItem.getDuration());
        query.setString(6, newItem.getType());
        return query;
    }

    @Override
    protected PreparedStatement getSQLDelete(Connection con, RecipeModelBean item) throws SQLException {
        PreparedStatement query = con.prepareStatement("DELETE FROM JAVA_ASI.recipe r WHERE r.id = ?");
        query.setInt(1, item.getId());
        return query;
    }

    @Override
    protected PreparedStatement getSQLUpdate(Connection con, RecipeModelBean item) throws SQLException {
        PreparedStatement query = con.prepareStatement("UPDATE JAVA_ASI.recipe SET title = ? ,description = ? , expertise = ?, nbpeople = ?, duration = ?, type = ? WHERE id = ?");
        query.setString(1, item.getTitle());
        query.setString(2, item.getDescription());
        query.setInt(3, item.getExpertise());
        query.setInt(4, item.getNbpeople());
        query.setInt(5, item.getDuration());
        query.setString(6, item.getType());

        query.setInt(7, item.getId());
        return query;
    }

    @Override
    protected PreparedStatement getSQLGetAll(Connection con) throws SQLException {
        PreparedStatement query = con.prepareStatement("SELECT * FROM JAVA_ASI.recipe");
        return query;
    }

    @Override
    protected PreparedStatement getSQLGetById(Connection con, int id) throws SQLException {
        PreparedStatement query = con.prepareStatement("SELECT * FROM JAVA_ASI.recipe WHERE id = ?");
        query.setInt(1,id);
        return query;
    }


    @Override
    protected RecipeModelBean toObject(ResultSet rs) throws SQLException {
        return new RecipeModelBean(
                rs.getString("title"),
                rs.getString("description"),
                Integer.parseInt(rs.getString("expertise")),
                Integer.parseInt(rs.getString("nbpeople")),
                Integer.parseInt(rs.getString("duration")),
                rs.getString("type"),
                rs.getString("image"),
                rs.getInt("id"));
    }
}