package com.org.concordia.photoapi.gateways;

import java.sql.ResultSet;

public interface PhotoGateway {

	public ResultSet getPhotos();

	public ResultSet getPhotoById(int photoId);

	public ResultSet getUserFavouritePhotos(int userId);

	public ResultSet getUserLikedPhotos(int userId);

	public ResultSet getNoOfLikesOfPhoto(int photoId);

	public void addUserLikedPhotos(int userId, int photoId);

	public void addUserFavPhotos(int userId, int photoId);

	public void removeUserLikedPhotos(int userId, int photoId);

	public void removeUserFavPhotos(int userId, int photoId);

}
