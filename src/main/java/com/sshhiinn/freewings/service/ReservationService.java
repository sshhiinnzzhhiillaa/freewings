package com.sshhiinn.freewings.service;

import com.sshhiinn.freewings.dto.ReservationRequest;
import com.sshhiinn.freewings.model.Reservation;


public interface ReservationService {
    public Reservation bookFlight(ReservationRequest reservationRequest);
}
