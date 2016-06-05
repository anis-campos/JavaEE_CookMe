package cookMe.dao.instance;


import cookMe.dao.fabric.DaoFabric;
import cookMe.model.EnumParser;
import cookMe.model.user.UserModelBean;
import cookMe.model.user.UserType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao extends AbstractDao<UserModelBean> {

    public UserDao(String connectionString) {
        super(connectionString);
    }

    public static void main(String[] argv) {
        UserDao userDao = DaoFabric.getInstance().createUserDao();
        List<UserModelBean> all = userDao.getAll();
        for (UserModelBean bean : all) {
            System.out.println(bean);
        }
    }

    @Override
    protected PreparedStatement getSQLInsert(Connection con, UserModelBean newItem) throws SQLException {
        PreparedStatement query = con.prepareStatement("INSERT INTO JAVA_ASI.users(firstname ,lastname , age , login , password , email, type ) VALUES(?,?,?,?,?,?,?)");
        query.setString(1, newItem.getFirstname());
        query.setString(2, newItem.getLastname());
        query.setInt(3, newItem.getAge());
        query.setString(4, newItem.getLogin());
        query.setString(5, newItem.getPassword());
        query.setString(6, newItem.getEmail());
        query.setString(7, newItem.getType().name());
        return query;
    }

    @Override
    protected PreparedStatement getSQLDelete(Connection con, UserModelBean item) throws SQLException {
        PreparedStatement query = con.prepareStatement("DELETE FROM JAVA_ASI.users WHERE id = ?");
        query.setInt(1, item.getId());
        return query;
    }

    @Override
    protected PreparedStatement getSQLUpdate(Connection con, UserModelBean item) throws SQLException {
        PreparedStatement query = con.prepareStatement("UPDATE JAVA_ASI.users SET firstname =  ?, lastname = ? , age = ? , login = ? , password = ? , email = ?, type = ? WHERE id = ?");
        query.setString(1, item.getFirstname());
        query.setString(2, item.getLastname());
        query.setInt(3, item.getAge());
        query.setString(4, item.getLogin());
        query.setString(5, item.getPassword());
        query.setString(6, item.getEmail());
        query.setString(7, item.getType().name());

        query.setInt(8, item.getId());

        return query;
    }

    @Override
    protected PreparedStatement getSQLGetAll(Connection con) throws SQLException {
        PreparedStatement query = con.prepareStatement("SELECT * FROM JAVA_ASI.users");
        return query;
    }

    @Override
    protected PreparedStatement getSQLGetById(Connection con, int id) throws SQLException {
        PreparedStatement query = con.prepareStatement("SELECT * FROM JAVA_ASI.users u WHERE u.id = ?");
        query.setInt(1, id);
        return query;
    }

    @Override
    protected UserModelBean toObject(ResultSet rs) throws SQLException {
        return new UserModelBean(
                rs.getInt("id"),
                rs.getString("firstname"),
                rs.getString("lastname"),
                Integer.parseInt(rs.getString("age")),
                rs.getString("login"),
                rs.getString("password"),
                rs.getString("email"),
                EnumParser.ParseWithDefault(UserType.class, rs.getString("type")));
    }

    public UserModelBean findByLogin(String login) {
        //return value

        UserModelBean user = null;
        try (Connection connection = getConnection()) {
            PreparedStatement query = connection.prepareStatement("SELECT * FROM JAVA_ASI.users u WHERE u.login = ?;");
            query.setString(1, login);

            ResultSet rs = query.executeQuery();

            if (rs.first())
                user = toObject(rs);

            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;

    }

    public UserModelBean checkUser(String login, String pwd) {
        try (Connection connection = getConnection()) {
            PreparedStatement query = connection.prepareStatement("SELECT * FROM JAVA_ASI.users WHERE login = ? AND password = ?");
            query.setString(1, login);
            query.setString(2, pwd);
            query.execute();
            ResultSet rs = query.getResultSet();
            if (rs.first()) {
                return toObject(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
