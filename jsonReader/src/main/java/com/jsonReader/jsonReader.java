package com.jsonReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONObject;
import org.json.JSONTokener;

public class jsonReader {
	
	private final String resourceName = ".\\resources\\data.json";
	
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

	public void jsonParser()
	{
		FileReader fr = null;
		
		try
		{
			fr = new FileReader(new File(resourceName));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Cannot find resource file " + resourceName);
		}

	    JSONTokener tokener = new JSONTokener(fr);
        JSONObject object = new JSONObject(tokener);
        
        if(object.get("firstName").equals("John"))
        	{
        		System.out.println(object.get("firstName"));
        	}
        
	}
	
	public static void main(String[] args) throws SQLException {
		
		//establish connection with DB
		dbConnect connect = new dbConnect();
		Connection conn = null;
		jsonReader jsonReader = new jsonReader();
		
		try
		{
			
			conn = connect.connectToDB();
			
			//execute select query
			jsonReader jsonreader = new jsonReader();
			jsonreader.selectAll(conn);
			
			//read json
			jsonReader.jsonParser();
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		
				}
	}

}
