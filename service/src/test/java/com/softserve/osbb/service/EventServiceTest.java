package com.softserve.osbb.service;

import com.softserve.osbb.config.ServiceApplication;
import com.softserve.osbb.model.Event;
import com.softserve.osbb.model.Osbb;
import com.softserve.osbb.model.enums.Periodicity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    private EventService eventService;
    
    @Autowired
    private OsbbService osbbService;

    private Event event;
    private Event event1;
    private Timestamp t1;
    private Timestamp t2;
    private Timestamp t3;
    private Timestamp t4;

    @Before
    public void init() throws ParseException {

        Osbb osbb = new Osbb();
        osbb.setName("Test OSBB");
        osbbService.addOsbb(osbb);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
        t1 = new Timestamp(dateFormat.parse("2016-01-01T00:00").getTime());
        t2 = new Timestamp(dateFormat.parse("2016-01-02T00:00").getTime());
        t3 = new Timestamp(dateFormat.parse("2016-01-03T00:00").getTime());
        t4 = new Timestamp(dateFormat.parse("2016-01-04T00:00").getTime());

        event = new Event();
        event.setTitle("Trash droping.");
        event.setAuthor("Main OSBB");
        event.setOsbb(osbb);
        event.setDescription("Simple repeatable trash recycling.");
        event.setRepeat(Periodicity.PERMANENT_DAYLY);
        event.setStartTime(t1);
        event.setEndTime(t2);

        event1 = new Event();
        event1.setTitle("Charity festival.");
        event1.setAuthor("City Council");
        event1.setOsbb(osbb);
        event1.setDescription("Charity festival for homelesspeople.");
        event1.setRepeat(Periodicity.ONE_TIME);
        event1.setStartTime(t3);
        event1.setEndTime(t4);
    }

    @Test
    public void testSave() {
        eventService.saveEvent(event);
        assertEquals(event, eventService.getEventById(event.getEventId()));
    }

    @Test
    public void testSaveList() {
        List<Event> list = new ArrayList<>();
        list.add(event);
        list.add(event1);
        eventService.saveEvents(list);
        assertTrue(eventService.getAllEvents().containsAll(list));
    }

    @Test
    public void testFindOne() {
        eventService.saveEvent(event);
        assertEquals(event, eventService.getEventById(event.getEventId()));
    }

    @Test
    public void testFindEvents() {
        List<Event> list = new ArrayList<>();
        list.add(event1);
        list.add(event);
        eventService.saveEvents(list);
        assertTrue(eventService.getEvents(list).containsAll(list));
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
        assertTrue(eventService.getEventsByIds(ids).containsAll(list));
    }

    @Test
    public void testFindAll() {
        List<Event> list = new ArrayList<>();
        list.add(event);
        list.add(event1);
        eventService.saveEvent(event);
        eventService.saveEvent(event1);
        assertTrue(eventService.getAllEvents().containsAll(list));
    }

    @Test
    public void testFindByInterval() {
        List<Event> list = new ArrayList<>();
        list.add(event);
        list.add(event1);
        eventService.saveEvents(list);
        List<Event> eventsWithRepeat = eventService.findByInterval(t1, t3);
        int count = 0;
        for (Event e : eventsWithRepeat) {
            if (e.getEventId().equals(event.getEventId())) {
                count++;
            }
        }
        assertTrue(count == 1);
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
        assertTrue(eventService.getAllEvents().isEmpty());
    }

    @Test
    public void testCount() {
        int before = eventService.getAllEvents().size();
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