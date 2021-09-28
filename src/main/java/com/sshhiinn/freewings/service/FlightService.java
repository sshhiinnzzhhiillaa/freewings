package com.sshhiinn.freewings.service;

import com.sshhiinn.freewings.model.Flight;
import com.sshhiinn.freewings.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class FlightService {

    private FlightRepository flightRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public Collection<Flight> getAll(){
        return (Collection<Flight>) flightRepository.findAll();
    }

}
