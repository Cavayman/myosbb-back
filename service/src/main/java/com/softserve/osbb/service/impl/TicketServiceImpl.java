package com.softserve.osbb.service.impl;

import com.softserve.osbb.model.Ticket;
import com.softserve.osbb.repository.TicketRepository;
import com.softserve.osbb.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Kris on 11.07.2016.
 */
@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Override
    public Ticket save(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket saveAndFlush(Ticket ticket) {
        return ticketRepository.saveAndFlush(ticket);
    }

    @Override
    public Ticket findOne(Integer id) {
        return ticketRepository.findOne(id);
    }

    @Override
    public Ticket findOne(String id) {
        try {
            return ticketRepository.findOne(Integer.parseInt(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean exists(Integer id) {
        return ticketRepository.exists(id);
    }

    @Override
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    @Override
    public List<Ticket> findAll(Sort sort) {
        return ticketRepository.findAll(sort);
    }

    @Override
    public Page<Ticket> findAll(Pageable pageable) {
        return ticketRepository.findAll(pageable);
    }

    @Override
    public List<Ticket> findAll(Iterable<Integer> iterable) {
        return ticketRepository.findAll(iterable);
    }

    @Override
    public long count() {
        return ticketRepository.count();
    }

    @Override
    public boolean delete(Integer id) {
        ticketRepository.delete(id);
        return true;
    }

    @Override
    public boolean delete(Ticket ticket) {
        ticketRepository.delete(ticket);
        return true;
    }

    @Override
    public boolean delete(Iterable<? extends Ticket> iterable) {
        ticketRepository.delete(iterable);
        return true;
    }

    @Override
    public boolean deleteAll() {
        ticketRepository.deleteAll();
        return true;
    }


    @Override
    public void flush() {
        ticketRepository.flush();
    }

    @Override
    public Ticket getOne(Integer id) {
        return ticketRepository.getOne(id);
    }

    @Override
    public List<Ticket> save(Iterable<Ticket> iterable) {
        return ticketRepository.save(iterable);
    }

    @Override
    public Ticket update(Ticket ticket) {
        return ticketRepository.exists(ticket.getTicketId()) ? ticketRepository.save(ticket) : null;
    }
}
