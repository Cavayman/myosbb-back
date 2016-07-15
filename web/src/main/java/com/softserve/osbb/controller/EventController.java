package com.softserve.osbb.controller;

import com.softserve.osbb.model.Event;
import com.softserve.osbb.service.EventService;
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
 * Created by nataliia on 10.07.16.
 */

@RestController
@RequestMapping("/restful/event")
public class EventController {

    private static Logger logger = LoggerFactory.getLogger(EventController.class);

    @Autowired
    private EventService eventService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Resource<Event>> createEvent(@RequestBody Event event) {
        logger.info("Saving event " + event);
        event = eventService.saveEvent(event);
        Resource<Event> eventResource = new Resource<>(event);
        eventResource.add(linkTo(methodOn(EventController.class).findEventById(event.getEventId())).withSelfRel());
        return new ResponseEntity<>(eventResource, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Resource<Event>>> findAllEvents() {
        List<Event> eventList = eventService.findAllEvents();
        logger.info("Getting all events.");
        final List<Resource<Event>> resourceEventList = new ArrayList<>();
        for (Event e : eventList) {
            Resource<Event> eventResource = new Resource<>(e);
            eventResource.add(linkTo(methodOn(EventController.class)
                    .findEventById(e.getEventId()))
                    .withSelfRel());
            resourceEventList.add(eventResource);
        }
        return new ResponseEntity<>(resourceEventList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Resource<Event>> findEventById(@PathVariable("id") Integer eventId) {
        logger.info("Getting event by id: " + eventId);
        Event event = eventService.findEventById(eventId);
        Resource<Event> eventResource = new Resource<>(event);
        eventResource.add(linkTo(methodOn(EventController.class).findEventById(eventId)).withSelfRel());
        return new ResponseEntity<>(eventResource, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Resource<Event>> updateEvent(@PathVariable("id") Integer eventId,
                                                       @RequestBody Event event) {
        logger.info("Updating event by id: " + eventId);
        event = eventService.updateEvent(eventId, event);
        Resource<Event> eventResource = new Resource<>(event);
        eventResource.add(linkTo(methodOn(EventController.class).findEventById(eventId)).withSelfRel());
        return new ResponseEntity<>(eventResource, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Event> deleteEventById(@PathVariable("id") Integer eventId) {
        logger.info("Removing event by id: " + eventId);
        eventService.deleteEventById(eventId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public ResponseEntity<Event> deleteAllEvents() {
        logger.info("Removing all events.");
        eventService.deleteAllEvents();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

