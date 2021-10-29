package com.sshhiinn.freewings.controller;

import com.sshhiinn.freewings.model.Order;
import com.sshhiinn.freewings.model.Seat;
import com.sshhiinn.freewings.service.SeatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SeatController {

    private SeatService seatService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @PostMapping(value = {"/seat/create"})
    public String createSeat(Model model, @ModelAttribute("flight") Seat seat) {
        try {
            Seat newSeat = seatService.create(seat);
            return "redirect:/seat/" + newSeat.getId();
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("add", true);
            return "seat-edit";
        }
    }

    @GetMapping(value = {"/seat/{seatId}"})
    public String getSeat(Model model, @PathVariable long seatId) {
        Seat seat = null;
        try {
            seat = seatService.getById(seatId);
        } catch (ResourceNotFoundException ex) {
            model.addAttribute("errorMessage", "Order not found");
        }
        model.addAttribute("seat", seat);
        return "seat";
    }

    @GetMapping("/seats/{flightId}")
    public String getAllSeatsByFlightId(Model model, @PathVariable long flightId) {
        List<Seat> list = seatService.getAll(flightId);
        model.addAttribute("seats", list);
        return "seat-list";
    }

    @PostMapping(value = {"/seat/{seatId}/edit"})
    public String updateSeat(Model model, @PathVariable long seatId, @ModelAttribute("seat") Seat seat) {
        try {
            seat.setId(seatId);
            seatService.update(seat);
            return "redirect:/seat/" + seat.getId();
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("add", false);
            return "seat-edit";
        }
    }

    @PostMapping(value = {"/seat/{seatId}/delete"})
    public String deleteSeatById(Model model, @PathVariable long seatId) {
        try {
            seatService.deleteById(seatId);
            return "redirect:/seat";
        } catch (ResourceNotFoundException ex) {
            String errorMessage = ex.getMessage();
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            return "seat";
        }
    }
}
