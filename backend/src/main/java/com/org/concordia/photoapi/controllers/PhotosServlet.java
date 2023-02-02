package com.org.concordia.photoapi.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.concordia.photoapi.mappers.PhotoMapper;
import com.org.concordia.photoapi.mappers.PhotoMapperImpl;
import com.org.concordia.photoapi.model.Photo;
import com.org.concordia.photoapi.model.ResponseForUserCreation;

@WebServlet(name = "photosServlet", urlPatterns = "/get-all-photos")
public class PhotosServlet extends HttpServlet {

	private static final long serialVersionUID = 2872241476921678269L;
	private PhotoMapper photosMapper = new PhotoMapperImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			ObjectMapper mapper = new ObjectMapper();
			String jsonString;
			List<Photo> photos = photosMapper.getPhotos();

			if (photos.size() != 0) {
				System.out.println(photos.size());
				System.out.println(photos.get(0).getPhotoId());
				jsonString = mapper.writeValueAsString(photos);
			} else {
				ResponseForUserCreation responseForUser = new ResponseForUserCreation("error", "No photos found in DB");
				jsonString = mapper.writeValueAsString(responseForUser);
				resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}

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
