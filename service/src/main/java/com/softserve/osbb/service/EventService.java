package com.softserve.osbb.service;

import com.softserve.osbb.model.Event;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by nataliia on 10.07.16.
 */
public interface EventService {

    Event addEvent(Event event);

    List<Event> addEvents(List<Event> list);

    Event getEventById(Integer id);

    List<Event> getListEvents(List<Integer> ids);

    Event getOneEventBySearchTerm(String searchTerm);

    List<Event> getAllEventsBySearchTerm(String searchTerm);

    List<Event> getAllEventsBetweenDates(LocalDateTime from, LocalDateTime to);

    List<Event> getAllEvents();

    Event updateEvent(Integer id, Event event) throws Exception;

    void deleteEvent(Event event);

    void deleteEventById(Integer id);

    void deleteListEvents(List<Event> list);

    void deleteAllEvents();

    long countEvents();

    boolean existsEvent(Integer id);

}
