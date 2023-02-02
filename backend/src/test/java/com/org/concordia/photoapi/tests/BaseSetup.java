package com.org.concordia.photoapi.tests;


import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;

public class BaseSetup {
	
	@BeforeClass
	public static void setup()
	{
		RestAssured.port = 8080;
		RestAssured.basePath = "/photo-api/";
		RestAssured.baseURI = "http://localhost";
	}
}
