package com.org.concordia.photoapi.controllers;

import java.io.IOException;
import java.util.ArrayList;
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
import com.org.concordia.photoapi.model.User;

@WebServlet(name = "userDetailsAfterLoginServlet", urlPatterns = "/get-user")
public class userDetailsAfterLoginServlet extends HttpServlet {

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
				List<Photo> likedPhotos = photosMapper.getUserLikedPhotos(userId);
				List<Photo> favPhotos = photosMapper.getUserFavouritePhotos(userId);

				System.out.println(likedPhotos.size());
				System.out.println(favPhotos.size());

				ArrayList<Integer> liked_photo_ids = new ArrayList<Integer>();
				ArrayList<Integer> fav_photo_ids = new ArrayList<Integer>();

				// liked photo ids list
				for (int index = 0; index < likedPhotos.size(); index++) {
					liked_photo_ids.add(likedPhotos.get(index).getPhotoId());
				}

				// favourite photo ids list
				for (int index = 0; index < favPhotos.size(); index++) {
					fav_photo_ids.add(favPhotos.get(index).getPhotoId());
				}

				User userObject = new User(userId, username, fav_photo_ids, liked_photo_ids);

				jsonString = mapper.writeValueAsString(userObject);
				System.out.println(jsonString);
				resp.setContentType("application/json");
				resp.setCharacterEncoding("UTF-8");
				resp.getWriter().write(jsonString);

			} else {

				ResponseForUserCreation responseForUser = new ResponseForUserCreation("error",
						"User " + username + " does not exists in the system");
				jsonString = mapper.writeValueAsString(responseForUser);
				resp.setContentType("application/json");
				resp.setCharacterEncoding("UTF-8");
				resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				resp.getWriter().write(jsonString);
			}

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
