package com.attaproject.controller;

import com.attaproject.dao.CountryDAO;
import com.attaproject.dao.RegionDAO;
import com.attaproject.model.Country;
import com.attaproject.model.Location;
import com.attaproject.model.Region;
import com.attaproject.requestForm.RegionRequest;
import com.attaproject.responseForm.LocationResponseForm;
import com.attaproject.responseForm.RegionResponseForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/regions")
public class RegionController {

    @Autowired
    private RegionDAO regionDAO;
    @Autowired
    private LocationController locationController;
    @Autowired
    private CountryDAO countryController;

    //GET requests
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
        return new RegionResponseForm(region, locations);
    }

    //POST requests
    @PostMapping
    public ResponseEntity<String> addRegion(@RequestBody RegionRequest region){
        Country country = countryController.getCountry(region.getCountryName());

        return regionDAO.addRegion(region, country) ?
                new ResponseEntity<>("Region have been added successfully", HttpStatus.OK) :
                new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
    }

    //DELETE requests
    @DeleteMapping(value = "/{name}")
    public ResponseEntity<String> deleteRegion(@PathVariable("name") String name){
        Region region = regionDAO.getRegion(name);
        List<LocationResponseForm> locations = locationController.getLocations(region.getId());

        for (Location location : locations) {
            locationController.deleteLocation(location.getName());
        }

        return regionDAO.deleteRegion(region) ?
                new ResponseEntity<>("Region have been successfully removed", HttpStatus.OK) :
                new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
    }

    public List<RegionResponseForm> getRegions(Integer countryId){
        List<Region> regions = regionDAO.getRegionsByCountryId(countryId);
        List<RegionResponseForm> regionResponseForms = new ArrayList<>();

        for(Region region : regions){
            List<LocationResponseForm> locations = locationController.getLocations(region.getId());
            regionResponseForms.add(new RegionResponseForm(region,  locations));
        }

        return regionResponseForms;
    }

}
