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

    private static final Resource<Message> EMPTY_MESSAGE_LINK = new Resource<>(new Message());
    private static final List<Resource<Message>> MESSAGE_LIST = new ArrayList<>(0);

    private static Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
   private MessageServiceImpl messageService;

    @RequestMapping(value = "/message", method = RequestMethod.POST)
    public ResponseEntity<Resource<Message>> createMessage(@RequestBody Message message) {

        Resource<Message> messageResource;
        try {
            logger.info("saving message object " + message);
            message = messageService.save(message);
            messageResource = addResourceLinkToMessage(message);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(messageResource, HttpStatus.OK);
    }

    @RequestMapping(value = "/message", method = RequestMethod.GET)
    public ResponseEntity<List<Resource<Message>>> listAllMessages() {

        List<Message> messageList = messageService.findAll();
        logger.info("Getting all messages: " + messageList);
        if (messageList.isEmpty()) {
            logger.warn("no messagetList were found in the list: " + messageList);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        final List<Resource<Message>> resourceMessageList = new ArrayList<>();
        messageList.stream().forEach((message) -> resourceMessageList.add(addResourceLinkToMessage(message)));
        return new ResponseEntity<>(resourceMessageList, HttpStatus.OK);
    }

    private Resource<Message> addResourceLinkToMessage(Message message) {
        if (message == null) {
            return EMPTY_MESSAGE_LINK;
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

        return messageResource == EMPTY_MESSAGE_LINK ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(messageResource, HttpStatus.OK);
    }


    @RequestMapping(value = "/message/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Resource<Message>> updateMessage(@RequestBody Message message) {

        Resource<Message> messageResource;

        try {
            messageService.update(message);
            messageResource = addResourceLinkToMessage(message);

        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

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
