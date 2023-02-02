package com.org.concordia.photoapi.controllers;

import java.io.IOException;
import java.util.Base64;
import java.util.Base64.Decoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.concordia.photoapi.gateways.UserGateway;
import com.org.concordia.photoapi.gateways.UserGatewayImpl;
import com.org.concordia.photoapi.mappers.UserMapper;
import com.org.concordia.photoapi.mappers.UserMapperImpl;
import com.org.concordia.photoapi.model.ResponseForUserCreation;

@WebServlet(name = "addNewUserServlet", urlPatterns = "/add-user")
public class addNewUserServlet extends HttpServlet {

	private static final long serialVersionUID = 2872241476921678269L;
	private UserMapper usersMapper = new UserMapperImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String username = req.getParameter("username");
		System.out.println(username);

		String password = req.getParameter("password");
		System.out.println(password);

		// Decode password coming from UI to backend to validate authentication
		Decoder decoder = Base64.getDecoder();
		byte[] bytes = decoder.decode(password);
		String decodedPassword = new String(bytes);
		System.out.println("Decrypted password:" + decodedPassword);

		ResponseForUserCreation responseForUser = null;

		try {
			int userId = usersMapper.getUserIdByUsername(username);
			if (userId == -1) {
				if (usersMapper.addUser(username, password)) {
					responseForUser = new ResponseForUserCreation("success", "User successfully created");
				}
			} else {
				responseForUser = new ResponseForUserCreation("error",
						"User " + username + " already exists in the system");
				resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}

			ObjectMapper mapper = new ObjectMapper();
			String jsonString = mapper.writeValueAsString(responseForUser);
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
