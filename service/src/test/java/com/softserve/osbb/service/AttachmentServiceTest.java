package com.softserve.osbb.service;

import com.softserve.osbb.config.ServiceApplication;
import com.softserve.osbb.model.Attachment;
import com.softserve.osbb.model.Osbb;
import com.softserve.osbb.model.Ticket;
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
public class AttachmentServiceTest {

    private Attachment attachment;
    private Attachment attachment1;

    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private TicketService ticketService;


    @Before
    public void init() {
        Ticket ticket = new Ticket();
        ticket.setName("Test Ticket");
        ticketService.save(ticket);

        attachment = new Attachment();
        attachment.setPath("C://...");
        attachment.setTicket(ticket);

        attachment1 = new Attachment();
        attachment1.setPath("D://...");
        attachment1.setTicket(ticket);
    }

    @Test
    public void testSave() {
        attachmentService.saveAttachment(attachment);
        assertEquals(attachment, attachmentService.findAttachmentById(attachment.getAttachmentId()));
    }

    @Test
    public void testSaveList() {
        List<Attachment> list = new ArrayList<>();
        list.add(attachment);
        list.add(attachment1);
        attachmentService.saveAttachments(list);
        assertTrue(attachmentService.findAllAttachments().containsAll(list));
    }

    @Test
    public void testFindOne() {
        attachmentService.saveAttachment(attachment);
        assertEquals(attachment, attachmentService.findAttachmentById(attachment.getAttachmentId()));
    }

    @Test
    public void testFindAttachments() {
        List<Attachment> list = new ArrayList<>();
        list.add(attachment1);
        list.add(attachment);
        attachmentService.saveAttachments(list);
        assertTrue(attachmentService.findAttachments(list).containsAll(list));
    }

    @Test
    public void testFindAllByIDs() {
        List<Attachment> list = new ArrayList<>();
        list.add(attachment1);
        list.add(attachment);
        attachmentService.saveAttachments(list);
        List<Integer> ids = new ArrayList<>();
        ids.add(attachment.getAttachmentId());
        ids.add(attachment1.getAttachmentId());
        assertTrue(attachmentService.findAttachmentsByIds(ids).containsAll(list));
    }

    @Test
    public void testFindAll() {
        List<Attachment> list = new ArrayList<>();
        list.add(attachment);
        list.add(attachment1);
        attachmentService.saveAttachment(attachment);
        attachmentService.saveAttachment(attachment1);
        assertTrue(attachmentService.findAllAttachments().containsAll(list));
    }

    @Test
    public void testDelete() {
        attachmentService.saveAttachment(attachment);
        attachmentService.deleteAttachment(attachment);
        assertFalse(attachmentService.existsAttachment(attachment.getAttachmentId()));
    }

    @Test
    public void testDeleteById() {
        attachmentService.saveAttachment(attachment);
        attachmentService.deleteAttachmentById(attachment.getAttachmentId());
        assertFalse(attachmentService.existsAttachment(attachment.getAttachmentId()));
    }

    @Test
    public void testDeleteList() {
        List<Attachment> list = new ArrayList<>();
        list.add(attachment);
        list.add(attachment1);
        attachmentService.saveAttachments(list);
        attachmentService.deleteAttachments(list);
        assertFalse(attachmentService.existsAttachment(attachment.getAttachmentId()));
        assertFalse(attachmentService.existsAttachment(attachment1.getAttachmentId()));
    }

    @Test
    public void testDeleteAll() {
        attachmentService.saveAttachment(attachment);
        attachmentService.saveAttachment(attachment1);
        attachmentService.deleteAllAttachments();
        assertTrue(attachmentService.findAllAttachments().isEmpty());
    }

    @Test
    public void testCount() {
        int before = attachmentService.findAllAttachments().size();
        attachmentService.saveAttachment(attachment);
        attachmentService.saveAttachment(attachment1);
        assertEquals(before + 2, attachmentService.countAttachments());
    }

    @Test
    public void testExists() {
        attachmentService.saveAttachment(attachment);
        assertTrue(attachmentService.existsAttachment(attachment.getAttachmentId()));
    }

}