package com.sshhiinn.freewings.controller;

import com.sshhiinn.freewings.model.Flight;
import com.sshhiinn.freewings.service.FlightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FlightController {

    private FlightService flightService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @PostMapping(value = {"/flight/create"})
    public String createFlight(Model model, @ModelAttribute("flight") Flight flight) {
        try {
            Flight newFlight = flightService.create(flight);
            return "redirect:/flight/" + newFlight.getId();
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("add", true);
            return "flight-edit";
        }
    }

    @GetMapping(value = {"/flight/{flightId}"})
    public String getFlight(Model model, @PathVariable long flightId) {
        Flight flight = null;
        try {
            flight = flightService.getById(flightId);
        } catch (ResourceNotFoundException ex) {
            model.addAttribute("errorMessage", "Flight not found");
        }
        model.addAttribute("flight", flight);
        return "flight";
    }

    @GetMapping("/flights")
    public String getAllFlights(Model model) {
        List<Flight> list = flightService.getAll();
        model.addAttribute("flights", list);
        return "flight-list";
    }

    @PostMapping(value = {"/flight/{flightId}/edit"})
    public String updateContact(Model model, @PathVariable long flightId, @ModelAttribute("contact") Flight flight) {
        try {
            flight.setId(flightId);
            flightService.update(flight);
            return "redirect:/flight/" + flight.getId();
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("add", false);
            return "flight-edit";
        }
    }

    @PostMapping(value = {"/flight/{flightId}/delete"})
    public String deleteContactById(Model model, @PathVariable long flightId) {
        try {
            flightService.deleteById(flightId);
            return "redirect:/flight";
        } catch (ResourceNotFoundException ex) {
            String errorMessage = ex.getMessage();
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            return "flight";
        }
    }

}
