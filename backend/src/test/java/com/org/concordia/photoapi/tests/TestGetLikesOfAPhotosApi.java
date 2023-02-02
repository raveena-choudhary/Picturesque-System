package com.org.concordia.photoapi.tests;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import io.restassured.response.Response;

public class TestGetLikesOfAPhotosApi extends BaseSetup {
	public static final String photoId = "1234567";

	@Test(priority = 0)
	public void getLikedPhotos() throws JsonParseException, JsonMappingException, IOException {

		Response getLikesOfAPhotosInResponse = given().when().queryParam("photoId", photoId).get("/get-likes-of-photo").then()
				.assertThat().statusCode(200).extract().response();

		System.out.println(getLikesOfAPhotosInResponse);
		
		int noOfLikesForAPhoto = Integer.parseInt(getLikesOfAPhotosInResponse.asString());

		// assert values in json response
		Assert.assertEquals(noOfLikesForAPhoto, 2);
	}
}
