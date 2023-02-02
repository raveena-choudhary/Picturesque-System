package com.org.concordia.photoapi.gateways;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.org.concordia.photoapi.util.DBConnect;

public class PhotographerGatewayImpl implements PhotographerGateway {

	private static Connection conn = null;

	@Override
	public ResultSet getPhotosByPhotographerId(int photographerId) {

		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBConnect.getDBConnection();
			String photosByPhotographerNameSQL = "SELECT * FROM Photos p where p.photographer_id=" + photographerId;

			stmt = conn.createStatement();
			rs = stmt.executeQuery(photosByPhotographerNameSQL);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return rs;
	}

	@Override
	public ResultSet getPhotographers() {

		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBConnect.getDBConnection();
			String sql = "SELECT * FROM Photographer";

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return rs;
	}
}
