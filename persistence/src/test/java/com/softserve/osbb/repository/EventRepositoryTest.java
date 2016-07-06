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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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

    @Before
    public void init(){
        eventEntity = new EventEntity();
        eventEntity.setName("Trash droping.");
        eventEntity.setAuthor("Main OSBB");
        OsbbEntity osbb = new OsbbEntity();
        osbb.setName("Test OSBB");
        osbbRepository.save(osbb);
        eventEntity.setOsbb(osbb);
        eventEntity.setDescription("Simple repeatable trash recycling.");
        eventEntity.setRepeat(EventEntity.Repeat.EVERY_WEEK);
        eventEntity.setDate(new Date());
    }

    @Test
    public void testSaveEvent(){
        eventRepository.save(eventEntity);
        assertEquals(eventRepository.findOne(eventEntity.getEventId()), eventEntity);
    }
}