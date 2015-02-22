package com.expedia.server.model;

public class RecommendedHotel {

	String imageURL ;
	String description;
	Double userRating;
	
	
	public Double getUserRating() {
		return userRating;
	}

	public void setUserRating(Double userRating) {
		this.userRating = userRating;
	}

	public RecommendedHotel(String imageUrl, String description,Double rating) {
		super();
		this.imageURL = imageUrl;
		this.description = description;
		this.userRating = rating;
	}
	
	
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
		
}
