package com.attaproject.controller;

import com.attaproject.dao.SportDAO;
import com.attaproject.model.Location;
import com.attaproject.model.LocationSport;
import com.attaproject.model.Sport;
import com.attaproject.requestForm.SportRequest;
import com.attaproject.responseForm.SportLocationResponseForm;
import com.attaproject.responseForm.SportResponseForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/sports")
public class SportController {

    @Autowired
    private SportDAO sportDAO;
    @Autowired
    private LocationController locationController;

    //GET requests
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

    @GetMapping(value = "/{name}")
    public SportResponseForm getSport(@PathVariable("name") String name){

        Sport sport = sportDAO.getSport(name);
        List<SportLocationResponseForm> response = getSportLocationResponseForms(sport);
        return new SportResponseForm(sport, response);
    }

    //POST requests
    @PostMapping
    public ResponseEntity<String> addSport(@RequestBody SportRequest sport){

        return sportDAO.addSport(sport) ?
                new ResponseEntity<>("Sport have been added successfully", HttpStatus.OK) :
                new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
    }

    //DELETE requests
    @DeleteMapping(value = "/{name}")
    public ResponseEntity<String> deleteSport(@PathVariable("name") String name){
        return sportDAO.deleteSport(name) ?
                new ResponseEntity<>("Sport have been successfully removed", HttpStatus.OK) :
                new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
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

    public Sport getSport(int sportId) {

        return sportDAO.getSport(sportId);
    }
}
