package com.org.concordia.photoapi.tests;

import java.sql.Connection;

import org.testng.Assert;
import org.testng.annotations.AfterClass;

import com.org.concordia.photoapi.util.DBConnect;

public class TearDown {
	
	private static Connection conn;

	@AfterClass
	public static void closeDBConnection()
	{
		Assert.assertFalse(DBConnect.closeConnection(conn));
	}

}
