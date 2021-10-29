package com.sshhiinn.freewings.service;

import com.sshhiinn.freewings.model.Order;
import com.sshhiinn.freewings.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.LinkedList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    private boolean existsById(Long id) {
        return orderRepository.existsById(id);
    }

    public Order create(Order order) {
        if (order != null) {
            if (existsById(order.getId())) {
                throw new KeyAlreadyExistsException("Order with id: " + order.getId() + " already exists");
            }
            return orderRepository.save(order);
        } else {
            throw new IllegalArgumentException("Failed to save order: null or empty");
        }
    }

    public Order getById(long id) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order == null) {
            throw new ResourceNotFoundException("Cannot find order with id: " + id);
        } else {
            return order;
        }
    }

    public List<Order> getAll() {
        List<Order> list = new LinkedList<>();
        orderRepository.findAll().forEach(list::add);
        return list;
    }

    public void update(Order order) {
        if (order != null) {
            if (existsById(order.getId())) {
                throw new ResourceNotFoundException("Cannot find order with id: " + order.getId());
            }
            orderRepository.save(order);
        } else {
            throw new IllegalArgumentException("Failed to save order");
        }
    }

    public void deleteById(Long id) throws ResourceNotFoundException {
        if (!existsById(id)) {
            throw new ResourceNotFoundException("Cannot find order with id: " + id);
        } else {
            orderRepository.deleteById(id);
        }

    }
}
