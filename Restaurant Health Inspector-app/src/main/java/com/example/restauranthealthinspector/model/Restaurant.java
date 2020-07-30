package com.example.restauranthealthinspector.model;

import android.content.Context;

/**
 * A restaurant class to store the tracking number, name , address,
 * inspections reports and an icon ID for a restaurant.
 */
public class Restaurant {
    private String trackingNumber;
    private String restaurantName;
    private Address address;
    private RestaurantIcon setCurrentID;
    private boolean favourite;
    private InspectionsManager inspectionsManager = new InspectionsManager();

    public Restaurant(String trackingNumber, String restaurantName, Address address) {
        this.trackingNumber = trackingNumber;
        this.restaurantName = restaurantName;
        this.address = address;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public Address getAddress() {
        return address;
    }

    public InspectionsManager getInspectionsManager() {
        return inspectionsManager;
    }

//    public int setIconID(Context context){
//        RestaurantIcon iconID = new RestaurantIcon(context,trackingNumber,restaurantName);
//    }

    public int getIconID() {
        return setCurrentID.getIconID();

    }

    public void setIconID(Context context, String restaurantName){
        setCurrentID = new RestaurantIcon(context,restaurantName);

    }

    public void setFavourite(boolean favourite){
        this.favourite = favourite;
    }

    public boolean getFavourite(){
        return favourite;
    }
}
