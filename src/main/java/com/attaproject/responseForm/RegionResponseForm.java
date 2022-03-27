package com.attaproject.responseForm;

import com.attaproject.model.Country;
import com.attaproject.model.Region;

import java.util.List;

public class RegionResponseForm {

    private Region region;
    private Country country;
    private List<LocationResponseForm> locations;

    public RegionResponseForm(Region region, Country country, List<LocationResponseForm> locations) {
        this.region = region;
        this.country = country;
        this.locations = locations;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<LocationResponseForm> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationResponseForm> locations) {
        this.locations = locations;
    }
}
