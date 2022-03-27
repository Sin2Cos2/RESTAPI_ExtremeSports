package com.attaproject.responseForm;

import com.attaproject.model.Sport;

public class SportResponseForm extends Sport{

    private Double price;

    public SportResponseForm(Sport sport, Double price) {
        super(sport.getId(), sport.getName());
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
