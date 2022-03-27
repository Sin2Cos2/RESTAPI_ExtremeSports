package com.attaproject.responseForm;

import com.attaproject.model.Location;

import java.sql.Date;

public class SportLocationResponseForm extends Location {

    private Double price;
    private Date startDate;
    private Date endDate;

    public SportLocationResponseForm(Location location, Double price, Date startDate, Date endDate) {
        super(location.getId(), location.getName(), location.getRegionId(), location.getCountryId());
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
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
