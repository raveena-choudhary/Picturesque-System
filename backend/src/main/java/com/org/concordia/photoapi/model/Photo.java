package com.org.concordia.photoapi.model;

public class Photo {

  private Integer photoId;

  private String avgColor;
  private String title;
  private String imageMediumSize;
  private String imageLargeSize;
  private String imageOrignalSize;

  public Integer getPhotoId() {
    return photoId;
  }

  public void setPhotoId(Integer photoId) {
    this.photoId = photoId;
  }

  // public Photographer getPhotographer() {
  //   return photographer;
  // }

  // public void setPhotographer(Photographer photographer) {
  //   this.photographer = photographer;
  // }

  public String getAvgColor() {
    return avgColor;
  }

  public void setAvgColor(String avgColor) {
    this.avgColor = avgColor;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getImageMediumSize() {
    return imageMediumSize;
  }

  public void setImageMediumSize(String imageMediumSize) {
    this.imageMediumSize = imageMediumSize;
  }

  public String getImageLargeSize() {
    return imageLargeSize;
  }

  public void setImageLargeSize(String imageLargeSize) {
    this.imageLargeSize = imageLargeSize;
  }

  public String getImageOrignalSize() {
    return imageOrignalSize;
  }

  public void setImageOrignalSize(String imageOrignalSize) {
    this.imageOrignalSize = imageOrignalSize;
  }
}
