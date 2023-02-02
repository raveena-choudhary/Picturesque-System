package com.org.concordia.photoapi.mappers;

public interface UserMapper {
	public int getUserIdByUsername(String username);
	public String getPasswordByUsername(String username);
	public boolean addUser(String username, String password);
}
