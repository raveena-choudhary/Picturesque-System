package com.org.concordia.photoapi.tests;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.concordia.photoapi.model.ResponseForUserCreation;

public class TestAddAndRemoveFavouritePhotosByUserApi extends BaseSetup
{
	public static final String user = "testuser";
	public static final String photoId = "1234567";
	
	@Test(priority=0)
	public void addUserFavPhotos() throws JsonParseException, JsonMappingException, IOException
	{
	
	    given().
		when().
		queryParam("username", user).
		queryParams("photoId",photoId).
		post("/add-fav-photos").
		then().
		assertThat().
		statusCode(200);

	}
	
	@Test(priority=1)
	public void removeUserFavPhotos() throws JsonParseException, JsonMappingException, IOException
	{
		given().
		when().
		queryParam("username", user).
		queryParams("photoId",photoId).
		post("/remove-fav-photos").
		then().
		assertThat().
		statusCode(200);
	}
	
	//Negative tests
	@Test(priority=2)
	public void addUserFavPhotosWithInvalidUser() throws JsonParseException, JsonMappingException, IOException
	{
	
	    String invalidUser = "123";

		String response = given().when().queryParam("username", invalidUser).queryParams("photoId", photoId)
				.post("/add-fav-photos").then().statusCode(500).extract().response().asPrettyString();

		ObjectMapper mapper = new ObjectMapper();
		ResponseForUserCreation responseFromApi = mapper.readValue(response, ResponseForUserCreation.class);
		
		// assert values in json response
		Assert.assertEquals(responseFromApi.getType(),"error");
		Assert.assertEquals(responseFromApi.getMessage(),"User "+invalidUser+" does not exists in the system");

	}
	
	@Test(priority=3)
	public void removeUserFavPhotosWithInvalidUser() throws JsonParseException, JsonMappingException, IOException
	{
		
		String invalidUser = "123";

		String response = given().when().queryParam("username", invalidUser).queryParams("photoId", photoId)
				.post("/remove-fav-photos").then().statusCode(500).extract().response().asPrettyString();

		ObjectMapper mapper = new ObjectMapper();
		ResponseForUserCreation responseFromApi = mapper.readValue(response, ResponseForUserCreation.class);
		
		// assert values in json response
		Assert.assertEquals(responseFromApi.getType(),"error");
		Assert.assertEquals(responseFromApi.getMessage(),"Please check username: " + invalidUser);
	}
	
}
