package com.sshhiinn.freewings.repository;

import com.sshhiinn.freewings.model.Flight;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends CrudRepository<Flight, Long> {
}
