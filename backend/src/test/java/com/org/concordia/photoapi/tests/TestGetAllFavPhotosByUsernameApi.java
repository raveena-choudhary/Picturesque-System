package com.org.concordia.photoapi.tests;

import static io.restassured.RestAssured.given;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.concordia.photoapi.model.Photo;
import com.org.concordia.photoapi.model.ResponseForUserCreation;
import java.io.IOException;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestGetAllFavPhotosByUsernameApi extends BaseSetup {

	public static final String user = "testuser";
	public static final String photoId = "1234567";

	// positive tests
	@Test(priority = 0)
	public void addUserFavPhotos() throws JsonParseException, JsonMappingException, IOException {
		given().when().queryParam("username", user).queryParams("photoId", photoId).post("/add-fav-photos").then()
				.assertThat().statusCode(200);
	}

	@Test(priority = 1)
	public void getFavPhotos() throws JsonParseException, JsonMappingException, IOException {
		String getAllFavPhotosInResponse = given().when().queryParam("username", user).get("/get-fav-photos").then()
				.assertThat().statusCode(200).extract().response().asPrettyString();

		System.out.println(getAllFavPhotosInResponse);

		ObjectMapper mapper = new ObjectMapper();
		List<Photo> photos = mapper.readValue(getAllFavPhotosInResponse, new TypeReference<List<Photo>>() {
		});

		// assert values in json response
		Assert.assertEquals(photos.get(0).getPhotoId(), 1234567);
		Assert.assertEquals(photos.get(0).getAvgColor(), "#515149");
		Assert.assertEquals(photos.get(0).getTitle(), "Its Test Data");
		Assert.assertEquals(photos.get(0).getImageMediumSize(), "photo-1234567-md.jpeg");
		Assert.assertEquals(photos.get(0).getImageLargeSize(), "photo-1234567-lg.jpeg");
		Assert.assertEquals(photos.get(0).getImageOrignalSize(), "photo-1234567-o.jpeg");
	}

	// negative tests
	@Test(priority = 2)
	public void getUserFavPhotosWithInvalidUser() throws JsonParseException, JsonMappingException, IOException {
		String invalidUser = "123";

		String response = given().when().queryParam("username", invalidUser).queryParams("photoId", photoId)
				.get("/get-fav-photos").then().statusCode(500).extract().response().asPrettyString();

		ObjectMapper mapper = new ObjectMapper();
		ResponseForUserCreation responseFromApi = mapper.readValue(response, ResponseForUserCreation.class);

		// assert values in json response
		Assert.assertEquals(responseFromApi.getType(), "error");
		Assert.assertEquals(responseFromApi.getMessage(), "User " + invalidUser + " does not exists in the system");
	}
}
