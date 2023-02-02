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
import com.org.concordia.photoapi.mappers.UserMapper;
import com.org.concordia.photoapi.mappers.UserMapperImpl;
import com.org.concordia.photoapi.model.Photo;
import com.org.concordia.photoapi.model.ResponseForUserCreation;

@WebServlet(name = "favouritesServlet", urlPatterns = "/get-fav-photos")
public class FavouritesServlet extends HttpServlet {

	private static final long serialVersionUID = 2872241476921678269L;
	private PhotoMapper photosMapper = new PhotoMapperImpl();
	private UserMapper usersMapper = new UserMapperImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		System.out.println(username);

		ObjectMapper mapper = new ObjectMapper();
		String jsonString;

		try {
			int userId = usersMapper.getUserIdByUsername(username);
			if (userId != -1) {
				List<Photo> photos = photosMapper.getUserFavouritePhotos(userId);
				jsonString = mapper.writeValueAsString(photos);
			} else {
				ResponseForUserCreation responseForUser = new ResponseForUserCreation("error",
						"User " + username + " does not exists in the system");
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
