package com.softserve.osbb.repository;

import com.softserve.osbb.PersistenceConfiguration;
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
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by nataliia on 06.07.16.
 */

@Rollback
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PersistenceConfiguration.class)
@Transactional
public class EventRepositoryTest {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private OsbbRepository osbbRepository;

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
        osbbRepository.save(osbb);

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
        event.setRepeat(Periodicity.PERMANENT_WEEKLY);
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
        eventRepository.save(event);
        assertEquals(event, eventRepository.findOne(event.getEventId()));
    }

    @Test
    public void testSaveList() {
        List<Event> list = new ArrayList<>();
        list.add(event);
        list.add(event1);
        eventRepository.save(list);
        assertTrue(eventRepository.findAll().containsAll(list));
    }

    @Test
    public void testFindOne() {
        eventRepository.save(event);
        assertEquals(event, eventRepository.findOne(event.getEventId()));
    }

    @Test
    public void testFindAllByIDs() {
        List<Event> list = new ArrayList<>();
        list.add(event1);
        list.add(event);
        eventRepository.save(list);
        List<Integer> ids = new ArrayList<>();
        ids.add(event.getEventId());
        ids.add(event1.getEventId());
        assertTrue(eventRepository.findAll(ids).containsAll(list));
    }

    @Test
    public void testFindAll() {
        List<Event> list = new ArrayList<>();
        list.add(event);
        list.add(event1);
        eventRepository.save(event);
        eventRepository.save(event1);
        assertTrue(eventRepository.findAll().containsAll(list));
    }

    @Test
    public void testDelete() {
        eventRepository.save(event);
        eventRepository.delete(event);
        assertFalse(eventRepository.exists(event.getEventId()));
    }

    @Test
    public void testDeleteById() {
        eventRepository.save(event);
        eventRepository.delete(event.getEventId());
        assertFalse(eventRepository.exists(event.getEventId()));
    }

    @Test
    public void testDeleteList() {
        List<Event> list = new ArrayList<>();
        list.add(event);
        list.add(event1);
        eventRepository.save(list);
        eventRepository.delete(list);
        assertFalse(eventRepository.exists(event.getEventId()));
        assertFalse(eventRepository.exists(event1.getEventId()));
    }

    @Test
    public void testDeleteAll() {
        eventRepository.save(event);
        eventRepository.save(event1);
        eventRepository.deleteAll();
        assertTrue(eventRepository.findAll().isEmpty());
    }

    @Test
    public void testCount() {
        int before = eventRepository.findAll().size();
        eventRepository.save(event);
        eventRepository.save(event1);
        assertEquals(before + 2, eventRepository.count());
    }

    @Test
    public void testExists() {
        eventRepository.save(event);
        assertTrue(eventRepository.exists(event.getEventId()));
    }

    @Test
    public void testFindByStartTimeBetweenOrEndTimeBetween() {
        List<Event> list = new ArrayList<>();
        list.add(event);
        list.add(event1);
        eventRepository.save(list);
        assertTrue(eventRepository.findBetweenStartTimeAndEndTime(t1,t4).containsAll(list));
        assertTrue(eventRepository.findBetweenStartTimeAndEndTime(t1,t2).contains(event));
        assertFalse(eventRepository.findBetweenStartTimeAndEndTime(t1,t2).contains(event1));
    }
}