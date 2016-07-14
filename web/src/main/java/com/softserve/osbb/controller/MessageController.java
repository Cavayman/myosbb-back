package com.softserve.osbb.controller;

import com.softserve.osbb.model.Message;
import com.softserve.osbb.service.MessageService;
import com.softserve.osbb.service.impl.MessageServiceImpl;
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
 * Created by Kris on 12.07.2016.
 */
@RestController
@RequestMapping("/restful")
public class MessageController {

    private static Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/message", method = RequestMethod.POST)
    public ResponseEntity<Resource<Message>> createMessage(@RequestBody Message message) {

        logger.info("saving message object " + message);
        message = messageService.save(message);
        Resource<Message> messageResource = new Resource<>(message);
        messageResource.add(linkTo(methodOn(MessageController.class).getMessageById(message.getMessageId())).withSelfRel());
        return new ResponseEntity<>(messageResource, HttpStatus.OK);
    }

    @RequestMapping(value = "/message", method = RequestMethod.GET)
    public ResponseEntity<List<Resource<Message>>> listAllMessages() {
        List<Message> messageList = messageService.findAll();
        logger.info("Getting all messages.");
        final List<Resource<Message>> resourceMessageList = new ArrayList<>();
        for (Message e : messageList) {
            Resource<Message> messageResource = new Resource<>(e);
            messageResource.add(linkTo(methodOn(MessageController.class)
                    .getMessageById(e.getMessageId()))
                    .withSelfRel());
            resourceMessageList.add(messageResource);
        }
        return new ResponseEntity<>(resourceMessageList, HttpStatus.OK);
    }

    private Resource<Message> addResourceLinkToMessage(Message message) {
        if (message == null) {
            return null;
        }
        Resource<Message> messageResource = new Resource<>(message);

        messageResource.add(linkTo(methodOn(MessageController.class)
                .getMessageById(message.getMessageId()))
                .withSelfRel());
        return messageResource;
    }

    @RequestMapping(value = "/message/{id}", method = RequestMethod.GET)
    public ResponseEntity<Resource<Message>> getMessageById(@PathVariable("id") Integer messageId) {

        logger.info("fetching message by id: " + messageId);
        Message message = messageService.findOne(messageId);
        Resource<Message> messageResource = addResourceLinkToMessage(message);
        messageResource.add(linkTo(methodOn(MessageController.class).getMessageById(messageId)).withSelfRel());
        return new ResponseEntity<>(messageResource, HttpStatus.OK);
    }


    @RequestMapping(value = "/message/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Resource<Message>> updateMessage(@PathVariable("id") Integer messageId,@RequestBody Message message) {

        logger.info("Updating message by id: " + messageId);
        message = messageService.update(messageId, message);
        Resource<Message> messageResource = new Resource<>(message);
        messageResource.add(linkTo(methodOn(MessageController.class).getMessageById(messageId)).withSelfRel());
        return new ResponseEntity<>(messageResource, HttpStatus.OK);
    }


    @RequestMapping(value = "/message/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Message> deleteMessageById(@PathVariable("id") Integer messageId) {
        logger.info("removing message by id: " + messageId);
        messageService.delete(messageId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/message", method = RequestMethod.DELETE)
    public ResponseEntity<Message> deleteAll() {
        logger.info("removing all messages");
        messageService.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
