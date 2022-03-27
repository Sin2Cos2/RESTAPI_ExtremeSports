package com.attaproject.controller;

import com.attaproject.dao.CountryDAO;
import com.attaproject.dao.RegionDAO;
import com.attaproject.model.Country;
import com.attaproject.model.Region;
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
    private CountryDAO countryDAO;

    public List<RegionResponseForm> getRegionsByCountryId(Integer id){
        List<Region> regions = regionDAO.getRegionsByCountryId(id);
        List<RegionResponseForm> regionResponseForms = new ArrayList<>();

        for(Region region : regions){
            //TODO: Нужно ли тут добавлять страну? Добавить города
            regionResponseForms.add(new RegionResponseForm(region,  null));
        }

        return regionResponseForms;
    }

    @GetMapping
    public List<RegionResponseForm> getRegions(){

        List<Region> regions = regionDAO.getRegions();
        List<RegionResponseForm> regionResponseForms = new ArrayList<>();

        for(Region region : regions) {
            //TODO: добавить города
            regionResponseForms.add((new RegionResponseForm(region, null)));
        }

        return regionResponseForms;
    }

    @GetMapping(value = "/{name}")
    public RegionResponseForm getRegion(@PathVariable("name") String name){

        Region region = regionDAO.getRegion(name);
        //TODO: добавить города

        return new RegionResponseForm(region, null);
    }

}
