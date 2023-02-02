package com.org.concordia.photoapi.tests;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.testng.annotations.BeforeSuite;

import com.org.concordia.photoapi.util.DBConnect;

public class TestDataInsertionInDB {
	
	private Connection conn;
	
	@BeforeSuite
	public void insertTestDataInDB()
	{
		conn = DBConnect.getDBConnection();
		
		String photosSql;
		//Photos data
		photosSql = "INSERT INTO Photos(photo_id,photographer_id,avg_color,title,imageMediumSize,imageLargeSize,imageOrignalSize) VALUES(?,?,?,?,?,?,?)";

		try (PreparedStatement pstmt = conn.prepareStatement(photosSql)) {
			pstmt.setInt(1, 1234567);
			pstmt.setInt(2, 1234);
			pstmt.setString(3, "#515149");
			pstmt.setString(4, "Its Test Data");
			pstmt.setString(5, "photo-1234567-md.jpeg");
			pstmt.setString(6, "photo-1234567-lg.jpeg");
			pstmt.setString(7, "photo-1234567-o.jpeg");
			pstmt.executeUpdate();
			
			System.out.println("Test insertion done for Photos");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	
		//Photographer data
		photosSql = "INSERT INTO Photographer(photographer_id,p_name,p_url) VALUES(?,?,?)";
	
		try (PreparedStatement pstmt = conn.prepareStatement(photosSql)) {
			 pstmt.setInt(1, 1234);
			 pstmt.setString(2, "test");
			 pstmt.setString(3, "@test");
			pstmt.executeUpdate();
			
			System.out.println("Test insertion done for Photographer");
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		//Favourites Data
		photosSql = "INSERT INTO Favourites(photo_id) VALUES(?)";

		try (PreparedStatement pstmt = conn.prepareStatement(photosSql)) {
//			 pstmt.setInt(1, 11);
			 pstmt.setInt(1, 1234567);
			pstmt.executeUpdate();
			
			System.out.println("Test insertion done for Favourites");
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		//Likes Data
		photosSql = "INSERT INTO Likes(photo_id) VALUES(?)";

		try (PreparedStatement pstmt = conn.prepareStatement(photosSql)) {
//			 pstmt.setInt(1, 11);
			 pstmt.setInt(1, 1234567);
			pstmt.executeUpdate();
			
			System.out.println("Test insertion done for Likes");
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		//Users data
		try {
			String addUserSql = "INSERT INTO Users(username,password) values(?,?)" ;

			PreparedStatement pstmt = conn.prepareStatement(addUserSql);
			pstmt.setString(1, "testuser");
            pstmt.setString(2, "test");
            pstmt.executeUpdate();
            
            System.out.println("Test insertion done for Users");
            
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
