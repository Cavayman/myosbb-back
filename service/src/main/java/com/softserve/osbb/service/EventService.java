package com.softserve.osbb.service;

import com.softserve.osbb.model.Event;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by nataliia on 10.07.16.
 */
public interface EventService {

    Event saveEvent(Event event);

    Event findEventById(Integer id);

    List<Event> findEvents(List<Event> list);

    List<Event> findEventsByIds(List<Integer> ids);

    List<Event> findAllEvents();

    Event updateEvent(Integer id, Event event);

    void deleteEvent(Event event);

    void deleteEventById(Integer id);

    void deleteListEvents(List<Event> list);

    void deleteAllEvents();

    long countEvents();

    boolean existsEvent(Integer id);

}
