package com.softserve.osbb.dto;

import com.softserve.osbb.model.enums.TicketState;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Kris on 22.08.2016.
 */
public class TicketDTO {
    private Integer ticketId;
    private String name;
    private String description;
    private TicketState state;
    private Timestamp stateTime;
    private Timestamp time;
    private UserDTO user;
    private UserDTO assigned;
    private List<MessageDTO> messageDTOList;

    public TicketDTO() {
    }

    public TicketDTO(Integer ticketId, String name, String description, TicketState state, Timestamp stateTime, Timestamp time) {
        this.ticketId = ticketId;
        this.name = name;
        this.description = description;
        this.state = state;
        this.stateTime = stateTime;
        this.time = time;
    }

    public List<MessageDTO> getMessageDTOList() {
        return messageDTOList;
    }

    public void setMessageDTOList(List<MessageDTO> messageDTOList) {
        this.messageDTOList = messageDTOList;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TicketState getState() {
        return state;
    }

    public void setState(TicketState state) {
        this.state = state;
    }

    public Timestamp getStateTime() {
        return stateTime;
    }

    public void setStateTime(Timestamp stateTime) {
        this.stateTime = stateTime;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public UserDTO getAssigned() {
        return assigned;
    }

    public void setAssigned(UserDTO assigned) {
        this.assigned = assigned;
    }

    @Override
    public String toString() {
        return "TicketDTO{" +
                "ticketId=" + ticketId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", state=" + state +
                ", stateTime=" + stateTime +
                ", time=" + time +
                ", user=" + user +
                ", message="+messageDTOList+
                '}';
    }
}
