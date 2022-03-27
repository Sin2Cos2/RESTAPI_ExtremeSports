package com.attaproject.responseForm;

import com.attaproject.model.Region;

import java.util.List;

public class RegionResponseForm extends Region {

    private List<LocationResponseForm> locations;

    public RegionResponseForm(Region region, List<LocationResponseForm> locations) {
        super(region.getId(), region.getName(), region.getCountryId());
        this.locations = locations;
    }

    public List<LocationResponseForm> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationResponseForm> locations) {
        this.locations = locations;
    }
}
