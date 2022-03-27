package com.attaproject.controller;

import com.attaproject.dao.RegionDAO;
import com.attaproject.model.Region;
import com.attaproject.responseForm.RegionResponseForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/regions")
public class RegionController {

    @Autowired
    private RegionDAO regionDAO;

    public List<RegionResponseForm> getRegionsByCountryId(Integer id){
        List<Region> regions = regionDAO.getRegionsByCountryId(id);
        List<RegionResponseForm> regionResponseForms = new ArrayList<>();

        for(Region region : regions){
            regionResponseForms.add(new RegionResponseForm(region, null, null));
        }

        return regionResponseForms;
    }
}
