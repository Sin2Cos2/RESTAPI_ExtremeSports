package com.attaproject.responseForm;

import com.attaproject.model.Country;

import java.util.List;

public class CountryResponseForm {

    private Country country;
    private List<RegionResponseForm> regions;

    public CountryResponseForm(Country country, List<RegionResponseForm> regions) {
        this.country = country;
        this.regions = regions;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<RegionResponseForm> getRegions() {
        return regions;
    }

    public void setRegions(List<RegionResponseForm> regions) {
        this.regions = regions;
    }
}
