package com.attaproject.responseForm;

import com.attaproject.model.Sport;

public class SportResponseForm {

    private Sport sport;
    private Double price;

    public SportResponseForm(Sport sport, Double price) {
        this.sport = sport;
        this.price = price;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
