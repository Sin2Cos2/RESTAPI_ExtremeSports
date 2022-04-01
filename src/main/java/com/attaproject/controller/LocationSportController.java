package com.attaproject.controller;

import com.attaproject.dao.LocationSportDAO;
import com.attaproject.model.LocationSport;
import com.attaproject.model.Sport;
import com.attaproject.requestForm.SearchForm;
import com.attaproject.requestForm.SportRequest;
import com.attaproject.responseForm.LocationSportResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping(value = "/location/sport")
public class LocationSportController {

    @Autowired
    @Lazy
    private LocationSportDAO locationSportDAO;

    @PostMapping
    public List<LocationSportResponse> getLocationSport(@RequestBody SearchForm searchForm){
        List<LocationSportResponse> responses;
        if(searchForm.getSports().isEmpty())
            responses = locationSportDAO.getLocationSports(searchForm.getStartDate(), searchForm.getEndDate());
        else
            responses = locationSportDAO.getLocationSports(searchForm);

        responses.sort((o1, o2) -> {
            if (o1.getPrice() > o2.getPrice())
                return 1;
            if (o1.getPrice() == o2.getPrice())
                return 0;
            return -1;
        });

        return responses;
    }

    public List<LocationSport> getSportLocation(int sportId){
        return locationSportDAO.getSportLocation(sportId);
    }

    public void deleteSport(Sport sport) {
        this.locationSportDAO.deleteSport(sport);
    }

    public boolean addSport(SportRequest sport, Sport s) {
        return locationSportDAO.addSport(sport, s);
    }

    public boolean updateSport(SportRequest sport) {
        return this.locationSportDAO.updateSport(sport);
    }
}
