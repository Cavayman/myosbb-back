package com.softserve.osbb.service;

import com.softserve.osbb.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by nataliia on 10.07.16.
 */

@Service
public interface EventService {

    Event saveEvent(Event event);

    List<Event> saveEvents(List<Event> list);

    Event getEventById(Integer id);

    List<Event> getEvents(List<Event> list);

    List<Event> getEventsByIds(List<Integer> ids);

    List<Event> getAllEvents();

    Event updateEvent(Integer id, Event event);

    void deleteEvent(Event event);

    void deleteEventById(Integer id);

    void deleteEvents(List<Event> list);

    void deleteAllEvents();

    long countEvents();

    boolean existsEvent(Integer id);

    Page<Event> getAllEvents(Integer pageNumber, String sortedBy, Boolean ascOrder);

    List<Event> findEventsByNameOrAuthorOrDescription(String search);

    List<Event> findByInterval(Timestamp start, Timestamp end);

}
