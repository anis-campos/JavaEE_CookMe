package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.UserModel;

public class UserDAO extends DAO{
 
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
				UserModel u = new UserModel(
						rs.getString("lastname"),
						rs.getString("surname"), 
						Integer.parseInt(rs.getString("age")), 
						rs.getString("login"), rs.getString("pwd"));
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
 
 
}
