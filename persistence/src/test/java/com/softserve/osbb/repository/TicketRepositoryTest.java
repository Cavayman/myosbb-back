package com.softserve.osbb.repository;


import com.softserve.osbb.PersistenceConfiguration;
import com.softserve.osbb.model.Attachment;
import com.softserve.osbb.model.Ticket;
import com.softserve.osbb.model.User;
import com.softserve.osbb.model.enums.TicketState;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by Kris on 05.07.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PersistenceConfiguration.class)
@Transactional
public class TicketRepositoryTest {

    private Ticket ticket;

    @Autowired
    TicketRepository ticketRepository;

    @Before
    public void init() {
        ticket = new Ticket();

        ticket.setName("some name");
        ticket.setDescription("some description");
        ticket.setState(TicketState.NEW);
        ticket.setTime(LocalDate.now());
        ticket.setUser(new User());
        ticket.setAttachments(Arrays.asList(new Attachment(), new Attachment()));
    }

    @Test
    public void testSave() {
        Assert.assertNull(ticket.getTicketId());
        ticketRepository.save(ticket);
        Assert.assertNotNull(ticket.getTicketId());
    }

    @Test
    public void testDeleteTicketById() {
        ticketRepository.save(ticket);
        ticketRepository.delete(ticket.getTicketId());
        Assert.assertFalse(ticketRepository.exists(ticket.getTicketId()));
    }

    @Test
    public void testGetAllTicket() {
        List<Ticket> list = Arrays.asList(new Ticket(), new Ticket(), new Ticket());
        ticketRepository.save(list);
        Assert.assertTrue(list.size() == ticketRepository.findAll().size());
    }

    @Test
    public void testDeleteByTicket() {
        ticketRepository.save(ticket);
        ticketRepository.delete(ticket);
        Assert.assertFalse(ticketRepository.exists(ticket.getTicketId()));
    }

    @Test
    public void testDeleteAllTickets() {
        ticketRepository.deleteAll();
        ticketRepository.deleteAll();
        assertTrue(ticketRepository.findAll().isEmpty());
    }
}
