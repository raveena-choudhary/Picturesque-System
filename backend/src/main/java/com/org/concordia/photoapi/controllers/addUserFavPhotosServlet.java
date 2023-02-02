package com.org.concordia.photoapi.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.concordia.photoapi.mappers.PhotoMapper;
import com.org.concordia.photoapi.mappers.PhotoMapperImpl;
import com.org.concordia.photoapi.mappers.UserMapper;
import com.org.concordia.photoapi.mappers.UserMapperImpl;
import com.org.concordia.photoapi.model.ResponseForUserCreation;

@WebServlet(name = "addUserFavPhotosServlet", urlPatterns = "/add-fav-photos")
public class addUserFavPhotosServlet extends HttpServlet {

	private static final long serialVersionUID = 2872241476921678269L;
	private PhotoMapper photosMapper = new PhotoMapperImpl();
	private UserMapper usersMapper = new UserMapperImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		System.out.println(username);

		int photoId = Integer.parseInt(req.getParameter("photoId"));
		System.out.println(photoId);

		try {
			int userId = usersMapper.getUserIdByUsername(username);
			if (userId != -1) {
				photosMapper.addUserFavPhotos(userId, photoId);
			} else {
				ObjectMapper mapper = new ObjectMapper();
				ResponseForUserCreation responseForUser = new ResponseForUserCreation("error",
						"User " + username + " does not exists in the system");
				String jsonString = mapper.writeValueAsString(responseForUser);
				resp.setContentType("application/json");
				resp.setCharacterEncoding("UTF-8");
				resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				resp.getWriter().write(jsonString);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
