package com.attaproject.responseForm;

import com.attaproject.model.Sport;

import java.sql.Date;

public class SportResponseForm extends Sport{

    private Double price;
    private Date startDate;
    private Date endDate;

    public SportResponseForm(int id, String name, Double price, Date startDate, Date endDate) {
        super(id, name);
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
