package com.org.concordia.photoapi.tests;

import static io.restassured.RestAssured.given;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.concordia.photoapi.model.ResponseForUserCreation;
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestValidateUserApi extends BaseSetup {

  @Test
  public void userAuthenticationSuccess()
    throws JsonParseException, JsonMappingException, IOException {
    String getMessageAsResponse = given()
      .when()
      .queryParam("username", "testuser")
      .queryParams("password", "test")
      .get("/validate-user")
      .then()
      .statusCode(200)
      .extract()
      .response()
      .asPrettyString();

    ObjectMapper mapper = new ObjectMapper();
    ResponseForUserCreation response = mapper.readValue(
      getMessageAsResponse,
      ResponseForUserCreation.class
    );

    // assert values in json response
    Assert.assertEquals(response.getType(), "success");
    Assert.assertEquals(response.getMessage(), "User Authenticated");
  }

  @Test
  public void userAuthenticationWrongPassword()
    throws JsonParseException, JsonMappingException, IOException {
    String getMessageAsResponse = given()
      .when()
      .queryParam("username", "testuser")
      .queryParams("password", "123")
      .get("/validate-user")
      .then()
      .statusCode(500)
      .extract()
      .response()
      .asPrettyString();

    ObjectMapper mapper = new ObjectMapper();
    ResponseForUserCreation response = mapper.readValue(
      getMessageAsResponse,
      ResponseForUserCreation.class
    );

    // assert values in json response
    Assert.assertEquals(response.getType(), "error");
    Assert.assertEquals(
      response.getMessage(),
      "Please check the password entered"
    );
  }

  @Test
  public void userAuthenticationFailure()
    throws JsonParseException, JsonMappingException, IOException {
	
	String username = "test";
	String password = "test";
	
    String getMessageAsResponse = given()
      .when()
      .queryParam("username", username)
      .queryParams("password", password)
      .get("/validate-user")
      .then()
      .statusCode(500)
      .extract()
      .response()
      .asPrettyString();

    ObjectMapper mapper = new ObjectMapper();
    ResponseForUserCreation response = mapper.readValue(
      getMessageAsResponse,
      ResponseForUserCreation.class
    );

    // assert values in json response
    Assert.assertEquals(response.getType(), "error");
    Assert.assertEquals(
      response.getMessage(),
      "User " + username + " does not exists in the system"
    );
  }
}
