package com.org.concordia.photoapi.model;

import java.util.ArrayList;

public class User {

  private int userId;
  private String username;
  private String password;
  private ArrayList<Integer> favouriteIds;
  private ArrayList<Integer> likeIds;

  public User() {}

  public User(
    int userId,
    String username,
    ArrayList<Integer> favouriteIds,
    ArrayList<Integer> likeIds
  ) {
    this.userId = userId;
    this.username = username;
    this.favouriteIds = favouriteIds;
    this.likeIds = likeIds;
  }

  public String getPassword() {
    return password;
  }

  public String getUsername() {
    return username;
  }

  public int getUserId() {
    return userId;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public ArrayList<Integer> getFavourite() {
    return favouriteIds;
  }

  public void setFavourite(ArrayList<Integer> favouriteIds) {
    this.favouriteIds = favouriteIds;
  }

  public ArrayList<Integer> getLike() {
    return likeIds;
  }

  public void setLike(ArrayList<Integer> likeIds) {
    this.likeIds = likeIds;
  }
}
