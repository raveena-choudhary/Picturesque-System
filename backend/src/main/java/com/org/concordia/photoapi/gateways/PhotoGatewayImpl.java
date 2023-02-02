package com.org.concordia.photoapi.gateways;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.org.concordia.photoapi.util.DBConnect;

public class PhotoGatewayImpl implements PhotoGateway {

	private static Connection conn = DBConnect.getDBConnection();
	
	@Override
	public ResultSet getPhotos() {

		String photosSQL = "SELECT * FROM Photos p INNER JOIN Photographer ph ON p.photographer_id = ph.photographer_id";

		return getPhotosByQuery(photosSQL);
	}

	@Override
	public ResultSet getPhotoById(int photoId) {

		String photosSQL = "SELECT * FROM Photos p.photo_id='"
				+ photoId + "'";
		
		return getPhotosByQuery(photosSQL);
	}

	@Override
	public ResultSet getUserFavouritePhotos(int userId) {
		String favSQL = "SELECT * from Photos p, Favourites f where p.photo_id=f.photo_id and f.user_id='" + userId
				+ "'";

		return getListPhotosByLikesAndFavQuery(favSQL);
	}

	@Override
	public ResultSet getUserLikedPhotos(int userId) {
		String likesSQL = "SELECT * from Photos p, Likes l where p.photo_id=l.photo_id and l.user_id='" + userId + "'";
		return getListPhotosByLikesAndFavQuery(likesSQL);
	}

	@Override
	public void addUserLikedPhotos(int userId, int photoId) {
		try {
			String addUserLikedSql = "INSERT INTO Likes(user_id,photo_id) values(?,?)";

			PreparedStatement pstmt = conn.prepareStatement(addUserLikedSql);
			pstmt.setInt(1, userId);
			pstmt.setInt(2, photoId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void addUserFavPhotos(int userId, int photoId) {
		try {
			String addUserFavSql = "INSERT INTO Favourites(user_id,photo_id) values(?,?)";

			PreparedStatement pstmt = conn.prepareStatement(addUserFavSql);
			pstmt.setInt(1, userId);
			pstmt.setInt(2, photoId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void removeUserLikedPhotos(int userId, int photoId) {
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String removeUserLikedPhotosSql = "DELETE FROM Likes where user_id='" + userId + "' and  photo_id='"
					+ photoId + "'";

			stmt.executeUpdate(removeUserLikedPhotosSql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void removeUserFavPhotos(int userId, int photoId) {
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String removeUserFavSql = "DELETE FROM Favourites where user_id='" + userId + "' and  photo_id='" + photoId
					+ "'";
			stmt.executeUpdate(removeUserFavSql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public ResultSet getNoOfLikesOfPhoto(int photoId) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			String noOfLikeOfPhotoSQL = "SELECT photo_id FROM likes where photo_id ='" + photoId + "'";
			rs = stmt.executeQuery(noOfLikeOfPhotoSQL);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return rs;
	}

	private static ResultSet getPhotosByQuery(String sqlQuery) {
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sqlQuery);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return rs;

	}

	private static ResultSet getListPhotosByLikesAndFavQuery(String sqlQuery) {
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sqlQuery);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return rs;
	}
}
