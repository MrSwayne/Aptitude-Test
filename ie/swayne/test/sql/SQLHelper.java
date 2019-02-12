package ie.swayne.test.sql;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//The good old Swayne special

public class SQLHelper {
	
	private static String url = "jdbc:sqlite:";
	private static String fileName = "res/test.db";
	
	
	public Connection connect() {
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(url + fileName);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	//Called if database is deleted
	public void init() {
		System.out.println("Initialising Database " + fileName);
		
		String sql = "CREATE TABLE IF NOT EXISTS users("
				+ "name VARCHAR(32) PRIMARY KEY,"
				+ "password VARCHAR(256)"
				+ ");";
		String sql2 = "INSERT INTO USERS VALUES(Sam, password(ballymac1));";
		
		try(Connection conn = this.connect();
				Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
			stmt.execute(sql2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public SQLHelper() {
		if(!(new File(fileName).exists())) {
			init();
		}
	}
}
