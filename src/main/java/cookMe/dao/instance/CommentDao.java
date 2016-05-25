package cookMe.dao.instance;


import cookMe.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by djbranbran on 24/05/16.
 */
public class CommentDao {
    private Connection connection;
    private String dB_HOST;
    private String dB_PORT;
    private String dB_NAME;
    private String dB_USER;
    private String dB_PWD;

    public CommentDao(String DB_HOST, String DB_PORT, String DB_NAME, String DB_USER, String DB_PWD) {
        dB_HOST = DB_HOST;
        dB_PORT = DB_PORT;
        dB_NAME = DB_NAME;
        dB_USER = DB_USER;
        dB_PWD = DB_PWD;
    }

    public void addComment(CommentModelBean comment) {
        try {
            // create connection
            connection = DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
            PreparedStatement query = connection.prepareStatement("INSERT INTO comment(userID , recipeID, comment) VALUES(?,?,?)");
            query.setInt(1,comment.getUserModelBean().getId());
            query.setInt(2,comment.getRecipeModelBean().getId());
            query.setString(3, comment.getComment());
            query.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<CommentModelBean> getAllComments(){
        List<CommentModelBean> list = new ArrayList<CommentModelBean>();
        Statement query;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
            query = this.connection.createStatement();
            query.execute("SELECT * from comment;");
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

    protected CommentModelBean toObject(ResultSet rs) throws SQLException {
        return new CommentModelBean(
                new UserModelBean(rs.getInt("idUser"),
                rs.getString("firstname"),
                rs.getString("lastname"),
                Integer.parseInt(rs.getString("age")),
                rs.getString("login"),
                rs.getString("password"),
                rs.getString("email"),
                        Enum.valueOf(UserModelBean.UserType.class, rs.getString("type"))),
                new RecipeModelBean(rs.getString("title"),
                        rs.getString("description"),
                        Integer.parseInt(rs.getString("expertise")),
                        Integer.parseInt(rs.getString("nbpeople")),
                        Integer.parseInt(rs.getString("duration")),
                        rs.getString("type"),
                        rs.getString("image"),
                        rs.getInt("idRecipe")),
                rs.getString("comment")
        );
    }

	public List<CommentModelBean> find(CommentModelBean recipe) {
		Statement query;
		String sql = ((SearchCommentBean)recipe).getSQLSearchQuery();
		List<CommentModelBean> listRecipe = new ArrayList<CommentModelBean>();
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