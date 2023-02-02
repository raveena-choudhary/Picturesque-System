package com.org.concordia.photoapi.tests;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestGetLikesOfAllPhotosApi extends BaseSetup {
	public static final int photoId1 = 14173145;
	public static final int photoId2 = 14155604;

	@Test(priority = 0)
	public void getLikedPhotos() throws JsonParseException, JsonMappingException, IOException {

		String getLikesOfAllPhotosInResponse = given().when().get("/get-likes-of-all-photos").then()
				.assertThat().statusCode(200).extract().response().asPrettyString();

		System.out.println(getLikesOfAllPhotosInResponse);

		ObjectMapper mapper = new ObjectMapper();
		HashMap<Integer, Integer> getPhotos = mapper.readValue(getLikesOfAllPhotosInResponse, new TypeReference<HashMap<Integer, Integer>>() {
		});

		// assert values in json response
		Assert.assertEquals(getPhotos.get(photoId1), 1);
		Assert.assertEquals(getPhotos.get(photoId2), 0);
	}
}
