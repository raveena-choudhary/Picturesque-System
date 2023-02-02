package com.org.concordia.photoapi.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JSONReader {

	private final static String PHOTOS_JSON_PATH = ".\\resources\\photos.json";
	private final static String PHOTOGRAPHER_JSON_PATH = ".\\resources\\photographer.json";
	private static Connection conn = null;

	public void selectAll(Connection conn) throws SQLException {
		String sql = "SELECT * FROM Photos";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			System.out.println(rs.getInt("photo_id") + "\t" + rs.getInt("photographer_id") + "\t"
					+ rs.getString("avg_color") + "\t" + rs.getString("title") + "\t" + rs.getString("imageMediumSize")
					+ "\t" + rs.getString("imageLargeSize") + "\t" + rs.getString("imageOrignalSize") + "\t");
		}

		String sql1 = "SELECT * FROM Photographer";
		Statement stmt1 = conn.createStatement();
		ResultSet rs1 = stmt1.executeQuery(sql1);
		while (rs1.next()) {
			System.out.println(
					rs1.getInt("photographer_id") + "\t" + rs1.getString("p_name") + "\t" + rs1.getString("p_url"));
		}
	}

	public static void insertDataIntoPhotos(int photoId, int photographerId, String avgColor, String title,
			String imageMediumSize, String imageLargeSize, String imageOrignalSize) throws SQLException {
		String photosSql = "INSERT INTO Photos(photo_id,photographer_id,avg_color,title,imageMediumSize,imageLargeSize,imageOrignalSize) VALUES(?,?,?,?,?,?,?)";

		try (PreparedStatement pstmt = conn.prepareStatement(photosSql)) {
			pstmt.setInt(1, photoId);
			pstmt.setInt(2, photographerId);
			pstmt.setString(3, avgColor);
			pstmt.setString(4, title);
			pstmt.setString(5, imageMediumSize);
			pstmt.setString(6, imageLargeSize);
			pstmt.setString(7, imageOrignalSize);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void insertDataIntoPhotographer(int photographerId, String photographerName,String photographerProfile) throws SQLException {
		String photosSql = "INSERT INTO Photographer(photographer_id,p_name,p_url) VALUES(?,?,?)";

		try (PreparedStatement pstmt = conn.prepareStatement(photosSql)) {
			 pstmt.setInt(1, photographerId);
			 pstmt.setString(2, photographerName);
			 pstmt.setString(3, photographerProfile);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void parsePhotographerJson() throws JSONException, SQLException {
		FileReader fr = null;

		try {
			fr = new FileReader(new File(PHOTOGRAPHER_JSON_PATH));
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find resource file " + PHOTOGRAPHER_JSON_PATH);
		}

		JSONTokener tokener = new JSONTokener(fr);
		JSONObject jsonObject = new JSONObject(tokener);

		System.out.println(jsonObject);

		JSONArray jsonArray = (JSONArray) jsonObject.get("photographer");

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject photographerObject = jsonArray.getJSONObject(i);

			int photographerId = Integer.parseInt(photographerObject.get("photographerId").toString());
			String photographerName = photographerObject.get("photographerName").toString();
			String photographerProfile = photographerObject.get("photographerProfile").toString();

			insertDataIntoPhotographer(photographerId, photographerName, photographerProfile);
		}
	}
	
	public void parsePhotosJson() throws JSONException, SQLException {
		FileReader fr = null;

		try {
			fr = new FileReader(new File(PHOTOS_JSON_PATH));
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find resource file " + PHOTOS_JSON_PATH);
		}

		JSONTokener tokener = new JSONTokener(fr);
		JSONObject jsonObject = new JSONObject(tokener);

		System.out.println(jsonObject);

		JSONArray jsonArray = (JSONArray) jsonObject.get("photos");

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject photosObject = jsonArray.getJSONObject(i);

			int photoId = Integer.parseInt(photosObject.get("id").toString());
			int photographerId = Integer.parseInt(photosObject.get("photographer_id").toString());
			String avgColor = photosObject.get("avg_color").toString();
			String title = photosObject.get("title").toString();
			String imageMediumSize = photosObject.get("imageMediumSize").toString();
			String imageLargeSize = photosObject.get("imageLargeSize").toString();
			String imageOrignalSize = photosObject.get("imageOrignalSize").toString();

			insertDataIntoPhotos(photoId, photographerId, avgColor, title, imageMediumSize, imageLargeSize,
					imageOrignalSize);
		}
	}

	public static void main(String[] args) throws JSONException {
		// establish connection with DB
		JSONReader jsonReader = new JSONReader();

		try {
			conn = DBConnect.getDBConnection();
			JSONReader jsonreader = new JSONReader();

			// read json
			jsonReader.parsePhotosJson();
			jsonReader.parsePhotographerJson();

			// execute select query
			jsonreader.selectAll(conn);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
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
