package com.softserve.osbb.controller;


import com.softserve.osbb.model.Ticket;
import com.softserve.osbb.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Kris on 13.07.2016.
 */

@RestController
public class TicketController {

    private static final Resource<Ticket> EMPTY_TICKET_LINK = new Resource<>(new Ticket());
    private static Logger logger = LoggerFactory.getLogger(TicketController.class);

    @Autowired
    TicketService ticketService;

    @RequestMapping(value = "/ticket", method = RequestMethod.POST)
    public ResponseEntity<Resource<Ticket>> createMessage(@RequestBody Ticket ticket) {

        Resource<Ticket> ticketResource;
        try {
            logger.info("saving ticket object " + ticket);
            ticket = ticketService.save(ticket);
            ticketResource = addResourceLinkToTicket(ticket);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(ticketResource, HttpStatus.OK);
    }

    @RequestMapping(value = "/ticket", method = RequestMethod.GET)
    public ResponseEntity<List<Resource<Ticket>>> listAllTickets() {

        List<Ticket> ticketList = ticketService.findAll();
        logger.info("Getting all messages: " + ticketList);
        if (ticketList.isEmpty()) {
            logger.warn("no ticketList were found in the list: " + ticketList);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        final List<Resource<Ticket>> resourceTicketList = new ArrayList<>();
        ticketList.stream().forEach((ticket) -> resourceTicketList.add(addResourceLinkToTicket(ticket)));
        return new ResponseEntity<>(resourceTicketList, HttpStatus.OK);
    }

    private Resource<Ticket> addResourceLinkToTicket(Ticket ticket) {
        if (ticket == null) {
            return EMPTY_TICKET_LINK;
        }
        Resource<Ticket> ticketResource = new Resource<>(ticket);

        ticketResource.add(linkTo(methodOn(TicketController.class)
                .getTicketById(ticket.getTicketId()))
                .withSelfRel());
        return ticketResource;
    }

    @RequestMapping(value = "/ticket/{id}", method = RequestMethod.GET)
    public ResponseEntity<Resource<Ticket>> getTicketById(@PathVariable("id") Integer ticketId) {

        logger.info("fetching ticket by id: " + ticketId);

        Ticket ticket = ticketService.findOne(ticketId);

        Resource<Ticket> ticketResource = addResourceLinkToTicket(ticket);

        return ticketResource == EMPTY_TICKET_LINK ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(ticketResource, HttpStatus.OK);
    }

    @RequestMapping(value = "/ticket/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Resource<Ticket>> updateTicket(@RequestBody Ticket ticket) {

        Resource<Ticket> ticketResource;

        try {
            ticketService.update(ticket);
            ticketResource = addResourceLinkToTicket(ticket);

        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(ticketResource, HttpStatus.OK);
    }


    @RequestMapping(value = "/ticket/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Ticket> deleteTicketById(@PathVariable("id") Integer ticketId) {
        logger.info("removing ticket by id: " + ticketId);
        ticketService.delete(ticketId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/ticket", method = RequestMethod.DELETE)
    public ResponseEntity<Ticket> deleteAll() {
        logger.info("removing all messages");
        ticketService.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
