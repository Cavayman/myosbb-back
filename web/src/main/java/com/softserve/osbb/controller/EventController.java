package com.softserve.osbb.controller;

import com.softserve.osbb.model.Event;
import com.softserve.osbb.model.Osbb;
import com.softserve.osbb.service.EventService;
import com.softserve.osbb.util.PageCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.softserve.osbb.util.ResourceUtil.toResource;
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

    @RequestMapping(value="/", method = RequestMethod.POST)
    public ResponseEntity<Resource<Event>> createEvent(@RequestBody Event event) {
        logger.info("Saving event " + event);
        event = eventService.saveEvent(event);
        Resource<Event> eventResource = new Resource<>(event);
        eventResource.add(linkTo(methodOn(EventController.class).findEventById(event.getEventId())).withSelfRel());
        return new ResponseEntity<>(eventResource, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Resource<Event>>> findAllEvents() {
        List<Event> eventList = eventService.getAllEvents();
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
        Event event = eventService.getEventById(eventId);
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

    @RequestMapping(value = "/",  method = RequestMethod.DELETE)
    public ResponseEntity<Event> deleteAllEvents() {
        logger.info("Removing all events.");
        eventService.deleteAllEvents();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Resource<Event> getLink(Resource<Event> eventResource) {
        //adding self-link
        eventResource.add(linkTo(methodOn(EventController.class)
                .findEventById(eventResource.getContent().getEventId()))
                .withSelfRel());
        //adding link to osbb
        final Osbb osbbFromResource = eventResource.getContent().getOsbb();
        if (osbbFromResource != null) {
            eventResource.add(linkTo(methodOn(OsbbController.class)
                    .getOsbbById(osbbFromResource
                            .getOsbbId())).withRel("osbb"));
        }
        return eventResource;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<PageCreator<Resource<Event>>> listAllEvents(
            @RequestParam(value = "pageNumber", required = true) Integer pageNumber,
            @RequestParam(value = "sortedBy", required = false) String sortedBy,
            @RequestParam(value = "asc", required = false) Boolean ascOrder) {
        logger.info("get all event by page number: " + pageNumber);
        Page<Event> eventsByPage = eventService.getAllEvents(pageNumber, sortedBy, ascOrder);

        int currentPage = eventsByPage.getNumber() + 1;
        int begin = Math.max(1, currentPage - 5);
        int totalPages = eventsByPage.getTotalPages();
        int end = Math.min(currentPage + 5, totalPages);

        List<Resource<Event>> resourceList = new ArrayList<>();
        eventsByPage.forEach((event) -> resourceList.add(getLink(toResource(event))));

        PageCreator<Resource<Event>> pageCreator = new PageCreator<>();
        pageCreator.setRows(resourceList);
        pageCreator.setCurrentPage(Integer.valueOf(currentPage).toString());
        pageCreator.setBeginPage(Integer.valueOf(begin).toString());
        pageCreator.setEndPage(Integer.valueOf(end).toString());
        pageCreator.setTotalPages(Integer.valueOf(totalPages).toString());

        return new ResponseEntity<>(pageCreator, HttpStatus.OK);
    }
}

