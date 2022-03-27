package com.attaproject.responseForm;

import com.attaproject.model.Country;
import com.attaproject.model.Location;
import com.attaproject.model.Region;

import java.util.List;

public class LocationResponseForm {

    private Location location;
    private Country country;
    private Region region;
    private List<SportResponseForm> sports;

    public LocationResponseForm(Location location, Country country, Region region, List<SportResponseForm> sports) {
        this.location = location;
        this.country = country;
        this.region = region;
        this.sports = sports;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public List<SportResponseForm> getSports() {
        return sports;
    }

    public void setSports(List<SportResponseForm> sports) {
        this.sports = sports;
    }
}
