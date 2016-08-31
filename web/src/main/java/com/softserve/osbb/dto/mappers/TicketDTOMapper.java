package com.softserve.osbb.dto.mappers;

import com.softserve.osbb.dto.MessageDTO;
import com.softserve.osbb.dto.TicketDTO;
import com.softserve.osbb.dto.UserDTOMapper;
import com.softserve.osbb.model.Ticket;

import java.util.List;

/**
 * Created by Kris on 22.08.2016.
 */
public class TicketDTOMapper {

    public static TicketDTO mapTicketEntityToDTO(Ticket ticket, List<MessageDTO> messageDTO) {
        TicketDTO ticketDTO = new TicketDTO();
        if(ticket != null) {
            ticketDTO.setTicketId(ticket.getTicketId());
            ticketDTO.setName(ticket.getName());
            ticketDTO.setDescription(ticket.getDescription());
            ticketDTO.setState(ticket.getState());
            ticketDTO.setStateTime(ticket.getStateTime());
            ticketDTO.setTime(ticket.getTime());
            ticketDTO.setUser(new UserDTOMapper().mapUserEntityToDTO(ticket.getUser()));
            ticketDTO.setAssigned(new UserDTOMapper().mapUserEntityToDTO(ticket.getAssigned()));
            ticketDTO.setMessageDTOList(messageDTO);
        }
        return ticketDTO;
    }

    public static TicketDTO mapTicketEntityToDTO(Ticket ticket) {
        TicketDTO ticketDTO = new TicketDTO();
        if(ticket != null) {
            ticketDTO.setTicketId(ticket.getTicketId());
            ticketDTO.setName(ticket.getName());
            ticketDTO.setDescription(ticket.getDescription());
            ticketDTO.setState(ticket.getState());
            ticketDTO.setStateTime(ticket.getStateTime());
            ticketDTO.setTime(ticket.getTime());
            ticketDTO.setUser(new UserDTOMapper().mapUserEntityToDTO(ticket.getUser()));
            ticketDTO.setAssigned(new UserDTOMapper().mapUserEntityToDTO(ticket.getAssigned()));
        }
        return ticketDTO;
    }


}
