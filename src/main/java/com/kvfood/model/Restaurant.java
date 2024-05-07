package com.kvfood.model;

public class Restaurant {

	private int restaurantId;
	private String restaurantName;
	private int rating;
	private String imagePath;
	private int eta;
	private String cusineType;
	private String address;
	private boolean isActive;
	private int restaurantOwnerId;
	
	public Restaurant() {
		
	}
	

	public Restaurant(int restaurantId, String restaurantName, int rating, String imagePath, int eta, String cusineType,
			String address, boolean isActive, int restaurantOwnerId) {
		super();
		this.restaurantId = restaurantId;
		this.restaurantName = restaurantName;
		this.rating = rating;
		this.imagePath = imagePath;
		this.eta = eta;
		this.cusineType = cusineType;
		this.address = address;
		this.isActive = isActive;
		this.restaurantOwnerId = restaurantOwnerId;
	}



	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getEta() {
		return eta;
	}

	public void setEta(int eta) {
		this.eta = eta;
	}

	public int getRestaurantOwnerId() {
		return restaurantOwnerId;
	}

	public void setRestaurantOwnerId(int restaurantOwnerId) {
		this.restaurantOwnerId = restaurantOwnerId;
	}


	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}


	public String getCusineType() {
		return cusineType;
	}

	public void setCusineType(String cusineType) {
		this.cusineType = cusineType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "Restaurant [restaurantId=" + restaurantId + ", restaurantName=" + restaurantName + ", cusineType="
				+ cusineType + ", address=" + address + ", imagePath=" + imagePath + ", eta=" + eta + ", rating="
				+ rating + ", isActive=" + isActive + ", restaurantOwnerId=" + restaurantOwnerId + "]";
	}
		
}

