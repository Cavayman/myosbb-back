package com.softserve.osbb.controller;

import com.softserve.osbb.model.Message;
import com.softserve.osbb.model.Ticket;
import com.softserve.osbb.service.MessageService;
import com.softserve.osbb.service.TicketService;
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
@CrossOrigin
@RequestMapping("/restful/message")
public class MessageController {

    private static Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private TicketService ticketService;

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/ticket/{id}",method = RequestMethod.POST)
    public ResponseEntity<Resource<Message>> createMessage(@RequestBody Message message,
                                                           @PathVariable("id")  Integer ticketId  ){
        Resource<Message> messageResource;
        try {
            logger.info("Saving message object " + message.toString());
            Ticket ticket = ticketService.findOne(ticketId);
            ticket.getMessages().add(message);
            message.setTicket(ticket);
            message = messageService.save(message);
            ticketService.update(ticket);
            messageResource = addResourceLinkToMessage(message);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(messageResource, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Resource<Message>>> listAllMessages() {
        List<Message> messageList = messageService.findAll();
        logger.info("Getting all messages.");
        List<Resource<Message>> resourceMessageList = new ArrayList<>();
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

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Resource<Message>> getMessageById(@PathVariable("id") Integer messageId) {

        logger.info("Fetching message by id: " + messageId);
        Message message = messageService.findOne(messageId);
        Resource<Message> messageResource = addResourceLinkToMessage(message);
        messageResource.add(linkTo(methodOn(MessageController.class).getMessageById(messageId)).withSelfRel());
        return new ResponseEntity<>(messageResource, HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Resource<Message>> updateMessage(@RequestBody Message message) {
        logger.info("Updating message by id: " + message.getMessageId());
        message = messageService.update(message);
        Resource<Message> messageResource = new Resource<>(message);
        messageResource.add(linkTo(methodOn(MessageController.class).getMessageById(message.getMessageId())).withSelfRel());
        return new ResponseEntity<>(messageResource, HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Message> deleteMessageById(@PathVariable("id") Integer messageId) {
        logger.info("Removing message by id: " + messageId);
        messageService.delete(messageId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Message> deleteAll() {
        logger.info("Removing all messages");
        messageService.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/comments/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Resource<Message>>>  getMessageByTicketId(@PathVariable("id")Integer ticketId) {

        logger.info("message by ticket: " + ticketId);
        Ticket ticket = ticketService.findOne(ticketId);
        List<Message> messages = messageService.findMessagesByTicket(ticket);

        List<Resource<Message>> resourceMessageList = new ArrayList<>();
        for (Message e : messages) {
            Resource<Message> messageResource = new Resource<>(e);
            messageResource.add(linkTo(methodOn(MessageController.class)
                    .getMessageById(e.getMessageId()))
                    .withSelfRel());
            resourceMessageList.add(messageResource);
        }
        return new ResponseEntity<>(resourceMessageList, HttpStatus.OK);
    }
}
