package com.softserve.osbb.service;

import com.softserve.osbb.model.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * Created by Kris on 12.07.2016.
 */
public interface TicketService {

    Ticket save(Ticket ticket);

    Ticket saveAndFlush(Ticket ticket);

    Ticket findOne(Integer id);

    Ticket findOne(String id);

    boolean exists(Integer id);

    List<Ticket> findAll();

    List<Ticket> findAll(Sort sort);

    Page<Ticket> findAll(Pageable pageable);

    List<Ticket> findAll(Iterable<Integer> iterable);

    long count();

    boolean delete(Integer id);

    boolean delete(Ticket ticket);

    boolean delete(Iterable<? extends Ticket> iterable);

    boolean deleteAll();

    void flush();

    Ticket getOne(Integer id);

    List<Ticket> save(Iterable<Ticket> iterable);

    Ticket update(Ticket ticket);

}
