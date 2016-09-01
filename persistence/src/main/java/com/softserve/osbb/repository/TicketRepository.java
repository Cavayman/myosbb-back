package com.softserve.osbb.repository;

import com.softserve.osbb.model.Ticket;
import com.softserve.osbb.model.User;
import com.softserve.osbb.model.enums.TicketState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    List<Ticket> findByOrderByTimeDesc();

    Page<Ticket> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description, Pageable pageRequest);

    Page<Ticket> findByState(TicketState ticketState,Pageable pageRequest);

    Page<Ticket> findTicketsByUser(User user, Pageable pageRequest);
}
