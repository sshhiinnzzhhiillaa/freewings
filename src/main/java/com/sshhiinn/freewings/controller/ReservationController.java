package com.sshhiinn.freewings.controller;

import com.sshhiinn.freewings.dto.ReservationRequest;
import com.sshhiinn.freewings.exception.FlightNotFound;
import com.sshhiinn.freewings.model.Flight;
import com.sshhiinn.freewings.model.Reservation;
import com.sshhiinn.freewings.repository.FlightRepository;
import com.sshhiinn.freewings.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Tag(name = "Reservation", description = "Reservations API")
@Controller
public class ReservationController {

    private final FlightRepository flightRepository;

    private final ReservationService reservationService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);

    public ReservationController(FlightRepository flightRepository, ReservationService reservationService) {
        this.flightRepository = flightRepository;
        this.reservationService = reservationService;
    }

    @Operation(summary = "Get complete reservation")
    @RequestMapping("/showCompleteReservation")
    public String showCompleteReservation(@RequestParam("flightId") Long flightId, ModelMap modelMap) {
        LOGGER.info("showCompleteReservation() invoked with the Flight Id: " + flightId);
        Optional<Flight> flight = flightRepository.findById(flightId);
        if (flight.isEmpty()) {
            LOGGER.error("Flight Not found: {}", flight);
            throw new FlightNotFound("flightId " + flightId);
        }
        LOGGER.info("Flight found: {}", flight);
        modelMap.addAttribute("flight", flight.get());
        return "reservation/completeReservation";
    }

    @Operation(summary = "Add complete reservation")
    @RequestMapping(value = "/completeReservation", method = RequestMethod.POST)
    public String completeReservation(ReservationRequest reservationRequest, ModelMap modelMap) {
        LOGGER.info("completeReservation() invoked with the Reservation: " + reservationRequest.toString());
        Reservation reservation = reservationService.bookFlight(reservationRequest);
        modelMap.addAttribute("msg", "Reservation created successfully and the reservation id is " + reservation.getId());
        return "reservation/reservationConfirmation";
    }

}
