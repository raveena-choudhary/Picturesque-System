package com.org.concordia.photoapi.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.concordia.photoapi.mappers.PhotographerMapper;
import com.org.concordia.photoapi.mappers.PhotographerMapperImpl;
import com.org.concordia.photoapi.model.Photo;
import com.org.concordia.photoapi.model.Photographer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
  name = "getPhotographersServlet",
  urlPatterns = "/get-photographers-with-photos"
)
public class getPhotographersServlet extends HttpServlet {

  private static final long serialVersionUID = 2872241476921678269L;
  private PhotographerMapper photographerMapper = new PhotographerMapperImpl();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
    try {
      List<Photographer> photographers = photographerMapper.getListOfPhotographers();
      for (Photographer photographer : photographers) {
        List<Photo> photos = photographerMapper.getListOfPhotosByPhotographerId(
          photographer.getphotographerId()
        );

        photographer.setPhotos(photos);
      }

      ObjectMapper mapper = new ObjectMapper();
      String jsonString = mapper.writeValueAsString(photographers);
      System.out.println(jsonString);
      resp.setContentType("application/json");
      resp.setCharacterEncoding("UTF-8");
      resp.getWriter().write(jsonString);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
