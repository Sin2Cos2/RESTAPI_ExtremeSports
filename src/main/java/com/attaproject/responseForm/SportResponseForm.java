package com.attaproject.responseForm;

import com.attaproject.model.Sport;

import java.util.List;

public class SportResponseForm extends Sport {

    private List<SportLocationResponseForm> locations;

    public SportResponseForm(Sport sport, List<SportLocationResponseForm> responseFormList) {
        super(sport.getId(), sport.getName());
        this.locations = responseFormList;
    }

    public List<SportLocationResponseForm> getLocations() {
        return locations;
    }

    public void setLocations(List<SportLocationResponseForm> locations) {
        this.locations = locations;
    }
}
