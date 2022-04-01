package com.attaproject.responseForm;

import com.attaproject.model.Location;

import java.util.ArrayList;
import java.util.List;

public class LocationResponseForm extends Location {

    private List<LocationSportResponseList> sports;

    public LocationResponseForm(Location location, List<LocationSportResponseList> sports) {
        super(location.getId(), location.getName(), location.getRegionId(), location.getCountryId());
        this.sports = sports;
    }

    public LocationResponseForm(int id, String name, int regionId, int countryId) {
        super(id, name, regionId, countryId);
        sports = new ArrayList<>();
    }

    public List<LocationSportResponseList> getSports() {
        return sports;
    }

    public void setSports(List<LocationSportResponseList> sports) {
        this.sports = sports;
    }
}
