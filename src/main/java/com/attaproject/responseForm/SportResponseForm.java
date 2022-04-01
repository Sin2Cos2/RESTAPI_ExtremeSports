package com.attaproject.responseForm;

import com.attaproject.model.Sport;

import java.util.List;

public class SportResponseForm extends Sport {

    private List<SportLocationResponseList> locations;

    public SportResponseForm(Sport sport, List<SportLocationResponseList> responseFormList) {
        super(sport.getId(), sport.getName());
        this.locations = responseFormList;
    }

    public List<SportLocationResponseList> getLocations() {
        return locations;
    }

    public void setLocations(List<SportLocationResponseList> locations) {
        this.locations = locations;
    }
}
