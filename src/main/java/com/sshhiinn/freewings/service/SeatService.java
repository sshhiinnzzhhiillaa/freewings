package com.sshhiinn.freewings.service;

import com.sshhiinn.freewings.model.Order;
import com.sshhiinn.freewings.model.Seat;
import com.sshhiinn.freewings.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.LinkedList;
import java.util.List;

@Service
public class SeatService {

    private SeatRepository seatRepository;

    @Autowired
    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }


    private boolean existsById(Long id) {
        return seatRepository.existsById(id);
    }

    public Seat create(Seat seat) {
        if (seat != null) {
            if (existsById(seat.getId())) {
                throw new KeyAlreadyExistsException("Seat with id: " + seat.getId() + " already exists");
            }
            return seatRepository.save(seat);
        } else {
            throw new IllegalArgumentException("Failed to save seat: null or empty");
        }
    }

    public Seat getById(long id) {
        Seat order = seatRepository.findById(id).orElse(null);
        if (order == null) {
            throw new ResourceNotFoundException("Cannot find seat with id: " + id);
        } else {
            return order;
        }
    }

    public List<Seat> getAll(long flightId) {
        return new LinkedList<>(seatRepository.findAllByFlightId(flightId));
    }

    public void update(Seat seat) {
        if (seat != null) {
            if (existsById(seat.getId())) {
                throw new ResourceNotFoundException("Cannot find seat with id: " + seat.getId());
            }
            seatRepository.save(seat);
        } else {
            throw new IllegalArgumentException("Failed to save seat");
        }
    }

    public void deleteById(Long id) throws ResourceNotFoundException {
        if (!existsById(id)) {
            throw new ResourceNotFoundException("Cannot find seat with id: " + id);
        } else {
            seatRepository.deleteById(id);
        }

    }
}
