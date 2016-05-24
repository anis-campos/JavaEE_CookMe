package step1.db;

import java.sql.Connection;
import java.sql.SQLException;

public class Connect {
	private final static String DB_HOST = "dasilvacamposanis.fr";
	private final static String DB_PORT = "8080";
	private final static String DB_NAME = "JAVA_ASI";
	private final static String DB_USER = "java";
	private final static String DB_PWD = "TpJavaAsi2016";
	
	public static Connection getConnection(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			return java.sql.DriverManager.getConnection("jdbc:mysql://"+DB_HOST+":"+DB_PORT+"/"+DB_NAME, DB_USER,DB_PWD);
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
}
