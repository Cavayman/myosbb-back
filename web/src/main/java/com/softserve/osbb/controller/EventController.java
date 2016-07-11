package com.softserve.osbb.controller;

import com.softserve.osbb.model.Event;
import com.softserve.osbb.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Created by nataliia on 10.07.16.
 */

@RestController
public class EventController {

    @Autowired
    private EventService eventService;

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public List<Event> findAllEvents() {
        return eventService.findAllEvents();
    }

    @RequestMapping(value = "/event/{ids}", method = RequestMethod.GET)
    public List<Event> findEvent(List<Integer> ids) {
        return eventService.findAllEventsByIDs(ids);
    }

    @RequestMapping(value = "/event/{id}", method = RequestMethod.GET)
    public Event findOneEvent(Integer id) {
        return eventService.findOneEventByID(id);
    }
}

