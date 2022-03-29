package com.attaproject.controller;

import com.attaproject.dao.CountryDAO;
import com.attaproject.model.Country;
import com.attaproject.requestForm.CountryRequest;
import com.attaproject.responseForm.CountryResponseForm;
import com.attaproject.responseForm.RegionResponseForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/countries")
public class CountryController {

    //TODO: Добавить для GET: /countries/name/regions/name/locations/name/sports
    @Autowired
    private CountryDAO countryDAO;
    @Autowired
    private RegionController regionController;

    //GET requests
    @GetMapping
    public List<CountryResponseForm> getCountries(){

        List<Country> countries = countryDAO.getCountries();
        List<CountryResponseForm> countryResponseForms = new ArrayList<>();

        for(Country country : countries){
            List<RegionResponseForm> regions = regionController.getRegions(country.getId());
            countryResponseForms.add(new CountryResponseForm(country, regions));
        }

        return countryResponseForms;
    }

    @GetMapping(value = "/{country}")
    public CountryResponseForm getCountry(@PathVariable("country") String c){

        Country country = countryDAO.getCountry(c);
        return new CountryResponseForm(country, regionController.getRegions(country.getId()));
    }

    //POST requests
    @PostMapping
    public ResponseEntity<String> addCountry(@RequestBody CountryRequest country){
        return countryDAO.addCountry(country) ?
                new ResponseEntity<>("Country have been added successfully", HttpStatus.OK) :
                new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
    }

    //DELETE requests
    @DeleteMapping(value = "/{name}")
    public ResponseEntity<String> deleteCountry(@PathVariable("name") String name){
        Country country = countryDAO.getCountry(name);
        List<RegionResponseForm> regions = regionController.getRegions(country.getId());

        for (RegionResponseForm region : regions) {
            regionController.deleteRegion(region.getName());
        }

        return countryDAO.deleteCountry(country) ?
                new ResponseEntity<>("Country have been successfully removed", HttpStatus.OK) :
                new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
    }
}

