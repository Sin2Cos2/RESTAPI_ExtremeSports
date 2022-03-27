package com.attaproject.responseForm;

import com.attaproject.model.Location;
import com.attaproject.model.Region;

import java.util.List;

public class LocationResponseForm extends Location {

    private Region region;
    private List<SportResponseForm> sports;

    public LocationResponseForm(Location location, Region region, List<SportResponseForm> sports) {
        super(location.getId(), location.getName(), location.getStartDate(),
                location.getEndDate(), location.getRegionId(), location.getCountryId());
        this.region = region;
        this.sports = sports;
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
