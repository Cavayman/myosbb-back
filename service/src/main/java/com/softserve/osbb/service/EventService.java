package com.softserve.osbb.service;

import com.softserve.osbb.model.Event;

import java.util.List;

/**
 * Created by nataliia on 10.07.16.
 */
public interface EventService {

    void saveEvent(Event event);

    void saveEventList(List<Event> list);

    Event findOneEventByID(Integer id);

    List<Event> findAllEventsByIDs(List<Integer> ids);

    List<Event> findAllEvents();

    void deleteEvent(Event event);

    void deleteEventByID(Integer id);

    void deleteListEvents(List<Event> list);

    void deleteAllEvents();

    long countEvents();

    boolean exitsEvent(Integer id);

}
