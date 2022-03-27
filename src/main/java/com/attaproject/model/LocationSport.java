package com.attaproject.model;

import java.sql.Date;

public class LocationSport {

    private int locationId;
    private int sportId;
    private Double price;
    private Date startDate;
    private Date endDate;

    public LocationSport(int locationId, int sportId, Double price, Date startDate, Date endDate) {
        this.locationId = locationId;
        this.sportId = sportId;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getSportId() {
        return sportId;
    }

    public void setSportId(int sportId) {
        this.sportId = sportId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
