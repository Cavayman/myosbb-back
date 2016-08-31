package com.softserve.osbb.util;

import com.softserve.osbb.model.Ticket;
import org.springframework.hateoas.Resource;

import java.util.List;

/**
 * Created by Kris on 29.08.2016.
 */
public class TicketPageCreator extends PageCreator<Resource<Ticket>> {

    private List<String> dates;

    public List<String> getDates() {
        return dates;
    }
}
