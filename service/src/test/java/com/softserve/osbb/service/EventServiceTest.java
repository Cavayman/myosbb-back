package com.softserve.osbb.service;

import com.softserve.osbb.config.ServiceApplication;
import com.softserve.osbb.model.Event;
import com.softserve.osbb.model.Osbb;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by nataliia on 18.07.16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ServiceApplication.class)
@Rollback
@Transactional
public class EventServiceTest {
    
    private Event event;
    private Event event1;

    @Autowired
    private EventService eventService;
    
    @Autowired
    private OsbbService osbbService;
    
    @Before
    public void init() {
        Osbb osbb = new Osbb();
        osbb.setName("Test OSBB");
        osbbService.addOsbb(osbb);

        event = new Event();
        event.setName("Trash droping.");
        event.setAuthor("Main OSBB");
        event.setOsbb(osbb);
        event.setDescription("Simple repeatable trash recycling.");
        event.setRepeat(Event.Repeat.EVERY_WEEK);
        event.setDate(LocalDate.now());

        event1 = new Event();
        event1.setName("Charity festival.");
        event1.setAuthor("City Council");
        event1.setOsbb(osbb);
        event1.setDescription("Charity festival for homelesspeople.");
        event1.setRepeat(Event.Repeat.NEVER);
        event1.setDate(LocalDate.now());
    }

    @Test
    public void testSave() {
        eventService.saveEvent(event);
        assertEquals(event, eventService.findEventById(event.getEventId()));
    }

    @Test
    public void testSaveList() {
        List<Event> list = new ArrayList<>();
        list.add(event);
        list.add(event1);
        eventService.saveEvents(list);
        assertTrue(eventService.findAllEvents().containsAll(list));
    }

    @Test
    public void testFindOne() {
        eventService.saveEvent(event);
        assertEquals(event, eventService.findEventById(event.getEventId()));
    }

    @Test
    public void testFindEvents() {
        List<Event> list = new ArrayList<>();
        list.add(event1);
        list.add(event);
        eventService.saveEvents(list);
        assertTrue(eventService.findEvents(list).containsAll(list));
    }

    @Test
    public void testFindAllByIDs() {
        List<Event> list = new ArrayList<>();
        list.add(event1);
        list.add(event);
        eventService.saveEvents(list);
        List<Integer> ids = new ArrayList<>();
        ids.add(event.getEventId());
        ids.add(event1.getEventId());
        assertTrue(eventService.findEventsByIds(ids).containsAll(list));
    }

    @Test
    public void testFindAll() {
        List<Event> list = new ArrayList<>();
        list.add(event);
        list.add(event1);
        eventService.saveEvent(event);
        eventService.saveEvent(event1);
        assertTrue(eventService.findAllEvents().containsAll(list));
    }

    @Test
    public void testDelete() {
        eventService.saveEvent(event);
        eventService.deleteEvent(event);
        assertFalse(eventService.existsEvent(event.getEventId()));
    }

    @Test
    public void testDeleteById() {
        eventService.saveEvent(event);
        eventService.deleteEventById(event.getEventId());
        assertFalse(eventService.existsEvent(event.getEventId()));
    }

    @Test
    public void testDeleteList() {
        List<Event> list = new ArrayList<>();
        list.add(event);
        list.add(event1);
        eventService.saveEvents(list);
        eventService.deleteEvents(list);
        assertFalse(eventService.existsEvent(event.getEventId()));
        assertFalse(eventService.existsEvent(event1.getEventId()));
    }

    @Test
    public void testDeleteAll() {
        eventService.saveEvent(event);
        eventService.saveEvent(event1);
        eventService.deleteAllEvents();
        assertTrue(eventService.findAllEvents().isEmpty());
    }

    @Test
    public void testCount() {
        int before = eventService.findAllEvents().size();
        eventService.saveEvent(event);
        eventService.saveEvent(event1);
        assertEquals(before + 2, eventService.countEvents());
    }

    @Test
    public void testExists() {
        eventService.saveEvent(event);
        assertTrue(eventService.existsEvent(event.getEventId()));
    }
    
}