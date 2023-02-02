package com.org.concordia.photoapi.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.org.concordia.photoapi.gateways.UserGateway;
import com.org.concordia.photoapi.gateways.UserGatewayImpl;

public class UserMapperImpl implements UserMapper {

	private UserGateway usersGateway = new UserGatewayImpl();

	@Override
	public int getUserIdByUsername(String username) {
		int userId = -1;

		try {

			ResultSet rs = usersGateway.getUserIdByUsername(username);

			if (rs != null) {
				while (rs.next()) {
					userId = rs.getInt("user_id");
				}
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return userId;
	}

	@Override
	public String getPasswordByUsername(String username) {
		String password = "";

		try {

			ResultSet rs = usersGateway.getPasswordByUsername(username);

			if (rs != null) {

				while (rs.next()) {
					password = rs.getString("password");
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return password;
	}

	@Override
	public boolean addUser(String username, String password) {
		return usersGateway.addUser(username, password);
	}
}
