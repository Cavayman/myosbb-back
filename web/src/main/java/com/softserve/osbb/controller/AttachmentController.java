package com.softserve.osbb.controller;

import com.softserve.osbb.model.Attachment;
import com.softserve.osbb.service.AttachmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by nataliia on 11.07.16.
 */

@RestController
@RequestMapping("/restful/attachment")
public class AttachmentController {

    private static Logger logger = LoggerFactory.getLogger(AttachmentController.class);

    @Autowired
    private AttachmentService attachmentService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Resource<Attachment>> createAttachment(@RequestBody Attachment attachment) {
        logger.info("Saving attachment " + attachment);
        attachment = attachmentService.saveAttachment(attachment);
        Resource<Attachment> attachmentResource = new Resource<>(attachment);
        attachmentResource.add(linkTo(methodOn(AttachmentController.class).findAttachmentById(attachment.getAttachmentId())).withSelfRel());
        return new ResponseEntity<>(attachmentResource, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Resource<Attachment>>> findAllAttachments() {
        List<Attachment> attachmentList = attachmentService.findAllAttachments();
        logger.info("Getting all attachments.");
        final List<Resource<Attachment>> resourceAttachmentList = new ArrayList<>();
        for (Attachment e : attachmentList) {
            Resource<Attachment> attachmentResource = new Resource<>(e);
            attachmentResource.add(linkTo(methodOn(AttachmentController.class)
                    .findAttachmentById(e.getAttachmentId()))
                    .withSelfRel());
            resourceAttachmentList.add(attachmentResource);
        }
        return new ResponseEntity<>(resourceAttachmentList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Resource<Attachment>> findAttachmentById(@PathVariable("id") Integer attachmentId) {
        logger.info("Getting attachment by id: " + attachmentId);
        Attachment attachment = attachmentService.findAttachmentById(attachmentId);
        Resource<Attachment> attachmentResource = new Resource<>(attachment);
        attachmentResource.add(linkTo(methodOn(AttachmentController.class).findAttachmentById(attachmentId)).withSelfRel());
        return new ResponseEntity<>(attachmentResource, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Resource<Attachment>> updateAttachment(@PathVariable("id") Integer attachmentId,
                                                       @RequestBody Attachment attachment) {
        logger.info("Updating attachment by id: " + attachmentId);
        attachment = attachmentService.updateAttachment(attachmentId, attachment);
        Resource<Attachment> attachmentResource = new Resource<>(attachment);
        attachmentResource.add(linkTo(methodOn(AttachmentController.class).findAttachmentById(attachmentId)).withSelfRel());
        return new ResponseEntity<>(attachmentResource, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Attachment> deleteAttachmentById(@PathVariable("id") Integer attachmentId) {
        logger.info("Removing attachment by id: " + attachmentId);
        attachmentService.deleteAttachmentById(attachmentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Attachment> deleteAllAttachments() {
        logger.info("Removing all attachments.");
        attachmentService.deleteAllAttachments();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
