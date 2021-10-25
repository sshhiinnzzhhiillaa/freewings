package com.sshhiinn.freewings.service;

import com.sshhiinn.freewings.model.Flight;
import com.sshhiinn.freewings.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Service
public class FlightService {

    private FlightRepository flightRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    private boolean existsById(Long id) {
        return flightRepository.existsById(id);
    }

    public Flight create(Flight flight) {
        if (flight != null) {
            if (existsById(flight.getId())) {
                throw new KeyAlreadyExistsException("Flight with id: " + flight.getId() + " already exists");
            }
            return flightRepository.save(flight);
        } else {
            throw new IllegalArgumentException("Failed to save flight: null or empty");
        }
    }

    public Flight getById(long id) {
        Flight flight = flightRepository.findById(id).orElse(null);
        if (flight == null) {
            throw new ResourceNotFoundException("Cannot find Contact with id: " + id);
        } else {
            return flight;
        }
    }

    public List<Flight> getAll() {
        List<Flight> list = new LinkedList<>();
        flightRepository.findAll().forEach(list::add);
        return list;
    }

}
