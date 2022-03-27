package com.attaproject.controller;

import com.attaproject.dao.LocationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/locations")
public class LocationController {

    @Autowired
    private LocationDAO locationDAO;
}
