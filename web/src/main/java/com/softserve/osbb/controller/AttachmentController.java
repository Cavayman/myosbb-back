package com.softserve.osbb.controller;

import com.softserve.osbb.model.Attachment;
import com.softserve.osbb.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by nataliia on 11.07.16.
 */

@RestController
public class AttachmentController {

    @Autowired
    private AttachmentService attachmentService;

    @RequestMapping(value = "/attachments", method = RequestMethod.GET)
    public List<Attachment> findAllAttachments() {
        return attachmentService.findAllAttachments();
    }

    @RequestMapping(value = "/attachment/{ids}", method = RequestMethod.GET)
    public List<Attachment> findAttachment(List<Integer> ids) {
        return attachmentService.findAllAttachmentsByIDs(ids);
    }

    @RequestMapping(value = "/attachment/{id}", method = RequestMethod.GET)
    public Attachment findOneAttachment(Integer id) {
        return attachmentService.findOneAttachmentByID(id);
    }
}
