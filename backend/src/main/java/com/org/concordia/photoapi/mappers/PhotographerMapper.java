package com.org.concordia.photoapi.mappers;

import com.org.concordia.photoapi.model.Photo;
import com.org.concordia.photoapi.model.Photographer;
import java.util.List;

public interface PhotographerMapper {
  public List<Photo> getListOfPhotosByPhotographerId(int photographerId);

  public List<Photographer> getListOfPhotographers();
}
