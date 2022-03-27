package com.attaproject.controller;

import com.attaproject.dao.LocationDAO;
import com.attaproject.model.Location;
import com.attaproject.responseForm.LocationResponseForm;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @GetMapping
    public List<LocationResponseForm> getLocations(){

        List<Location> locations = locationDAO.getLocations();
        List<LocationResponseForm> responseForms = new ArrayList<>();
        for(Location location : locations){
            //TODO: добавить спорт
            responseForms.add(new LocationResponseForm(location, null));
        }

        return responseForms;
    }

    @GetMapping(value = "/{name}")
    public LocationResponseForm getLocation(@PathVariable("name") String name){

        Location location = locationDAO.getLocation(name);
        //TODO: добавить спорт
        return new LocationResponseForm(location, null);
    }

    public List<LocationResponseForm> getLocations(int region_id) {

        List<Location> locations = locationDAO.getLocations(region_id);
        List<LocationResponseForm> responseForms = new ArrayList<>();
        for (Location location : locations) {
            //TODO: добавить спорт
            responseForms.add(new LocationResponseForm(location, null));
        }

        return responseForms;
    }
}
