package net.viralpatel.spring.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

public class DBUtil {

	private static String schema = "str";
	static {
		try{
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e) {
			
		}
	}
	
	public static Connection getConnection() {
		String conUrl = "jdbc:postgresql://ec2-54-179-171-159.ap-southeast-1.compute.amazonaws.com:5432/hms?user=postgres&password=";
		Connection con = null;
		try{
			con = DriverManager.getConnection(conUrl);
			String schemaQuery = "set search_path to "+schema;
			PreparedStatement pstmt = con.prepareStatement(schemaQuery);
			Boolean res = pstmt.execute();
		}catch(SQLException ex) {
			System.out.println(ex);
		}
		return con;		
	}
	
	
	/**
	 * Sets the data source. The data source is injected from the
	 * spring-servlet.xml file.
	 *
	 * @param dataSource
	 *            the new data source
	 */
	public static void setDataSource(DataSource dataSource) {
		System.out.println(" dataSource :"+ dataSource);
	}
}
