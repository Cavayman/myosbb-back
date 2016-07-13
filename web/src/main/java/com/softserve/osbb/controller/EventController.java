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
public class EventController {

    private static final Resource<Event> EMPTY_EVENT_LINK = new Resource<>(new Event());
    private static Logger logger = LoggerFactory.getLogger(EventController.class);

    @Autowired
    private EventService eventService;

    @RequestMapping(value = "/event", method = RequestMethod.GET)
    public ResponseEntity<List<Resource<Event>>> listAllevents() {

        List<Event> eventList = eventService.getAllEvents();

        logger.info("getting all events: " + eventList);

        if (eventList.isEmpty()) {
            logger.warn("no eventList were found in the list: " + eventList);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        final List<Resource<Event>> resourceeventList = new ArrayList<>();

        eventList.stream().forEach((event) -> resourceeventList.add(addResourceLinkToEvent(event)));

        return new ResponseEntity<>(resourceeventList, HttpStatus.OK);
    }

    @RequestMapping(value = "/event", method = RequestMethod.POST)
    public ResponseEntity<Resource<Event>> createEvent(@RequestBody Event event) {

        Resource<Event> eventResource;
        try {

            logger.info("saving event object " + event);
            event = eventService.addEvent(event);

            eventResource = addResourceLinkToEvent(event);

        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(eventResource, HttpStatus.OK);
    }

    @RequestMapping(value = "/event/{id}", method = RequestMethod.GET)
    public ResponseEntity<Resource<Event>> getEventById(@PathVariable("id") Integer eventId) {

        logger.info("fetching event by id: " + eventId);

        Event event = eventService.getEventById(eventId);

        Resource<Event> eventResource = addResourceLinkToEvent(event);

        return eventResource == EMPTY_EVENT_LINK ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(eventResource, HttpStatus.OK);
    }

    private Resource<Event> addResourceLinkToEvent(Event event) {

        if (event == null) {
            return EMPTY_EVENT_LINK;
        }

        Resource<Event> eventResource = new Resource<>(event);

        eventResource.add(linkTo(methodOn(EventController.class)
                .getEventById(event.getEventId()))
                .withSelfRel());
        return eventResource;
    }

    @RequestMapping(value = "/event/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Resource<Event>> updateEvent(@PathVariable("id") Integer eventId,
                                                         @RequestBody Event event) {

        Resource<Event> eventResource;

        try {

            logger.info("updating event by id: " + eventId);

            event = eventService.updateEvent(eventId, event);

            eventResource = addResourceLinkToEvent(event);

        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(eventResource, HttpStatus.OK);
    }

    @RequestMapping(value = "/event/find", method = RequestMethod.GET)
    public ResponseEntity<List<Resource<Event>>> getEventsByName(@RequestParam(value = "searchParam",
            required = true)
                                                                           String searchParam) {

        logger.info("fetching event by search parameter: " + searchParam);
        List<Event> eventsBySearchTerm = eventService.getAllEventsBySearchTerm(searchParam);

        if (eventsBySearchTerm.isEmpty()) {
            logger.warn("no events were found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Resource<Event>> resourceEventList = new ArrayList<>();

        eventsBySearchTerm.stream().forEach((event) -> resourceEventList.add(addResourceLinkToEvent(event)));

        return new ResponseEntity<>(resourceEventList, HttpStatus.OK);
    }

    @RequestMapping(value = "/event/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Event> deleteEventById(@PathVariable("id") Integer eventId) {
        logger.info("removing event by id: " + eventId);
        eventService.deleteEventById(eventId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/event", method = RequestMethod.DELETE)
    public ResponseEntity<Event> deleteAllEvents() {
        logger.info("removing all events");
        eventService.deleteAllEvents();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

