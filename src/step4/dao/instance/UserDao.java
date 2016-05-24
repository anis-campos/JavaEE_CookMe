package step4.dao.instance;


import step4.model.UserModelBean;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private Connection connection;
    private String dB_HOST;
    private String dB_PORT;
    private String dB_NAME;
    private String dB_USER;
    private String dB_PWD;

    public UserDao(String DB_HOST, String DB_PORT, String DB_NAME, String DB_USER, String
            DB_PWD) {
        dB_HOST = DB_HOST;
        dB_PORT = DB_PORT;
        dB_NAME = DB_NAME;
        dB_USER = DB_USER;
        dB_PWD = DB_PWD;
    }

    public void addUser(UserModelBean user) {
        try {
            // create connection
            connection = DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
            //TODO A l’image de DB.java créer une réquète permettant d’ajout l’utilisateur à la base de données, ATTENTION, utiliser cette fois–ci les PrepareStatement
            PreparedStatement query = connection.prepareStatement("INSERT INTO (firstname ,lastname , age , login , password , email ) users VALUES(?,?,?,?,?)");
            query.setString(1,user.getFirstname());
            query.setString(2,user.getLastname());
            query.setInt(3,user.getAge());
            query.setString(4,user.getLogin());
            query.setString(5,user.getEmail());
            query.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<UserModelBean> getAllUser() {
        //return value
        ArrayList<UserModelBean> userList = new ArrayList<UserModelBean>();
        List<UserModelBean> list = new ArrayList<UserModelBean>();
        Statement query;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
            //TODO A l’image de DB.java créer une réquète permettant de récupérer
            //l’ensemble des utilisateurs contenu dans la base et de les placer dans une liste
            query = this.connection.createStatement();
            query.execute("SELECT * from users;");
            ResultSet rs = query.getResultSet();
            while (rs.next()) {
                list.add(toObject(rs));
            }

            connection.close();
            return userList;
        } catch (SQLException e) {
            e.printStackTrace();
            return userList;
        }
    }

    protected UserModelBean toObject(ResultSet rs) throws SQLException {
        return new UserModelBean(
                rs.getString("firstname"),
                rs.getString("lastname"),
                Integer.parseInt(rs.getString("age")),
                rs.getString("login"),
                rs.getString("password"),
                rs.getString("email"));
    }

    public UserModelBean checkUser(String login, String pwd) {
    	try {
            // create connection
            connection = DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
            //TODO A l’image de DB.java créer une réquète permettant d’ajout l’utilisateur à la base de données, ATTENTION, utiliser cette fois–ci les PrepareStatement
            PreparedStatement query = connection.prepareStatement("SELECT * FROM users WHERE login = ? AND password = ?");
            query.setString(1,login);
            query.setString(2,pwd);
            query.execute();
            ResultSet rs = query.getResultSet();
            if(rs.first()) {
            	return toObject(rs);
            }
            
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    	return null;
    }
}
