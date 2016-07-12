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
    public void saveEvent(Event event) {
        eventRepository.save(event);
    }

    @Override
    public void saveEventList(List<Event> list) {
        eventRepository.save(list);
    }

    @Override
    public Event findOneEventByID(Integer id) {
        return eventRepository.findOne(id);
    }

    @Override
    public List<Event> findAllEventsByIDs(List<Integer> ids) {
        return eventRepository.findAll(ids);
    }

    @Override
    public List<Event> findAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public void deleteEvent(Event event) {
        eventRepository.delete(event);
    }

    @Override
    public void deleteEventByID(Integer id) {
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
