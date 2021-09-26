package com.sshhiinn.freewings.repository;

import com.sshhiinn.freewings.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
