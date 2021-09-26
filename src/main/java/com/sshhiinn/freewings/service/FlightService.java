package com.sshhiinn.freewings.service;

import com.sshhiinn.freewings.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightService {

    private FlightRepository repository;

    @Autowired
    public FlightService(FlightRepository repository) {
        this.repository = repository;
    }

    public FlightRepository getRepository() {
        return repository;
    }

    public void setRepository(FlightRepository repository) {
        this.repository = repository;
    }
}
