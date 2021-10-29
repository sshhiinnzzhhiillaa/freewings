package com.sshhiinn.freewings.controller;

import com.sshhiinn.freewings.model.Flight;
import com.sshhiinn.freewings.model.Order;
import com.sshhiinn.freewings.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    private OrderService orderService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @PostMapping(value = {"/order/create"})
    public String createOrder(Model model, @ModelAttribute("flight") Order order) {
        try {
            Order newOrder = orderService.create(order);
            return "redirect:/order/" + newOrder.getId();
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("add", true);
            return "order-edit";
        }
    }

    @GetMapping(value = {"/order/{orderId}"})
    public String getOrder(Model model, @PathVariable long orderId) {
        Order order = null;
        try {
            order = orderService.getById(orderId);
        } catch (ResourceNotFoundException ex) {
            model.addAttribute("errorMessage", "Order not found");
        }
        model.addAttribute("order", order);
        return "order";
    }

    @GetMapping("/orders")
    public String getAllOrders(Model model) {
        List<Order> list = orderService.getAll();
        model.addAttribute("orders", list);
        return "order-list";
    }

    @PostMapping(value = {"/order/{orderId}/edit"})
    public String updateOrder(Model model, @PathVariable long orderId, @ModelAttribute("order") Order order) {
        try {
            order.setId(orderId);
            orderService.update(order);
            return "redirect:/order/" + order.getId();
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("add", false);
            return "order-edit";
        }
    }

    @PostMapping(value = {"/order/{orderId}/delete"})
    public String deleteOrderById(Model model, @PathVariable long orderId) {
        try {
            orderService.deleteById(orderId);
            return "redirect:/order";
        } catch (ResourceNotFoundException ex) {
            String errorMessage = ex.getMessage();
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            return "order";
        }
    }
}
