package net.viralpatel.spring.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

	static {
		try{
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e) {
			
		}
	}
	
	public static Connection getConnection() {
		String conUrl = "jdbc:postgresql://localhost:5432/hms?user=postgres&password=";
		Connection con = null;
		try{
			con = DriverManager.getConnection(conUrl);
		}catch(SQLException ex) {
			System.out.println(ex);
		}
		return con;		
	}
}
