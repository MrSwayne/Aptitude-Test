package ie.swayne.IQ.SQL;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SQLHelper {

	private static String url = "jdbc:sqlite:";
	private static String fileName = "IQ.db";
	
	
	public SQLHelper() {
		createDatabase();
	}
	
	private Connection connect() {
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(url + fileName);
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
	
	private void createDatabase() {

		System.out.println("Initialising database..");
		
		if(new File(fileName).exists()) {
			System.out.println("Database exists");
			init(fileName);
		}
		
		else {
			try (Connection conn = this.connect()) {
					DatabaseMetaData meta = conn.getMetaData();
					System.out.println("The driver name is " + meta.getDriverName());
					System.out.println("The database " + fileName + " has been created.");
				
					init(fileName);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	private void init(String fileName) {
		
		System.out.println("Initialising database tables..");
		
		String sql = "CREATE TABLE IF NOT EXISTS questions ("
				//	+ "id INTEGER PRIMARY KEY autoincrement,"
					+ "category VARCHAR(32) DEFAULT NULL,"
					+ "question VARCHAR(128) DEFAULT NULL,"
					+ "answers VARCHAR(512) DEFAULT NULL,"
					+ "correctAnswers CHAR(1) DEFAULT NULL,"
					+ "url VARCHAR(128) DEFAULT NULL,"
					+ "mark INTEGER DEFAULT 0,"
					+ "UNIQUE(question, answers, correctAnswers, url));";

		
		String sql3 = "CREATE TABLE IF NOT EXISTS users ("
					+ "name VARCHAR(16) PRIMARY KEY,"
					+ "password VARCHAR(32));";
		


		
		try (Connection conn = this.connect();
			Statement stmt = conn.createStatement()) {
			
			stmt.execute(sql);
			stmt.execute(sql3);
			
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void insertIntoQuestions(String category, String question, String answers, String correctAnswers, String url, int mark) {
		System.out.println("Inserting " + question + " into table");
		
		String sql = "INSERT INTO questions(category, question, answers, correctAnswers, url, mark) VALUES(?,?,?,?,?,?)";
		
		try(Connection conn = this.connect();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, category);
			pstmt.setString(2, question);
			pstmt.setString(3, answers);
			pstmt.setString(4, correctAnswers);
			pstmt.setString(5, (url == null)?	"null": url);
			pstmt.setInt(6, mark);
			pstmt.executeUpdate();
			
			System.out.println("Succesfully ");
			
		} catch (SQLException e) {
			System.out.println("Error, duplicate question");
		}
		
	}
	
	public List<Map<String,String>> readQuestions() {
		
		System.out.println("Attemping to access database..");
		ArrayList<String> rows = new ArrayList<String>();
		
		try (Connection conn = this.connect();
			Statement stmt = conn.createStatement()) {
			
			
			String sql = "SELECT * FROM questions";
			ResultSet rs = stmt.executeQuery(sql);
			
			String r = "";
			
			
			List<Map<String,String>> resultList = new ArrayList<Map<String,String>>();
			Map<String, String> row = null;
			
		    ResultSetMetaData metaData = rs.getMetaData();
		    int columnCount = metaData.getColumnCount();
			
			while(rs.next()) {
				
				row = new HashMap<String,String>();
				for(int i = 0;i < columnCount;i++) {
					row.put(metaData.getColumnName(i+1), rs.getString(metaData.getColumnName(i+1)));
				}
				resultList.add(row);
			}
			
			return resultList;

		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("No questions found");
		return null;
	}

}
