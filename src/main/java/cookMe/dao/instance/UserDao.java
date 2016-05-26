package cookMe.dao.instance;


import cookMe.model.UserModelBean;

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
            PreparedStatement query = connection.prepareStatement("INSERT INTO users(firstname ,lastname , age , login , password , email, type ) VALUES(?,?,?,?,?,?,?)");
            query.setString(1, user.getFirstname());
            query.setString(2, user.getLastname());
            query.setInt(3, user.getAge());
            query.setString(4, user.getLogin());
            query.setString(5, user.getPwd());
            query.setString(6, user.getEmail());
            query.setString(6, user.getType().name());

            query.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public UserModelBean findByLogin(String login) {
        //return value

        UserModelBean user = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
            //TODO A l’image de DB.java créer une réquète permettant de récupérer
            //l’ensemble des utilisateurs contenu dans la base et de les placer dans une liste
            PreparedStatement query = this.connection.prepareStatement("SELECT * from users u WHERE u.login = ?;");
            query.setString(1, login);

            ResultSet rs = query.executeQuery();
            ;

            if (rs.first())
                user = toObject(rs);

            connection.close();

            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;

    }

    public List<UserModelBean> getAllUser() {
        //return value
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
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return list;
        }
    }

    protected UserModelBean toObject(ResultSet rs) throws SQLException {
        return new UserModelBean(
                rs.getInt("id"),
                rs.getString("firstname"),
                rs.getString("lastname"),
                Integer.parseInt(rs.getString("age")),
                rs.getString("login"),
                rs.getString("password"),
                rs.getString("email"),
                Enum.valueOf(UserModelBean.UserType.class, rs.getString("type")));
    }

    public UserModelBean checkUser(String login, String pwd) {
        try {
            // create connection
            connection = DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
            //TODO A l’image de DB.java créer une réquète permettant d’ajout l’utilisateur à la base de données, ATTENTION, utiliser cette fois–ci les PrepareStatement
            PreparedStatement query = connection.prepareStatement("SELECT * FROM users WHERE login = ? AND password = ?");
            query.setString(1, login);
            query.setString(2, pwd);
            query.execute();
            ResultSet rs = query.getResultSet();
            if (rs.first()) {
                return toObject(rs);
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
