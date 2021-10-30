package com.sshhiinn.freewings.repository;

import com.sshhiinn.freewings.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
