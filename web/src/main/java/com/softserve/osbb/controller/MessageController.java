package com.softserve.osbb.controller;

import com.softserve.osbb.dto.MessageDTO;
import com.softserve.osbb.dto.mappers.MessageDTOMapper;
import com.softserve.osbb.model.Message;
import com.softserve.osbb.model.Ticket;
import com.softserve.osbb.model.User;
import com.softserve.osbb.service.MessageService;
import com.softserve.osbb.service.TicketService;
import com.softserve.osbb.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/ticket/{id}",method = RequestMethod.POST)
    public ResponseEntity<Resource<Message>> createMessage(@RequestBody Message message,
                                                           @PathVariable("id")  Integer ticketId){
        Resource<Message> messageResource;
        try {
            User user = userService.findOne(message.getUser().getUserId());
            user.getMessages().add(message);
            message.setUser(user);
            logger.info("User mes " + user.getUserId());

            Ticket ticket = ticketService.findOne(ticketId);
            ticket.getMessages().add(message);
            message.setTicket(ticket);
            logger.info("Ticket mes " + ticket.getTicketId());

            message.setTime(new Timestamp(new Date().getTime()));
            message = messageService.save(message);
            userService.save(user);
            ticketService.update(ticket);
            messageResource = addResourceLinkToMessage(message);
            logger.info("Saving message object " + message.toString());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(messageResource, HttpStatus.OK);
    }

    @RequestMapping(value = "/answer",method = RequestMethod.POST)
    public ResponseEntity<Resource<Message>> createAnswer(@RequestBody MessageDTO messageDTO){
        Resource<Message> messageResource;
        logger.info("MESSAGE"+messageDTO);
        try {
            User user = userService.findOne(messageDTO.getUser().getUserId());
            Message message = MessageDTOMapper.mapMessageDTOtoEntity(messageDTO,user);
            user.getMessages().add(message);
            message.setUser(user);
            logger.info("ANSW " + message);

            message.setTime(new Timestamp(new Date().getTime()));
            message = messageService.save(message);
            userService.save(user);
            messageResource = addResourceLinkToMessage(message);
            logger.info("Saving answer object " + message.toString());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(messageResource, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Resource<MessageDTO>>> listAllMessages() {
        List<Message> messageList = messageService.findAll();
        List<MessageDTO> messageDTO = new ArrayList<>();
        for(int i=0;i<messageList.size();i++){
            List<Message> answersList = messageService.getAnswers(messageList.get(i).getMessageId());
            messageDTO.add(MessageDTOMapper.mapMessageEntityToDTO(messageList.get(i),answersList));
            logger.info("GETTING MESS  "+messageDTO.get(i));
        }

        logger.info("Getting all messages.");
        List<Resource<MessageDTO>> resourceMessageList = new ArrayList<>();
        if(resourceMessageList.isEmpty()){
            logger.info("Getting all messages EMPTY");

        }
        for (MessageDTO e : messageDTO) {
            Resource<MessageDTO> messageResource = new Resource<>(e);
            messageResource.add(linkTo(methodOn(MessageController.class)
                    .getMessageById(e.getMessageId()))
                    .withSelfRel());
            resourceMessageList.add(messageResource);
        }
        return new ResponseEntity<>(resourceMessageList, HttpStatus.OK);
    }

    private Resource<MessageDTO> addResourceLinkToMessageDTO(MessageDTO message) {
        if (message == null) {
            return null;
        }
        Resource<MessageDTO> messageResource = new Resource<>(message);

        messageResource.add(linkTo(methodOn(MessageController.class)
                .getMessageById(message.getMessageId()))
                .withSelfRel());
        return messageResource;
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
    public ResponseEntity<Resource<Message>> updateMessage(@RequestBody Message message, @PathVariable("id") Integer ticketId) {
        logger.info("Updating message by id: " + ticketId);
        message.setTicket(ticketService.findOne(ticketId));
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
