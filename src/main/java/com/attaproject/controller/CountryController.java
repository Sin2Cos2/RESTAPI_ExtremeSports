package com.attaproject.controller;

import com.attaproject.dao.CountryDAO;
import com.attaproject.dao.RegionDAO;
import com.attaproject.mapper.CountryMapper;
import com.attaproject.model.Country;
import com.attaproject.responseForm.CountryResponseForm;
import com.attaproject.responseForm.RegionResponseForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "countries")
public class CountryController {

    @Autowired
    private CountryDAO countryDAO;
    @Autowired
    private RegionController regionController;

    @GetMapping
    public List<CountryResponseForm> getCountries(){

        List<Country> countries = countryDAO.getCountries();
        List<CountryResponseForm> countryResponseForms = new ArrayList<>();

        for(Country country : countries){
            List<RegionResponseForm> regions = regionController.getRegionsByCountryId(country.getId());
            countryResponseForms.add(new CountryResponseForm(country, regions));
        }

        return countryResponseForms;
    }

    @GetMapping(value = "/{id}")
    public Country getCountry(@PathVariable("id") int id){

        return countryDAO.getCountry(id);
    }
}
