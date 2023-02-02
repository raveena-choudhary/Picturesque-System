package com.org.concordia.photoapi.tests;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.concordia.photoapi.model.Photo;
import com.org.concordia.photoapi.model.Photographer;

public class TestGetAllPhotosByPhotographer extends BaseSetup {

	@Test
	public void showAllPhotosToUserByPhotographer() throws JsonParseException, JsonMappingException, IOException {
		String getAllPhotosInResponse = given().when().get("/get-photographers-with-photos").then().statusCode(200)
				.extract().response().asPrettyString();

		ObjectMapper mapper = new ObjectMapper();
		List<Photographer> photographers = mapper.readValue(getAllPhotosInResponse,
				new TypeReference<List<Photographer>>() {
				});

		for (int index = 0; index < photographers.size(); index++) {
			System.out.println(photographers.get(index).getphotographerId());
			if (photographers.get(index).getphotographerId() == 1234) {
				List<Photo> photos = photographers.get(index).getPhotos();

				// assert values for Photographer details
				Assert.assertEquals(photographers.get(index).getphotographerId(), 1234);
				Assert.assertEquals(photographers.get(index).getphotographerName(), "test");
				Assert.assertEquals(photographers.get(index).getphotographerUrl(), "@test");

				// assert values for photos by Photographer
				Assert.assertEquals(photos.get(0).getPhotoId(), 1234567);
				Assert.assertEquals(photos.get(0).getAvgColor(), "#515149");
				Assert.assertEquals(photos.get(0).getTitle(), "Its Test Data");
				Assert.assertEquals(photos.get(0).getImageMediumSize(), "photo-1234567-md.jpeg");
				Assert.assertEquals(photos.get(0).getImageLargeSize(), "photo-1234567-lg.jpeg");
				Assert.assertEquals(photos.get(0).getImageOrignalSize(), "photo-1234567-o.jpeg");
			}
		}
	}
}
