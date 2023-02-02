package com.org.concordia.photoapi.tests;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.testng.annotations.AfterSuite;

import com.org.concordia.photoapi.util.DBConnect;

public class TestDataDeletionFromDB {
	
	private Connection conn;
	
	@AfterSuite
	public void deleteTestDataFromDB()
	{	
		conn=DBConnect.getDBConnection();
		String photosSql;
		
		//photos
		photosSql = "DELETE FROM Photos where photo_id=?";
		
		try (PreparedStatement pstmt = conn.prepareStatement(photosSql)) {
			pstmt.setInt(1, 1234567);
			pstmt.executeUpdate();
			
			System.out.println("Test deletion done for Photos");
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		//photographer
		 photosSql = "DELETE FROM Photographer where photographer_id=?";

			try (PreparedStatement pstmt = conn.prepareStatement(photosSql)) {
				 pstmt.setInt(1, 1234);
				pstmt.executeUpdate();
				
				System.out.println("Test deletion done for Photographer");
				
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		
		//Favourites
		
		photosSql = "DELETE FROM Favourites where photo_id=?";

		try (PreparedStatement pstmt = conn.prepareStatement(photosSql)) {
//			 pstmt.setInt(1, 11);
			 pstmt.setInt(1, 1234567);
			pstmt.executeUpdate();
			
			System.out.println("Test deletion done for Favourites");
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
		//Likes
		photosSql = "DELETE FROM Likes where photo_id=?";

		try (PreparedStatement pstmt = conn.prepareStatement(photosSql)) {
			 pstmt.setInt(1, 1234567);
			pstmt.executeUpdate();
			
			System.out.println("Test deletion done for Likes");
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		//Users
		try {
			String addUserSql = "DELETE FROM Users where username=? and password=?" ;

			PreparedStatement pstmt = conn.prepareStatement(addUserSql);
			pstmt.setString(1, "testuser");
            pstmt.setString(2, "test");
            pstmt.executeUpdate();
            
            System.out.println("Test deletion done for Users");
            
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
}
