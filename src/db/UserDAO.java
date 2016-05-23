package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.UserModel;

public class UserDAO extends DAO<UserModel>{
 
	public UserDAO(){
		super();
	}

	public List<UserModel> getData(){
		List<UserModel> userList = new ArrayList<UserModel>();
		Statement query;
			
		try{
			query = connection.createStatement();
			ResultSet rs =  query.executeQuery("select * from user");
			while(rs.next()){
				UserModel u = toObject(rs);
				userList.add(u);
			}
			return userList;
			
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	public void addUser(UserModel user){
		Statement query;
		try{
			query = connection.createStatement();
			
			connection.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		UserDAO db = new UserDAO();
	}


	@Override
	public UserModel find(int id) {
		PreparedStatement query = null;
		try {
			query = this.connection.prepareStatement("select * from users u where u.id = ? ");
			query.setInt(1,id);
            ResultSet resultSet = query.executeQuery();
            if(resultSet.first()){
                return toObject(resultSet);
            }

        } catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

	@Override
	public UserModel create(UserModel obj) {
		return null;
	}

	@Override
	public UserModel update(UserModel obj) {
		return null;
	}

	@Override
	public void delete(UserModel obj) {

	}

	@Override
	public List<UserModel> getAll() {
		Statement query = null;
		List<UserModel> list = new ArrayList<UserModel>();
		try {
			query = this.connection.createStatement();
			query.execute("SELECT * from users;");
			ResultSet rs = query.getResultSet();
			while(rs.next()){
				list.add(toObject(rs));
			}
			return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
    @Override
    protected UserModel toObject(ResultSet rs) throws SQLException {
        return new UserModel(
                rs.getInt("id"),
                rs.getString("lastname"),
                rs.getString("firstname"),
                Integer.parseInt(rs.getString("age")),
                rs.getString("login"), rs.getString("password"));
    }
}
