package com.org.concordia.photoapi.gateways;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.org.concordia.photoapi.util.DBConnect;

public class UserGatewayImpl implements UserGateway {

	private static Connection conn = DBConnect.getDBConnection();

	@Override
	public ResultSet getUserIdByUsername(String username) {
		Statement stmt=null;
		ResultSet rs=null;

		try {
			stmt = conn.createStatement();
			String userIdSql = "SELECT user_id FROM Users where username='" + username + "'";

			rs = stmt.executeQuery(userIdSql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return rs;
	}

	@Override
	public ResultSet getPasswordByUsername(String username) {
		Statement stmt=null;
		ResultSet rs=null;
		
		try {
			stmt = conn.createStatement();
			String passwordSql = "SELECT password FROM Users where username='" + username + "'";

			rs = stmt.executeQuery(passwordSql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return rs;
	}

	@Override
	public boolean addUser(String username, String password) {
		try {
			String addUserSql = "INSERT INTO Users(username,password) values(?,?)" ;

			PreparedStatement pstmt = conn.prepareStatement(addUserSql);
			pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
            
            return true;
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
}
