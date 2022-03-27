package com.attaproject.responseForm;

import com.attaproject.model.Location;

import java.util.ArrayList;
import java.util.List;

public class LocationResponseForm extends Location {

    private List<LocationSportResponseForm> sports;

    public LocationResponseForm(Location location, List<LocationSportResponseForm> sports) {
        super(location.getId(), location.getName(), location.getRegionId(), location.getCountryId());
        this.sports = sports;
    }

    public LocationResponseForm(int id, String name, int regionId, int countryId) {
        super(id, name, regionId, countryId);
        sports = new ArrayList<>();
    }

    public List<LocationSportResponseForm> getSports() {
        return sports;
    }

    public void setSports(List<LocationSportResponseForm> sports) {
        this.sports = sports;
    }
}
