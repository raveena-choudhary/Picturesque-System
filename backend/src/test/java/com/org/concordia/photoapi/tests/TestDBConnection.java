package com.org.concordia.photoapi.tests;

import java.sql.Connection;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.concordia.photoapi.util.DBConnect;

public class TestDBConnection {
	
	Connection conn;
	
	@Test
    public void setupConnection() {
		conn = DBConnect.getDBConnection();
    }

	@Test
    public void closeConnection() {
        Assert.assertFalse(DBConnect.closeConnection(conn));
	}
}
