package com.softserve.osbb.service;

import com.softserve.osbb.config.ServiceApplication;
import com.softserve.osbb.model.Attachment;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

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

    @Before
    public void init() {

        attachment = new Attachment();
        attachment.setPath("C://...");

        attachment1 = new Attachment();
        attachment1.setPath("D://...");
    }

    @Test
    public void testSave() {
        attachmentService.saveAttachment(attachment);
        assertEquals(attachment, attachmentService.getAttachmentById(attachment.getAttachmentId()));
    }

    @Test
    public void testSaveList() {
        List<Attachment> list = new ArrayList<>();
        list.add(attachment);
        list.add(attachment1);
        attachmentService.saveAttachments(list);
        assertTrue(attachmentService.getAllAttachments().containsAll(list));
    }

    @Test
    public void testFindOne() {
        attachmentService.saveAttachment(attachment);
        assertEquals(attachment, attachmentService.getAttachmentById(attachment.getAttachmentId()));
    }

    @Test
    public void testFindAttachments() {
        List<Attachment> list = new ArrayList<>();
        list.add(attachment1);
        list.add(attachment);
        attachmentService.saveAttachments(list);
        assertTrue(attachmentService.getAttachments(list).containsAll(list));
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
        assertTrue(attachmentService.getAttachmentsByIds(ids).containsAll(list));
    }

    @Test
    public void testFindAll() {
        List<Attachment> list = new ArrayList<>();
        list.add(attachment);
        list.add(attachment1);
        attachmentService.saveAttachment(attachment);
        attachmentService.saveAttachment(attachment1);
        assertTrue(attachmentService.getAllAttachments().containsAll(list));
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
        assertTrue(attachmentService.getAllAttachments().isEmpty());
    }

    @Test
    public void testCount() {
        int before = attachmentService.getAllAttachments().size();
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