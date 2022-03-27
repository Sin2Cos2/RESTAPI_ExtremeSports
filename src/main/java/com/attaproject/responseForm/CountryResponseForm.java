package com.attaproject.responseForm;

import com.attaproject.model.Country;

import java.util.List;

public class CountryResponseForm extends Country{

    private List<RegionResponseForm> regions;

    public CountryResponseForm(Country country, List<RegionResponseForm> regions) {
        super(country.getId(), country.getName());
        this.regions = regions;
    }

    public List<RegionResponseForm> getRegions() {
        return regions;
    }

    public void setRegions(List<RegionResponseForm> regions) {
        this.regions = regions;
    }
}
