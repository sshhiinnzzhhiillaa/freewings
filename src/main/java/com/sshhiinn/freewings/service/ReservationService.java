package com.sshhiinn.freewings.service;

import com.flightreservation.flightreservation.domains.Reservation;
import com.flightreservation.flightreservation.dto.ReservationRequest;


public interface ReservationService {
    public Reservation bookFlight(ReservationRequest reservationRequest);
}
