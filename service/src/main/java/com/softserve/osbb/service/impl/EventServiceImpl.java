package com.softserve.osbb.service.impl;

import com.softserve.osbb.model.Event;
import com.softserve.osbb.repository.EventRepository;
import com.softserve.osbb.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by nataliia on 10.07.16.
 */
@Service
public class EventServiceImpl implements EventService {

    private static final List<Event> EMPTY_LIST = new CopyOnWriteArrayList<>();
    private static final Event EMPTY_EVENT = new Event();

    @Autowired
    EventRepository eventRepository;

    @Override
    public Event addEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public List<Event> addEvents(List<Event> list) {
        return eventRepository.save(list);
    }

    @Override
    public Event getEventById(Integer id) {
        return eventRepository.findOne(id);
    }

    @Override
    public List<Event> getListEvents(List<Integer> ids) {
        return eventRepository.findAll(ids);
    }

    @Override
    public Event getOneEventBySearchTerm(String searchTerm) {
        return (searchTerm != null || !searchTerm.isEmpty()) ?
                eventRepository.getAllEventsBySearchParam(searchTerm)
                        .stream()
                        .findFirst()
                        .get() : EMPTY_EVENT;
    }

    @Override
    public List<Event> getAllEventsBySearchTerm(String searchTerm) {
        return (searchTerm == null || searchTerm.isEmpty()) ? EMPTY_LIST :
                eventRepository.getAllEventsBySearchParam(searchTerm);
    }

    @Override
    public List<Event> getAllEventsBetweenDates(LocalDateTime from, LocalDateTime to) {
        return eventRepository.getAllEventsBetweenDates(from == null ? LocalDateTime.now() : from,
                to == null ? LocalDateTime.now() : to);
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Event updateEvent(Integer id, Event event) throws Exception {
        return event == null ? null : updateEventIfExists(id, event);
    }

    private Event updateEventIfExists(Integer id, Event event) throws Exception {

        boolean isExisted = eventRepository.exists(id);

        if (!isExisted) {
            throw new Exception("event under id: " + event.getEventId() + " doesn't exist and thus" +
                    " cannot be updated");

        }
        return eventRepository.save(event);

    }

    @Override
    public void deleteEvent(Event event) {
        eventRepository.delete(event);
    }

    @Override
    public void deleteEventById(Integer id) {
        eventRepository.delete(id);
    }

    @Override
    public void deleteListEvents(List<Event> list) {
        eventRepository.delete(list);
    }

    @Override
    public void deleteAllEvents() {
        eventRepository.deleteAll();
    }

    @Override
    public long countEvents() {
        return eventRepository.count();
    }

    @Override
    public boolean existsEvent(Integer id) {
        return eventRepository.exists(id);
    }
}
