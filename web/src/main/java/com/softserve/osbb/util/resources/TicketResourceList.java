package com.softserve.osbb.util.resources;

import com.softserve.osbb.controller.TicketController;
import com.softserve.osbb.model.Ticket;
import com.softserve.osbb.util.resources.EntityResourceList;
import org.springframework.hateoas.Resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Kris on 23.08.2016.
 */
public class TicketResourceList extends EntityResourceList<Ticket> {
    @Override
    public Resource<Ticket> createLink(Resource<Ticket> reportResource) {
        Ticket report = reportResource.getContent();

        reportResource.add(linkTo(methodOn(TicketController.class)
                .getTicketById(report.getTicketId())).withSelfRel());


        return reportResource;
    }
}
