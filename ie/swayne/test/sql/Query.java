package ie.swayne.test.sql;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class Query extends ArrayList<Row> {
	
	
	public Query(ResultSet resultSet) {
		ResultSetMetaData metaData;
		try {
			metaData = resultSet.getMetaData();
			int columnCount = metaData.getColumnCount();
			
			while(resultSet.next()) {
				Row row = new Row();
				for(int i = 0;i < columnCount;i++) 
					row.add(metaData.getColumnName(i + 1), resultSet.getString(metaData.getColumnName(i+1)));
				this.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public String toString() {
		String line = "";
		for(int i = 0;i < this.size(); i++) {
			line+= this.get(i).toString() + "\n";
		}
		return line;
	}
	
	public int getColumnSize() {
		return this.get(0).getColsSize();
	}
	
	public String getColumnName(int i) {
		return this.get(0).getColName(i);
	}
}
