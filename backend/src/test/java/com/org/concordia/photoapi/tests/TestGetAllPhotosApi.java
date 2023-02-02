package com.org.concordia.photoapi.tests;

import static io.restassured.RestAssured.given;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.concordia.photoapi.model.Photo;
import java.io.IOException;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestGetAllPhotosApi extends BaseSetup {

	@Test
	public void showAllPhotosToUser() throws JsonParseException, JsonMappingException, IOException {
		String getAllPhotosInResponse = given().when().get("/get-all-photos").then().statusCode(200).extract()
				.response().asPrettyString();

		ObjectMapper mapper = new ObjectMapper();
		List<Photo> photos = mapper.readValue(getAllPhotosInResponse, new TypeReference<List<Photo>>() {
		});

		// assert values in json response
		Assert.assertEquals(photos.get(0).getPhotoId(), 1234567);
		Assert.assertEquals(photos.get(0).getAvgColor(), "#515149");
		Assert.assertEquals(photos.get(0).getTitle(), "Its Test Data");
		Assert.assertEquals(photos.get(0).getImageMediumSize(), "photo-1234567-md.jpeg");
		Assert.assertEquals(photos.get(0).getImageLargeSize(), "photo-1234567-lg.jpeg");
		Assert.assertEquals(photos.get(0).getImageOrignalSize(), "photo-1234567-o.jpeg");
	}
}
