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

    @RequestMapping(value = "/event", method = RequestMethod.GET)
    public List<Event> findAllEvents() {
        return eventService.findAllEvents();
    }
}

