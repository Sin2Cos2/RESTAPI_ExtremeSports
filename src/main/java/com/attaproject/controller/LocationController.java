package com.attaproject.controller;

import com.attaproject.dao.LocationDAO;
import com.attaproject.model.Location;
import com.attaproject.model.LocationSport;
import com.attaproject.model.Sport;
import com.attaproject.responseForm.LocationResponseForm;
import com.attaproject.responseForm.LocationSportResponseForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/locations")
public class LocationController {

    @Autowired
    private LocationDAO locationDAO;
    @Autowired
    private SportController sportController;

    @GetMapping
    public List<LocationResponseForm> getLocations(){

        List<Location> locations = locationDAO.getLocations();
        List<LocationResponseForm> responseForms = new ArrayList<>();
        for(Location location : locations){
            List<LocationSportResponseForm> response = getLocationSportResponseForms(location);
            responseForms.add(new LocationResponseForm(location, response));
        }

        return responseForms;
    }

    private List<LocationSportResponseForm> getLocationSportResponseForms(Location location) {
        List<LocationSport> locationSports = locationDAO.getLocationsSports(location.getId());
        List<LocationSportResponseForm> response = new ArrayList<>();
        for (LocationSport locationSport : locationSports) {
            Sport sport = sportController.getSport(locationSport.getSportId());
            response.add(new LocationSportResponseForm(sport, locationSport.getPrice(),
                    locationSport.getStartDate(), locationSport.getEndDate()));
        }
        return response;
    }

    @GetMapping(value = "/{name}")
    public LocationResponseForm getLocation(@PathVariable("name") String name){

        Location location = locationDAO.getLocation(name);
        List<LocationSportResponseForm> response = getLocationSportResponseForms(location);
        return new LocationResponseForm(location, response);
    }

    public List<LocationResponseForm> getLocations(int region_id) {

        List<Location> locations = locationDAO.getLocations(region_id);
        List<LocationResponseForm> responseForms = new ArrayList<>();
        for (Location location : locations) {
            List<LocationSportResponseForm> response = getLocationSportResponseForms(location);
            responseForms.add(new LocationResponseForm(location, response));
        }

        return responseForms;
    }

    public Location getLocation(int locationId) {

        return locationDAO.getLocation(locationId);
    }
}
