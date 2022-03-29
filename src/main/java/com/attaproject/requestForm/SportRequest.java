package com.attaproject.requestForm;


import java.util.List;

public class SportRequest {

    private String name;
    private List<SportLocationRequest> locations;

    public SportRequest(String name, List<SportLocationRequest> locations) {
        this.name = name;
        this.locations = locations;
    }

    public SportRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SportLocationRequest> getLocations() {
        return locations;
    }

    public void setLocations(List<SportLocationRequest> locations) {
        this.locations = locations;
    }
}
