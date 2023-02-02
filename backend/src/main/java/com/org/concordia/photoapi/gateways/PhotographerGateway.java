package com.org.concordia.photoapi.gateways;

import java.sql.ResultSet;

public interface PhotographerGateway {
	
  public ResultSet getPhotosByPhotographerId(int photographerId);

  public ResultSet getPhotographers();
}
