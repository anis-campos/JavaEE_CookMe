package db;

import java.sql.Connection;

public abstract class DAO {
	protected Connection connection;
	public DAO(){
		connection = Connect.getConnection();
	}
}
