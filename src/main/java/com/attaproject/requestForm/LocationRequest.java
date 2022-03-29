package com.attaproject.requestForm;

import java.util.List;

public class LocationRequest {

    private String name;
    private String regionName;
    private String countryName;
    private List<LocationSportRequest> locationSport;

    public LocationRequest() {
    }

    public LocationRequest(String name, String regionName, String countryName, List<LocationSportRequest> locationSport) {
        this.name = name;
        this.regionName = regionName;
        this.countryName = countryName;
        this.locationSport = locationSport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public List<LocationSportRequest> getLocationSport() {
        return locationSport;
    }

    public void setLocationSport(List<LocationSportRequest> locationSport) {
        this.locationSport = locationSport;
    }
}
