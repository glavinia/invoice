package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
	public Connection createConnection() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/invoice1","root","");
		return con;
	}
	public void closeConnection(Connection con) throws SQLException{
		con.close();
	}
	public ResultSet selectStatement(Connection con,String sqlStatement) throws SQLException{
		Statement statement = con.createStatement();
		ResultSet rs = statement.executeQuery(sqlStatement);
		return rs;		
	}	
}
