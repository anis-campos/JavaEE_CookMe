package step1.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import step1.model.UserModel;

public class UserDAO extends DAO<UserModel> {

    
    @Override
    public UserModel find(int id) {
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
    public UserModel create(UserModel obj) {
        PreparedStatement query;
        try {
            query = this.connection.prepareStatement("INSERT INTO (firstname ,lastname , age , login , password , email ) users VALUES(?,?,?,?,?)");
            query.setString(1,obj.getFirstname());
            query.setString(2,obj.getLastname());
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
    public UserModel update(UserModel obj) {
        PreparedStatement query;
        try {
            query = this.connection.prepareStatement("UPDATE users set firstname = ?,lastname = ?, age = ?, login = ?, password = ?, email = ? where u.id = ? ");
            query.setString(1,obj.getFirstname());
            query.setString(2,obj.getLastname());
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
    public void delete(UserModel obj) {
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
    public List<UserModel> getAll() {
        Statement query = null;
        List<UserModel> list = new ArrayList<UserModel>();
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
    protected UserModel toObject(ResultSet rs) throws SQLException {
        return new UserModel(
                rs.getInt("id"),
                rs.getString("firstname"),
                rs.getString("lastname"),
                Integer.parseInt(rs.getString("age")),
                rs.getString("login"), rs.getString("password"));
    }
}
