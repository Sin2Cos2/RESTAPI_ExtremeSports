package com.attaproject.requestForm;

public class RegionRequest {
    private String name;
    private String countryName;

    public RegionRequest(String name, String countryName) {
        this.name = name;
        this.countryName = countryName;
    }

    public RegionRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
