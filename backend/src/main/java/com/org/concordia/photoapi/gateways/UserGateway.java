package com.org.concordia.photoapi.gateways;

import java.sql.ResultSet;

public interface UserGateway {
	public ResultSet getUserIdByUsername(String username);
	public ResultSet getPasswordByUsername(String username);
	public boolean addUser(String username, String password);
}
