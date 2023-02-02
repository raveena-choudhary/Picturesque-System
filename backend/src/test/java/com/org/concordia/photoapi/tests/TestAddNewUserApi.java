package com.org.concordia.photoapi.tests;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.concordia.photoapi.model.ResponseForUserCreation;
import com.org.concordia.photoapi.util.DBConnect;

public class TestAddNewUserApi extends BaseSetup
{
	public static final String user = "test123";
	public static final String pass = "test";
	
	@Test(priority=1)
	public void addNewUserSuccess() throws JsonParseException, JsonMappingException, IOException
	{
	
		String getMessageAsResponse = given().
		when().
		queryParam("username", user).
		queryParams("password",pass).
		post("/add-user").
		then().
		statusCode(200).
		extract().
		response().
		asPrettyString();
		
		ObjectMapper mapper = new ObjectMapper();
		ResponseForUserCreation response = mapper.readValue(getMessageAsResponse, ResponseForUserCreation.class);
		
		//assert values in json response
		Assert.assertEquals(response.getType(),"success");
		Assert.assertEquals(response.getMessage(),"User successfully created");	
	}
	
	@Test(priority=2)
	public void addNewUserFailure() throws JsonParseException, JsonMappingException, IOException
	{
		String getMessageAsResponse = given().
		when().
		queryParam("username", user).
		queryParams("password",pass).
		post("/add-user").
		then().
		statusCode(500).
		extract().
		response().
		asPrettyString();
		
		ObjectMapper mapper = new ObjectMapper();
		ResponseForUserCreation response = mapper.readValue(getMessageAsResponse, ResponseForUserCreation.class);
		
		//assert values in json response
		Assert.assertEquals(response.getType(),"error");
		Assert.assertEquals(response.getMessage(),"User " + user + " already exists in the system");	
	}
	
	@Test(priority=0)
	public static void deleteNewUserCreated()
	{
		Connection conn = DBConnect.getDBConnection();
		
		try {
			String addUserSql = "DELETE FROM Users where username=? and password=?" ;

			PreparedStatement pstmt = conn.prepareStatement(addUserSql);
			pstmt.setString(1, user);
            pstmt.setString(2, pass);
            pstmt.executeUpdate();
            
            System.out.println("Test deletion done for User " + user);
            
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
