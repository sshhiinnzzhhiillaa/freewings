package com.sshhiinn.freewings.service;

import com.sshhiinn.freewings.dto.ReservationRequest;
import com.sshhiinn.freewings.exception.FlightNotFound;
import com.sshhiinn.freewings.model.Flight;
import com.sshhiinn.freewings.model.Passenger;
import com.sshhiinn.freewings.model.Reservation;
import com.sshhiinn.freewings.repository.FlightRepository;
import com.sshhiinn.freewings.repository.PassengerRepository;
import com.sshhiinn.freewings.repository.ReservationRepository;
import com.sshhiinn.freewings.util.EmailUtil;
import com.sshhiinn.freewings.util.PdfGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {

    @Value("${com.flightreservation.flightreservation.itinerary.dirpath}")
    private String ITINERARY_DIR;

    private final FlightRepository flightRepository;

    private final PassengerRepository passengerRepository;

    private final ReservationRepository reservationRepository;

    private final PdfGenerator pdfGenerator;

    private final EmailUtil emailUtil;

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);

    public ReservationServiceImpl(FlightRepository flightRepository, PassengerRepository passengerRepository, ReservationRepository reservationRepository, PdfGenerator pdfGenerator, EmailUtil emailUtil) {
        this.flightRepository = flightRepository;
        this.passengerRepository = passengerRepository;
        this.reservationRepository = reservationRepository;
        this.pdfGenerator = pdfGenerator;
        this.emailUtil = emailUtil;
    }

    @Override
    public Reservation bookFlight(ReservationRequest reservationRequest) {
        LOGGER.info("Inside bookFlight()");
        Long flightId = reservationRequest.getFlightId();
        Optional<Flight> flightOptional = flightRepository.findById(flightId);
        if (!flightOptional.isPresent()) {
            throw new FlightNotFound("No flight found with id " + flightId);
        }
        LOGGER.info("Flight found with id: {}", flightId);
        Flight flight = flightOptional.get();
        Passenger passenger = new Passenger();
        passenger.setFirstName(reservationRequest.getPassengerFirstName());
        passenger.setMiddleName(reservationRequest.getPassengerMiddleName());
        passenger.setLastName(reservationRequest.getPassengerLastName());
        passenger.setEmail(reservationRequest.getPassengerEmail());
        passenger.setPhone(reservationRequest.getPassengerPhone());

        passengerRepository.save(passenger);
        LOGGER.info("Saved the passenger:" + passenger);

        Reservation reservation = new Reservation();
        reservation.setFlight(flight);
        reservation.setPassenger(passenger);
        reservation.setCheckedIn(false);
        final Reservation savedReservation = reservationRepository.save(reservation);
        LOGGER.info("Saving the reservation:" + reservation);


        String filePath = ITINERARY_DIR + savedReservation.getId()
                + ".pdf";
        LOGGER.info("Generating  the itinerary");
        pdfGenerator.generateItenary(savedReservation, filePath);
        LOGGER.info("Emailing the Itinerary");
        emailUtil.sendItenary("dlulla@akamai.com", filePath);

        return savedReservation;

    }
}
