package com.attaproject.controller;

import com.attaproject.dao.SportDAO;
import com.attaproject.model.Location;
import com.attaproject.model.LocationSport;
import com.attaproject.model.Sport;
import com.attaproject.responseForm.SportLocationResponseForm;
import com.attaproject.responseForm.SportResponseForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/sports")
public class SportController {

    @Autowired
    private SportDAO sportDAO;
    @Autowired
    private LocationController locationController;

    @GetMapping
    public List<SportResponseForm> getSports(){

        List<Sport> sports = sportDAO.getSports();
        List<SportResponseForm> responseForms = new ArrayList<>();
        for (Sport sport : sports) {
            List<SportLocationResponseForm> response = getSportLocationResponseForms(sport);
            responseForms.add(new SportResponseForm(sport, response));
        }

        return responseForms;
    }

    private List<SportLocationResponseForm> getSportLocationResponseForms(Sport sport) {
        List<LocationSport> responseFormList = sportDAO.getSportLocation(sport.getId());
        List<SportLocationResponseForm> response = new ArrayList<>();
        for (LocationSport locationSport : responseFormList) {
            Location location = locationController.getLocation(locationSport.getLocationId());
            response.add(new SportLocationResponseForm(location, locationSport.getPrice(),
                    locationSport.getStartDate(), locationSport.getEndDate()));
        } return response;
    }

    @GetMapping(value = "/{name}")
    public SportResponseForm getSport(@PathVariable("name") String name){

        Sport sport = sportDAO.getSport(name);
        List<SportLocationResponseForm> response = getSportLocationResponseForms(sport);
        return new SportResponseForm(sport, response);
    }

    public Sport getSport(int sportId) {

        return sportDAO.getSport(sportId);
    }
}
