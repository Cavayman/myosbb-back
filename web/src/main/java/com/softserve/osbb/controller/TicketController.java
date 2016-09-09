package com.softserve.osbb.controller;


import com.softserve.osbb.dto.TicketDTO;
import com.softserve.osbb.dto.mappers.TicketDTOMapper;
import com.softserve.osbb.model.Ticket;
import com.softserve.osbb.model.User;
import com.softserve.osbb.model.enums.TicketState;
import com.softserve.osbb.service.MessageService;
import com.softserve.osbb.service.TicketService;
import com.softserve.osbb.service.UserService;
import com.softserve.osbb.util.PageRequestGenerator;
import com.softserve.osbb.util.TicketPageCreator;
import com.softserve.osbb.util.resources.EntityResourceList;
import com.softserve.osbb.util.resources.TicketResourceList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.softserve.osbb.util.ResourceUtil.toResource;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Kris on 13.07.2016.
 */

@RestController
@CrossOrigin
@RequestMapping("/restful/ticket")
public class TicketController {

    private static Logger logger = LoggerFactory.getLogger(TicketController.class);

    @Autowired
    TicketService ticketService;

    @Autowired
    UserService userService;

    @Autowired
    MessageService messageService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Resource<Ticket>> createTicket(@RequestBody Ticket ticket) {
        Resource<Ticket> ticketResource;
        try {
            User user = userService.findOne(ticket.getUser().getUserId());
            user.getTickets().add(ticket);
            ticket.setUser(user);

            User assigned = userService.findOne(ticket.getAssigned().getUserId());
            assigned.getTickets().add(ticket);
            ticket.setAssigned(assigned);

            ticket.setTime(new Timestamp(new Date().getTime()));
            ticket.setStateTime(new Timestamp(new Date().getTime()));

            ticket = ticketService.save(ticket);
            userService.save(user);
            userService.save(assigned);
            logger.info("Saving ticket object " + ticket.toString());
            ticketResource = addResourceLinkToTicket(ticket);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(ticketResource, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Resource<TicketDTO>>> listAllTickets() {
        List<Ticket> ticketList = ticketService.getAllTicketsByTime();
        List<TicketDTO> ticketDTOList = new ArrayList<>();
        for (Ticket ticket : ticketList) {
            ticketDTOList.add(TicketDTOMapper.mapTicketEntityToDTO(ticket));
        }
        final List<Resource<TicketDTO>> resourceTicketList = new ArrayList<>();
        for (TicketDTO t : ticketDTOList) {
            resourceTicketList.add(addResourceLinkToTicketDTO(t));
        }
        logger.info("Get all tickets." + Arrays.toString(ticketDTOList.toArray()));
        return new ResponseEntity<>(resourceTicketList, HttpStatus.OK);

    }


    private Resource<TicketDTO> addResourceLinkToTicketDTO(TicketDTO ticket) {
        Resource<TicketDTO> ticketResource = new Resource<>(ticket);
        ticketResource.add(linkTo(methodOn(TicketController.class)
                .getTicketById(ticket.getTicketId()))
                .withSelfRel());
        return ticketResource;
    }

    private Resource<Ticket> addResourceLinkToTicket(Ticket ticket) {
        Resource<Ticket> ticketResource = new Resource<>(ticket);
        ticketResource.add(linkTo(methodOn(TicketController.class)
                .getTicketById(ticket.getTicketId()))
                .withSelfRel());
        return ticketResource;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Resource<Ticket>> getTicketById(@PathVariable("id") Integer ticketId) {
        Ticket ticket = ticketService.findOne(ticketId);
       // TicketDTO ticketDTO = new TicketDTOMapper().mapTicketEntityToDTO(ticket);
        logger.info("Get ticketDTO by id: " + ticket);
        return new ResponseEntity<>(addResourceLinkToTicket(ticket), HttpStatus.OK);
    }

    @RequestMapping(value = "/map/{id}", method = RequestMethod.GET)
    public ResponseEntity<Resource<TicketDTO>> getTicketByIdDTO(@PathVariable("id") Integer ticketId) {
        Ticket ticket = ticketService.findOne(ticketId);
        TicketDTO ticketDTO = new TicketDTOMapper().mapTicketEntityToDTO(ticket);
        logger.info("Get ticketDTO by id: " + ticket);
        return new ResponseEntity<>(addResourceLinkToTicketDTO(ticketDTO), HttpStatus.OK);
    }
    @RequestMapping(value = "/state",method = RequestMethod.PUT)
    public ResponseEntity<Resource<Ticket>> updateState(@RequestBody Ticket ticket) {

        logger.info("Updating ticketState: " + ticket.getTicketId());
        Ticket ticketDB = ticketService.findOne(ticket.getTicketId());
        ticketDB.setStateTime(new Timestamp(new Date().getTime()));
        ticketDB.setState(ticket.getState());

        ticket = ticketService.update(ticketDB);
        Resource<Ticket> ticketResource = new Resource<>(ticketDB);
        ticketResource.add(linkTo(methodOn(TicketController.class).getTicketById(ticket.getTicketId())).withSelfRel());
        return new ResponseEntity<>(ticketResource, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Resource<Ticket>> updateTicket(@RequestBody Ticket ticket) {

        logger.info("Updating ticket by id: " + ticket.getTicketId());
        Ticket ticketDB = ticketService.findOne(ticket.getTicketId());
        ticket.setTime(ticketDB.getTime());

        User assigned = userService.findOne(ticket.getAssigned().getUserId());
        assigned.getTickets().add(ticket);
        ticket.setAssigned(assigned);
              if( ticket.getState() != ticketDB.getState()){

                  logger.info("Updating ticket by id. СТАТЕ РАЗНИЙ: ");
                    ticket.setStateTime(new Timestamp(new Date().getTime()));
                }
        ticket = ticketService.update(ticket);
        userService.save(assigned);
        Resource<Ticket> ticketResource = new Resource<>(ticket);
        ticketResource.add(linkTo(methodOn(TicketController.class).getTicketById(ticket.getTicketId())).withSelfRel());
        return new ResponseEntity<>(ticketResource, HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Ticket> deleteTicketById(@PathVariable("id") Integer ticketId) {
        logger.info("removing ticket by id: " + ticketId);
        ticketService.delete(ticketId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Ticket> deleteAll() {
        logger.info("removing all tickets");
        ticketService.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @RequestMapping(value = "/findName", method = RequestMethod.GET)
    public ResponseEntity<TicketPageCreator> listTicketsByName(
            @RequestParam(value = "pageNumber", required = true) Integer pageNumber,
            @RequestParam(value = "sortedBy", required = false) String sortedBy,
            @RequestParam(value = "order", required = false) Boolean orderType,
            @RequestParam(value = "rowNum", required = false) Integer rowNum,
            @RequestParam(value = "find") String findName) {
        logger.info("get all tickets by page number: " + pageNumber);
        final PageRequest pageRequest = PageRequestGenerator.generatePageRequest(pageNumber)
                .addRows(rowNum)
                .addSortedBy(sortedBy, "name")
                .addOrderType(orderType)
                .toPageRequest();
        Page<Ticket> ticketsByPage = ticketService.getTicketsByName(findName, pageRequest);
        PageRequestGenerator.PageSelector pageSelector = PageRequestGenerator.generatePageSelectorData(ticketsByPage);
        EntityResourceList<Ticket> ticketResourceLinkList = new TicketResourceList();
        ticketsByPage.forEach((ticket) -> ticketResourceLinkList.add(toResource(ticket)));
        TicketPageCreator pageCreator = setUpPageCreator(pageSelector, ticketResourceLinkList);
        logger.info("tickets: "+ ticketsByPage.toString() );
        return new ResponseEntity<>(pageCreator, HttpStatus.OK);
    }

    @RequestMapping(value = "/state", method = RequestMethod.GET)
    public ResponseEntity<TicketPageCreator> listTicketsByState(
            @RequestParam(value = "pageNumber", required = true) Integer pageNumber,
            @RequestParam(value = "sortedBy", required = false) String sortedBy,
            @RequestParam(value = "order", required = false) Boolean orderType,
            @RequestParam(value = "rowNum", required = false) Integer rowNum,
            @RequestParam(value = "findName") TicketState ticketState) {
        logger.info("get all tickets by page number: " + pageNumber);
        final PageRequest pageRequest = PageRequestGenerator.generatePageRequest(pageNumber)
                .addRows(rowNum)
                .addSortedBy(sortedBy, "name")
                .addOrderType(orderType)
                .toPageRequest();
        Page<Ticket> ticketsByPage = ticketService.getTicketsByState(ticketState, pageRequest);
        PageRequestGenerator.PageSelector pageSelector = PageRequestGenerator.generatePageSelectorData(ticketsByPage);
        EntityResourceList<Ticket> ticketResourceLinkList = new TicketResourceList();
        ticketsByPage.forEach((ticket) -> ticketResourceLinkList.add(toResource(ticket)));
        TicketPageCreator pageCreator = setUpPageCreator(pageSelector, ticketResourceLinkList);
        logger.info("tickets: "+ ticketsByPage.toString() );
        return new ResponseEntity<>(pageCreator, HttpStatus.OK);
    }

    @RequestMapping(value = "/userEmail", method = RequestMethod.GET)
    public ResponseEntity<TicketPageCreator> listTicketsByUser(
            @RequestParam(value = "pageNumber", required = true) Integer pageNumber,
            @RequestParam(value = "sortedBy", required = false) String sortedBy,
            @RequestParam(value = "order", required = false) Boolean orderType,
            @RequestParam(value = "rowNum", required = false) Integer rowNum,
            @RequestParam(value = "find") String email) {
        User user = userService.findUserByEmail(email);
        logger.info("get all tickets by page number: " + pageNumber);
        final PageRequest pageRequest = PageRequestGenerator.generatePageRequest(pageNumber)
                .addRows(rowNum)
                .addSortedBy(sortedBy, "name")
                .addOrderType(orderType)
                .toPageRequest();
        Page<Ticket> ticketsByPage = ticketService.getTicketsByUser(user, pageRequest);
        PageRequestGenerator.PageSelector pageSelector = PageRequestGenerator.generatePageSelectorData(ticketsByPage);
        EntityResourceList<Ticket> ticketResourceLinkList = new TicketResourceList();
        ticketsByPage.forEach((ticket) -> ticketResourceLinkList.add(toResource(ticket)));
        TicketPageCreator pageCreator = setUpPageCreator(pageSelector, ticketResourceLinkList);
        logger.info("tickets: "+ ticketsByPage.toString() );
        return new ResponseEntity<>(pageCreator, HttpStatus.OK);
    }


    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<TicketPageCreator> listAllTickets(
            @RequestParam(value = "pageNumber", required = true) Integer pageNumber,
            @RequestParam(value = "sortedBy", required = false) String sortedBy,
            @RequestParam(value = "order", required = false) Boolean orderType,
            @RequestParam(value = "rowNum", required = false) Integer rowNum) {
        logger.info("get all tickets by page number: " + pageNumber);
        final PageRequest pageRequest = PageRequestGenerator.generatePageRequest(pageNumber)
                .addRows(rowNum)
                .addSortedBy(sortedBy, "name")
                .addOrderType(orderType)
                .toPageRequest();
        Page<Ticket> ticketsByPage = ticketService.getAllTickets(pageRequest);
        PageRequestGenerator.PageSelector pageSelector = PageRequestGenerator.generatePageSelectorData(ticketsByPage);
        EntityResourceList<Ticket> ticketResourceLinkList = new TicketResourceList();
        ticketsByPage.forEach((ticket) -> ticketResourceLinkList.add(toResource(ticket)));
        TicketPageCreator pageCreator = setUpPageCreator(pageSelector, ticketResourceLinkList);
        return new ResponseEntity<>(pageCreator, HttpStatus.OK);
    }

    private TicketPageCreator setUpPageCreator(PageRequestGenerator.PageSelector pageSelector, List<Resource<Ticket>> resourceList) {
        TicketPageCreator pageCreator = new TicketPageCreator();
        pageCreator.setRows(resourceList);
        pageCreator.setCurrentPage(Integer.valueOf(pageSelector.getCurrentPage()).toString());
        pageCreator.setBeginPage(Integer.valueOf(pageSelector.getBegin()).toString());
        pageCreator.setEndPage(Integer.valueOf(pageSelector.getEnd()).toString());
        pageCreator.setTotalPages(Integer.valueOf(pageSelector.getTotalPages()).toString());
        return pageCreator;
    }
}
