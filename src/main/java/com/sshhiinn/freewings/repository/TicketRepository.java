package com.sshhiinn.freewings.repository;

import com.sshhiinn.freewings.model.Ticket;
import org.springframework.data.repository.CrudRepository;

public interface TicketRepository extends CrudRepository<Ticket, Long> {
}
