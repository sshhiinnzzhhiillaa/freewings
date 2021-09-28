package com.sshhiinn.freewings.controller;

import com.sshhiinn.freewings.model.Flight;
import com.sshhiinn.freewings.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController

public class FlightController {

    private FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/flights")
    public Collection<Flight> getAll(){
        return flightService.getAll();
    }
}
