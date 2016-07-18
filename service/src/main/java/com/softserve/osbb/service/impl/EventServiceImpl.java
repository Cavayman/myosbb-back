package com.softserve.osbb.service.impl;

import com.softserve.osbb.model.Event;
import com.softserve.osbb.repository.EventRepository;
import com.softserve.osbb.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by nataliia on 10.07.16.
 */
@Service
public class EventServiceImpl implements EventService {

    @Autowired
    EventRepository eventRepository;

    @Override
    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public List<Event> saveEvents(List<Event> list) {
        return eventRepository.save(list);
    }

    @Override
    public List<Event> findEvents(List<Event> list) {
        return eventRepository.save(list);
    }

    @Override
    public Event findEventById(Integer id) {
        return eventRepository.findOne(id);
    }

    @Override
    public List<Event> findEventsByIds(List<Integer> ids) {
        return eventRepository.findAll(ids);
    }

    @Override
    public List<Event> findAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Event updateEvent(Integer id, Event event) {
        return eventRepository.exists(id) ? eventRepository.save(event) : null;
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
    public void deleteEvents(List<Event> list) {
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
