package ie.swayne.test.sql;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

//The good old Swayne special

public class SQLHelper {
	
	private static String url = "jdbc:sqlite:";
	private static String fileName = "test.db";
	private static SQLHelper instance = null;
	
	public static SQLHelper getInstance() {
		if(instance == null)
			instance = new SQLHelper();
		return instance;
	}
	
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
				+ "user VARCHAR(32) PRIMARY KEY,"
				+ "pass VARCHAR(256)"
				+ ");";
		String sql2 = "INSERT INTO USERS VALUES(Sam, password(ballymac1));";
		
		try(Connection conn = this.connect();
				Statement stmt = conn.createStatement()) {
			DatabaseMetaData meta = conn.getMetaData();
			System.out.println("Driver: " +  meta.getDriverName());
			stmt.execute(sql);
			System.out.println("db " + fileName + " created succesfully.");
			stmt.execute(sql2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public synchronized Query executeQuery(String sql) {
		Query query = null;
		System.out.println("Executing " + sql);
		try(
			Connection conn = this.connect();
		) {
			
			if(sql.toUpperCase().startsWith("SELECT")) {
				PreparedStatement stmt = conn.prepareStatement(sql);
				query = new Query(stmt.executeQuery());
			}
			
			else {
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.executeUpdate();
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return query;
	}
	
	private SQLHelper() {
		File f = new File(fileName);
		if(!(f.exists())) {
			try {
				f.createNewFile();
				init();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
