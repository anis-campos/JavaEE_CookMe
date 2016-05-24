package step5.dao.instance;


import step5.model.RecipeModel;
import step5.model.SearchRecipeBean;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by djbranbran on 24/05/16.
 */
public class RecipesDao {
    private Connection connection;
    private String dB_HOST;
    private String dB_PORT;
    private String dB_NAME;
    private String dB_USER;
    private String dB_PWD;

    public RecipesDao(String DB_HOST,String DB_PORT, String DB_NAME,String DB_USER,String DB_PWD) {
        dB_HOST = DB_HOST;
        dB_PORT = DB_PORT;
        dB_NAME = DB_NAME;
        dB_USER = DB_USER;
        dB_PWD = DB_PWD;
    }

    public void addRecipe(RecipeModel recipe) {
        try {
            // create connection
            connection = DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
            PreparedStatement query = connection.prepareStatement("INSERT INTO (title ,description , expertise, nbpeople, duration, type) users VALUES(?,?,?,?,?)");
            query.setString(1,recipe.getTitle());
            query.setString(2,recipe.getDescription());
            query.setInt(3,recipe.getExpertise());
            query.setInt(4,recipe.getNbpeople());
            query.setInt(5,recipe.getDuration());
            query.setString(6,recipe.getType());
            query.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<RecipeModel> getAllRecipes(){
        ArrayList<RecipeModel> list = new ArrayList<RecipeModel>();
        Statement query;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
            query = this.connection.createStatement();
            query.execute("SELECT * from recipe;");
            ResultSet rs = query.getResultSet();
            while (rs.next()) {
                list.add(toObject(rs));
            }
            connection.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return list;
        }
    }

    protected RecipeModel toObject(ResultSet rs) throws SQLException {
        return new RecipeModel(
                rs.getString("title"),
                rs.getString("description"),
                Integer.parseInt(rs.getString("expertise")),
                Integer.parseInt(rs.getString("nbpeople")),
                Integer.parseInt(rs.getString("duration")),
                rs.getString("type"));
    }

	public List<RecipeModel> find(RecipeModel recipe) {
		Statement query;
		String sql = ((SearchRecipeBean)recipe).getSQLSearchQuery();
		List<RecipeModel> listRecipe = new ArrayList<RecipeModel>();
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
            query = this.connection.createStatement();
            query.execute(sql);
            ResultSet rs = query.getResultSet();
            while (rs.next()) {
            	listRecipe.add(toObject(rs));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return listRecipe;	
	}
}