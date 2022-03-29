package com.attaproject.model;

public class Location {

    private int id;
    private String name;
    private int regionId;
    private int countryId;

    public Location(Integer id, String name, int regionId, int countryId) {
        this.id = id;
        this.name = name;
        this.regionId = regionId;
        this.countryId = countryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
}
