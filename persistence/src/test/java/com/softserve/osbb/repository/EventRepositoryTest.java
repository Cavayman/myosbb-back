package com.softserve.osbb.repository;

import com.softserve.osbb.OsbbApplicationRunner;
import com.softserve.osbb.model.EventEntity;
import com.softserve.osbb.model.OsbbEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by nataliia on 06.07.16.
 */

@Rollback
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = OsbbApplicationRunner.class)
public class EventRepositoryTest {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private OsbbRepository osbbRepository;

    private EventEntity eventEntity;
    private EventEntity eventEntity1;

    @Before
    public void init() {

        OsbbEntity osbb = new OsbbEntity();
        osbb.setName("Test OSBB");
        osbbRepository.save(osbb);

        eventEntity = new EventEntity();
        eventEntity.setName("Trash droping.");
        eventEntity.setAuthor("Main OSBB");
        eventEntity.setOsbb(osbb);
        eventEntity.setDescription("Simple repeatable trash recycling.");
        eventEntity.setRepeat(EventEntity.Repeat.EVERY_WEEK);
        eventEntity.setDate(new Date());

        eventEntity1 = new EventEntity();
        eventEntity1.setName("Charity festival.");
        eventEntity1.setAuthor("City Council");
        eventEntity1.setOsbb(osbb);
        eventEntity1.setDescription("Charity festival for homelesspeople.");
        eventEntity1.setRepeat(EventEntity.Repeat.NEVER);
        eventEntity1.setDate(new Date());
    }

    @Test
    public void testSave() {
        eventRepository.save(eventEntity);
        assertEquals(eventEntity, eventRepository.findOne(eventEntity.getEventId()));
    }

    @Test
    public void testSaveList() {
        List<EventEntity> list = new ArrayList<>();
        list.add(eventEntity);
        list.add(eventEntity1);
        eventRepository.save(list);
        assertEquals(list, eventRepository.findAll());
    }

    @Test
    public void testFindOne() {
        eventRepository.save(eventEntity);
        assertEquals(eventEntity, eventRepository.findOne(eventEntity.getEventId()));
    }

    @Test
    public void testFindAllByIDs() {
        List<EventEntity> list = new ArrayList<>();
        list.add(eventEntity1);
        list.add(eventEntity);
        eventRepository.save(list);
        List<Integer> ids = new ArrayList<>();
        ids.add(eventEntity.getEventId());
        ids.add(eventEntity1.getEventId());
        assertTrue(eventRepository.findAll(ids).containsAll(list));
    }

    @Test
    public void testFindAll() {
        List<EventEntity> list = new ArrayList<>();
        list.add(eventEntity);
        list.add(eventEntity1);
        eventRepository.save(list);
        assertTrue(eventRepository.findAll().containsAll(list));
    }

    @Test
    public void testDelete() {
        eventRepository.save(eventEntity);
        eventRepository.delete(eventEntity);
        assertFalse(eventRepository.exists(eventEntity.getEventId()));
    }

    @Test
    public void testDeleteById() {
        eventRepository.save(eventEntity);
        eventRepository.delete(eventEntity.getEventId());
        assertFalse(eventRepository.exists(eventEntity.getEventId()));
    }

    @Test
    public void testDeleteList() {
        List<EventEntity> list = new ArrayList<>();
        list.add(eventEntity);
        list.add(eventEntity1);
        eventRepository.save(list);
        eventRepository.delete(list);
        assertFalse(eventRepository.exists(eventEntity.getEventId()));
        assertFalse(eventRepository.exists(eventEntity1.getEventId()));
    }

    @Test
    public void testDeleteAll() {
        eventRepository.save(eventEntity);
        eventRepository.save(eventEntity1);
        eventRepository.deleteAll();
        assertTrue(eventRepository.findAll().isEmpty());
    }

    @Test
    public void testCount() {
        int before = eventRepository.findAll().size();
        eventRepository.save(eventEntity);
        eventRepository.save(eventEntity1);
        assertEquals(before + 2, eventRepository.count());
    }

    @Test
    public void testExists() {
        eventRepository.save(eventEntity);
        assertTrue(eventRepository.exists(eventEntity.getEventId()));
    }
}