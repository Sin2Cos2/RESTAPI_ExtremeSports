package com.attaproject.controller;

import com.attaproject.dao.CountryDAO;
import com.attaproject.dao.LocationDAO;
import com.attaproject.dao.RegionDAO;
import com.attaproject.model.Country;
import com.attaproject.model.Location;
import com.attaproject.model.Region;
import com.attaproject.responseForm.LocationResponseForm;
import com.attaproject.responseForm.RegionResponseForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/regions")
public class RegionController {

    @Autowired
    private RegionDAO regionDAO;
    @Autowired
    private LocationController locationController;

    public List<RegionResponseForm> getRegions(Integer id){
        List<Region> regions = regionDAO.getRegionsByCountryId(id);
        List<RegionResponseForm> regionResponseForms = new ArrayList<>();

        for(Region region : regions){
            List<LocationResponseForm> locations = locationController.getLocations(region.getId());
            regionResponseForms.add(new RegionResponseForm(region,  locations));
        }

        return regionResponseForms;
    }

    @GetMapping
    public List<RegionResponseForm> getRegions(){

        List<Region> regions = regionDAO.getRegions();
        List<RegionResponseForm> regionResponseForms = new ArrayList<>();

        for(Region region : regions) {
            List<LocationResponseForm> locations = locationController.getLocations(region.getId());
            regionResponseForms.add((new RegionResponseForm(region, locations)));
        }

        return regionResponseForms;
    }

    @GetMapping(value = "/{name}")
    public RegionResponseForm getRegion(@PathVariable("name") String name){

        Region region = regionDAO.getRegion(name);
        List<LocationResponseForm> locations = locationController.getLocations(region.getId());
        return new RegionResponseForm(region, null);
    }

}
