package com.org.concordia.photoapi.controllers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.concordia.photoapi.mappers.PhotoMapper;
import com.org.concordia.photoapi.mappers.PhotoMapperImpl;
import com.org.concordia.photoapi.model.Photo;

@WebServlet(name = "sendImageServlet", urlPatterns = "/assets/*")
public class SendImageServlet extends HttpServlet {

	private static final long serialVersionUID = 2872241476921678269L;
	private PhotoMapper photosMapper = new PhotoMapperImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String imagesBase = Paths.get("").toAbsolutePath().toString() + "\\resources\\photos";
			List<Photo> photos = photosMapper.getPhotos();

			if (photos.size() != 0) {
				System.out.println(photos.size());
				String URLAfterWebDomain = req.getRequestURI();
				System.out.println("URLAfterWebDomain " + URLAfterWebDomain);
				if (!URLAfterWebDomain.startsWith("/photo-api/assets/"))
					return;
				String relativeImagePath = URLAfterWebDomain.substring("/photo-api/assets".length());

				resp.setContentType("image/jpeg");

				ServletOutputStream outStream;
				outStream = resp.getOutputStream();
				System.out.println(imagesBase + relativeImagePath);
				FileInputStream fin = new FileInputStream(imagesBase + relativeImagePath);

				BufferedInputStream bin = new BufferedInputStream(fin);
				BufferedOutputStream bout = new BufferedOutputStream(outStream);
				int ch = 0;
				while ((ch = bin.read()) != -1)
					bout.write(ch);
				bin.close();

				fin.close();
				bout.close();
				outStream.close();
			} else {
				ObjectMapper mapper = new ObjectMapper();
				String jsonString = mapper.writeValueAsString("No image found at " + imagesBase);
				resp.setContentType("application/json");
				resp.setCharacterEncoding("UTF-8");
				resp.getWriter().write(jsonString);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
