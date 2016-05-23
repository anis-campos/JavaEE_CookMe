package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.UserModel;

import org.apache.catalina.mbeans.UserMBean;

public class DB {
	private final String DB_HOST = "db-tp-cpe.fr";
	private final String DB_PORT = "3306";
	private final String DB_NAME = "binome22";
	private final String DB_USER = "binome22";
	private final String DB_PWD = "binome22";
	private Connection connection;
 
	public DB(){
		try{
			Class.forName("com.mysql.jdbc.Friver");
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"+DB_HOST+":"+DB_PORT+"/"+DB_NAME, DB_USER,DB_PWD);
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
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
 
 
}