package com.org.concordia.photoapi.model;

import java.util.List;

public class Photographer {
	private Integer photographerId;
	private String photographerName;
	private String photographerUrl;
	private List<Photo> photos;

	public Photographer() {
	}

	public Photographer(Integer photographerId, String photographerName, String photographerUrl, List<Photo> photos) {
		this.photographerId = photographerId;
		this.photographerName = photographerName;
		this.photographerUrl = photographerUrl;
		this.photos = photos;
	}

	public Integer getphotographerId() {
		return photographerId;
	}

	public void setPhotographerId(Integer photographerId) {
		this.photographerId = photographerId;
	}

	public String getphotographerName() {
		return photographerName;
	}

	public void setphotographerName(String photographerName) {
		this.photographerName = photographerName;
	}

	public String getphotographerUrl() {
		return photographerUrl;
	}

	public void setphotographerUrl(String photographerUrl) {
		this.photographerUrl = photographerUrl;
	}

	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}
}
