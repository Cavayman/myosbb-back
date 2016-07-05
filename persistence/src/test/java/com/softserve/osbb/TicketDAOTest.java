package com.softserve.osbb;

import com.softserve.osbb.dao.TicketDAO;
import com.softserve.osbb.model.TicketEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Kris on 03.07.2016.
 */
/*
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = OsbbApplicationRunner.class)
@Rollback
@Transactional
public class TicketDAOTest {

    @Autowired
    private TicketDAO ticketDAO;

    private TicketEntity ticketEntity = new TicketEntity();

    @Test
    public void testMessageEntityOperations(){
        ticketEntity.setTime(new Date());
        ticketEntity.setName("first ticket");
        ticketEntity.setDiscription("test description");

        ticketEntity = ticketDAO.save(ticketEntity);
        assertNotNull(ticketEntity);
        assertTrue(ticketEntity.getTicketId()!=0);

        ticketEntity.setDiscription("new description");
        ticketEntity = ticketDAO.save(ticketEntity);

        int size = ticketDAO.findAll().size();
        assertTrue(size == 1);

    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testSaveNullMessageEntity(){
        ticketEntity = null;
        ticketDAO.save(ticketEntity);

    }
}
*/
