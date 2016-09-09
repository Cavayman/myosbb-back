package com.softserve.osbb.service.impl;

import com.softserve.osbb.model.Event;
import com.softserve.osbb.repository.EventRepository;
import com.softserve.osbb.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by nataliia on 10.07.16.
 */

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    EventRepository eventRepository;

    private static final int DEF_ROWS = 10;

    @Override
    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public List<Event> saveEvents(List<Event> list) {
        return eventRepository.save(list);
    }

    @Override
    public List<Event> getEvents(List<Event> list) {
        return eventRepository.save(list);
    }

    @Override
    public Event getEventById(Integer id) {
        return eventRepository.findOne(id);
    }

    @Override
    public List<Event> getEventsByIds(List<Integer> ids) {
        return eventRepository.findAll(ids);
    }

    @Override
    public List<Event> getAllEvents() {
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

    @Override
    public Page<Event> getAllEvents(Integer pageNumber, String sortBy, Boolean order) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, DEF_ROWS,
                getSortingOrder(order), sortBy == null ? "startTime" : sortBy);
        return eventRepository.findAll(pageRequest);
    }

    public Sort.Direction getSortingOrder(Boolean order) {
        if (order == null) {
            return Sort.Direction.DESC;
        }
        return order == true ? Sort.Direction.ASC : Sort.Direction.DESC;
    }

    @Override
    public List<Event> findEventsByNameOrAuthorOrDescription(String search) {
      return eventRepository.findByNameOrAuthorOrDescription(search);
    }

    @Override
    public List<Event> findByInterval(Timestamp start, Timestamp end) {
        return eventRepository.findBetweenStartTimeAndEndTime(start, end);
    }

}
