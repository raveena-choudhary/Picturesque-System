package com.org.concordia.photoapi.mappers;

import com.org.concordia.photoapi.gateways.PhotographerGateway;
import com.org.concordia.photoapi.gateways.PhotographerGatewayImpl;
import com.org.concordia.photoapi.model.Photo;
import com.org.concordia.photoapi.model.Photographer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhotographerMapperImpl implements PhotographerMapper {

  private PhotographerGateway photographerGateway = new PhotographerGatewayImpl();

  @Override
  public List<Photo> getListOfPhotosByPhotographerId(int photographerId) {
    List<Photo> listOfPhotosByPhotographerId = new ArrayList<Photo>();

    try {
      ResultSet rs = photographerGateway.getPhotosByPhotographerId(
        photographerId
      );
      if (rs != null) {
        while (rs.next()) {
          Photo photo = new Photo();
          photo.setPhotoId(rs.getInt("photo_id"));
          photo.setAvgColor(rs.getString("avg_color"));
          photo.setTitle(rs.getString("title"));
          photo.setImageMediumSize(rs.getString("imageMediumSize"));
          photo.setImageLargeSize(rs.getString("imageLargeSize"));
          photo.setImageOrignalSize(rs.getString("imageOrignalSize"));

          listOfPhotosByPhotographerId.add(photo);
        }
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }

    return listOfPhotosByPhotographerId;
  }

  @Override
  public List<Photographer> getListOfPhotographers() {
    List<Photographer> listOfPhotographers = new ArrayList<Photographer>();

    try {
      ResultSet rs = photographerGateway.getPhotographers();

      if (rs != null) {
        while (rs.next()) {
          Photographer photographer = new Photographer();
          photographer.setPhotographerId(rs.getInt("photographer_id"));
          photographer.setphotographerName(rs.getString("p_name"));
          photographer.setphotographerUrl(rs.getString("p_url"));
          listOfPhotographers.add(photographer);
        }
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }

    return listOfPhotographers;
  }
}
