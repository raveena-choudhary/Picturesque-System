package com.jsonReader;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class jsonReader {
	
	public void selectAll(Connection conn) throws SQLException
	{
		String sql = "SELECT * FROM Person";  
		Statement stmt  = conn.createStatement();  
        ResultSet rs    = stmt.executeQuery(sql); 
        while (rs.next()) {  
            System.out.println(rs.getInt("SIN") +  "\t" +   
                               rs.getString("Name"));  
        }
	}

	public static void main(String[] args) throws SQLException {
		
		//establish connection with DB
		dbConnect connect = new dbConnect();
		Connection conn = connect.connectToDB();
		
		//execute select query
		jsonReader jsonreader = new jsonReader();
		jsonreader.selectAll(conn);
	}

}
