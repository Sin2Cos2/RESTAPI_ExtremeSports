package com.attaproject.responseForm;

import com.attaproject.model.Sport;

import java.sql.Date;

public class LocationSportResponseList extends Sport{

    private Double price;
    private Date startDate;
    private Date endDate;

    public LocationSportResponseList(Integer id, String name) {
        super(id, name);
    }

    public LocationSportResponseList(Sport sport, Double price, Date startDate, Date endDate) {
        super(sport.getId(), sport.getName());
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
