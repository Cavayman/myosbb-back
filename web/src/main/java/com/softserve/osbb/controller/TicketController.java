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
@RequestMapping("/restful/ticket")
public class TicketController {

    private static Logger logger = LoggerFactory.getLogger(TicketController.class);

    @Autowired
    TicketService ticketService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Resource<Ticket>> createMessage(@RequestBody Ticket ticket) {

        Resource<Ticket> ticketResource;
        try {
            logger.info("saving ticket object " + ticket);
            ticket = ticketService.save(ticket);
            ticketResource = addResourceLinkToTicket(ticket);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(ticketResource, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Resource<Ticket>>> listAllTickets() {
        List<Ticket> eventList = ticketService.findAll();
        logger.info("Getting all events.");
        final List<Resource<Ticket>> resourceEventList = new ArrayList<>();
        for (Ticket e : eventList) {
            Resource<Ticket> eventResource = new Resource<>(e);
            eventResource.add(linkTo(methodOn(EventController.class)
                    .findEventById(e.getTicketId()))
                    .withSelfRel());
            resourceEventList.add(eventResource);
        }
        return new ResponseEntity<>(resourceEventList, HttpStatus.OK);
    }

    private Resource<Ticket> addResourceLinkToTicket(Ticket ticket) {

        Resource<Ticket> ticketResource = new Resource<>(ticket);

        ticketResource.add(linkTo(methodOn(TicketController.class)
                .getTicketById(ticket.getTicketId()))
                .withSelfRel());
        return ticketResource;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Resource<Ticket>> getTicketById(@PathVariable("id") Integer ticketId) {

        logger.info("Getting event by id: " + ticketId);
        Ticket event = ticketService.getOne(ticketId);
        Resource<Ticket> eventResource = new Resource<>(event);
        eventResource.add(linkTo(methodOn(EventController.class).findEventById(ticketId)).withSelfRel());
        return new ResponseEntity<>(eventResource, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Resource<Ticket>> updateTicket(@RequestBody Ticket ticket) {

        logger.info("Updating ticket by id: " + ticket.getTicketId());
        ticket = ticketService.update(ticket);
        Resource<Ticket> eventResource = new Resource<>(ticket);
        eventResource.add(linkTo(methodOn(TicketController.class).getTicketById(ticket.getTicketId())).withSelfRel());
        return new ResponseEntity<>(eventResource, HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Ticket> deleteTicketById(@PathVariable("id") Integer ticketId) {
        logger.info("removing ticket by id: " + ticketId);
        ticketService.delete(ticketId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping( method = RequestMethod.DELETE)
    public ResponseEntity<Ticket> deleteAll() {
        logger.info("removing all messages");
        ticketService.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
