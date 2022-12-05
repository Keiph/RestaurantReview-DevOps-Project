package com.dvops.maven.eclipse;

public class Restaurant {
	protected String restaurantName;
	protected String restaurantLocation;
	protected String openingTime;
	protected String closingTime;
	protected String restaurantDescription;
	protected String restaurantCuisine;
	
	public Restaurant (String restaurantName, String restaurantLocation, String openingTime, String closingTime, String restaurantDescription,String restaurantCuisine) {
		super();
		this.restaurantName = restaurantName;
		this.restaurantLocation = restaurantLocation;
		this.openingTime = openingTime;
		this.closingTime = closingTime;
		this.restaurantDescription = restaurantDescription;
		this.restaurantCuisine = restaurantCuisine;
	}
	
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public String getRestaurantLocation() {
		return restaurantLocation;
	}
	public void setRestaurantLocation(String restaurantLocation) {
		this.restaurantLocation = restaurantLocation;
	}
	public String getOpeningTime() {
		return openingTime;
	}
	public void setOpeningTime(String openingTime) {
		this.openingTime = openingTime;
	}
	public String getClosingTime() {
		return closingTime;
	}
	public void setClosingTime(String closingTime) {
		this.closingTime = closingTime;
	}
	public String getRestaurantDescription() {
		return restaurantDescription;
	}
	public void setRestaurantDescription(String restaurantDescription) {
		this.restaurantDescription = restaurantDescription;
	}
	public String getRestaurantCuisine() {
		return restaurantCuisine;
	}
	public void setRestaurantCuisine(String restaurantCuisine) {
		this.restaurantCuisine = restaurantCuisine;
	}
	
}
