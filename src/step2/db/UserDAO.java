package step2.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import step1.model.UserModel;
import step2.model.UserModelBean;

public class UserDAO extends DAO<UserModelBean> {

    
    @Override
    public UserModelBean find(int id) {
        PreparedStatement query = null;
        try {
            query = this.connection.prepareStatement("select * from users u where u.id = ? ");
            query.setInt(1, id);
            ResultSet resultSet = query.executeQuery();
            if (resultSet.first()) {
                return toObject(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    @Override
    public UserModelBean create(UserModelBean obj) {
        PreparedStatement query;
        try {
            query = this.connection.prepareStatement("INSERT INTO (firstname ,lastname , age , login , password , email ) users VALUES(?,?,?,?,?)");
            query.setString(1,obj.getFirstname());
            query.setString(2,obj.getLastName());
            query.setInt(3,obj.getAge());
            query.setString(4,obj.getLogin());
            query.setString(5,obj.getEmail());
            query.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return find(obj.getId());
    }

    @Override
    public UserModelBean update(UserModelBean obj) {
        PreparedStatement query;
        try {
            query = this.connection.prepareStatement("UPDATE users set firstname = ?,lastname = ?, age = ?, login = ?, password = ?, email = ? where u.id = ? ");
            query.setString(1,obj.getFirstname());
            query.setString(2,obj.getLastName());
            query.setInt(3,obj.getAge());
            query.setString(4,obj.getLogin());
            query.setString(5,obj.getEmail());
            query.setInt(6,obj.getId());
            query.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return find(obj.getId());
    }

    @Override
    public void delete(UserModelBean obj) {
        PreparedStatement query;
        try {
            query = this.connection.prepareStatement("DELETE from users u where u.id = ? ");
            query.setInt(1, obj.getId());
            query.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<UserModelBean> getAll() {
        Statement query = null;
        List<UserModelBean> list = new ArrayList<UserModelBean>();
        try {
            query = this.connection.createStatement();
            query.execute("SELECT * from users;");
            ResultSet rs = query.getResultSet();
            while (rs.next()) {
                list.add(toObject(rs));
            }
            
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;


    }

    @Override
    protected UserModelBean toObject(ResultSet rs) throws SQLException {
        return new UserModelBean(
                rs.getInt("id"),
                rs.getString("firstname"),
                rs.getString("lastname"),
                Integer.parseInt(rs.getString("age")),
                rs.getString("login"), rs.getString("password"));
    }
}
