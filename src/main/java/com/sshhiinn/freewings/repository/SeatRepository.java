package com.sshhiinn.freewings.repository;

import com.sshhiinn.freewings.model.Seat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends CrudRepository<Seat, Long> {

    List<Seat> findAllByFlightId(long flightId);

}
