package com.org.concordia.photoapi.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.org.concordia.photoapi.gateways.PhotoGateway;
import com.org.concordia.photoapi.gateways.PhotoGatewayImpl;
import com.org.concordia.photoapi.model.Photo;

public class PhotoMapperImpl implements PhotoMapper {

	private PhotoGateway photosGateway = new PhotoGatewayImpl();

	@Override
	public List<Photo> getPhotos() {
		try {
			ResultSet rs = photosGateway.getPhotos();

			//refactoring
			return (rs != null) ? getListOfPhotos(rs) : null;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return null;
	}

	@Override
	public Photo getPhotoById(int photoId) {
		Photo photo = new Photo();
		try {
			ResultSet rs = photosGateway.getPhotos();

			if (rs != null) {
				while (rs.next()) {
					photo.setPhotoId(rs.getInt("photo_id"));
					photo.setAvgColor(rs.getString("avg_color"));
					photo.setTitle(rs.getString("title"));
					photo.setImageMediumSize(rs.getString("imageMediumSize"));
					photo.setImageLargeSize(rs.getString("imageLargeSize"));
					photo.setImageOrignalSize(rs.getString("imageOrignalSize"));

				}
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return photo;
	}

	@Override
	public List<Photo> getUserFavouritePhotos(int userId) {
		
		try {
			ResultSet rs = photosGateway.getUserFavouritePhotos(userId);

			//refactoring
			return  (rs != null) ? getListPhotosByLikesAndFavQuery(rs) : null;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public List<Photo> getUserLikedPhotos(int userId) {

		try {
			ResultSet rs = photosGateway.getUserLikedPhotos(userId);
			
			return (rs != null) ? getListPhotosByLikesAndFavQuery(rs) : null;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	private static List<Photo> getListOfPhotos(ResultSet rs) {

		List<Photo> listOfPhotos = new ArrayList<Photo>();

		try {

			if (rs != null) {
				while (rs.next()) {
					Photo photo = new Photo();
					photo.setPhotoId(rs.getInt("photo_id"));
					photo.setAvgColor(rs.getString("avg_color"));
					photo.setTitle(rs.getString("title"));
					photo.setImageMediumSize(rs.getString("imageMediumSize"));
					photo.setImageLargeSize(rs.getString("imageLargeSize"));
					photo.setImageOrignalSize(rs.getString("imageOrignalSize"));

					listOfPhotos.add(photo);
				}
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return listOfPhotos;
	}

	private static List<Photo> getListPhotosByLikesAndFavQuery(ResultSet rs) {
		List<Photo> listOfPhotos = new ArrayList<Photo>();

		try {
			if (rs != null) {
				while (rs.next()) {
					Photo photo = new Photo();
					photo.setPhotoId(rs.getInt("photo_id"));
					photo.setAvgColor(rs.getString("avg_color"));
					photo.setTitle(rs.getString("title"));
					photo.setImageMediumSize(rs.getString("imageMediumSize"));
					photo.setImageLargeSize(rs.getString("imageLargeSize"));
					photo.setImageOrignalSize(rs.getString("imageOrignalSize"));

					listOfPhotos.add(photo);

				}

			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return listOfPhotos;
	}

	@Override
	public int getNoOfLikesOfPhoto(int photoId) {
		int noOfLikes = 0;
		ResultSet rs = photosGateway.getNoOfLikesOfPhoto(photoId);

		try {

			if (rs != null) {

				while (rs.next()) {
					noOfLikes++;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return noOfLikes;
	}

	@Override
	public void addUserLikedPhotos(int userId, int photoId) {
		photosGateway.addUserLikedPhotos(userId, photoId);

	}

	@Override
	public void addUserFavPhotos(int userId, int photoId) {
		photosGateway.addUserFavPhotos(userId, photoId);

	}

	@Override
	public void removeUserLikedPhotos(int userId, int photoId) {
		photosGateway.removeUserLikedPhotos(userId, photoId);

	}

	@Override
	public void removeUserFavPhotos(int userId, int photoId) {
		photosGateway.removeUserFavPhotos(userId, photoId);

	}
}
