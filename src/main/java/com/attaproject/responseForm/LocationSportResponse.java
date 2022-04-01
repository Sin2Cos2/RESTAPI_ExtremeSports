package com.attaproject.responseForm;

public class LocationSportResponse {

    private String locationName;
    private String sportName;
    private Double price;

    public LocationSportResponse() {
    }

    public LocationSportResponse(String locationName, String sportName, Double price) {
        this.locationName = locationName;
        this.sportName = sportName;
        this.price = price;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
