package com.sshhiinn.freewings.repository;

import com.sshhiinn.freewings.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
}
