package com.org.concordia.photoapi.tests;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;
import org.testng.Assert;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.concordia.photoapi.model.User;

public class TestUserDetailsAfterLogin extends BaseSetup
{
	public static final String username = "testuser";
	
	@Test
	public void userDetailsAfterLogin() throws JsonParseException, JsonMappingException, IOException
	{
	
		String response = given().
		when().
		queryParam("username", username).
		get("/get-user").
		then().
		statusCode(200).
		extract().
		response().
		asPrettyString();
		
		
		ObjectMapper mapper = new ObjectMapper();
		User user = mapper.readValue(response, User.class);
		
		//assert values in json response
		Assert.assertEquals(user.getUsername() , username);
		Assert.assertEquals(user.getFavourite().get(0) , 1234567);
		Assert.assertEquals(user.getLike().get(0) , 1234567);
		
	}
}
