package com.softserve.osbb.dto;

import com.softserve.osbb.model.Message;
import com.softserve.osbb.model.Ticket;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Kris on 31.08.2016.
 */
public class MessageDTO {
    private Integer messageId;
    private Integer parentId;
    private String message;
    private Timestamp time;
    private Ticket ticket;
    private UserDTO user;
   // private Collection<Message> answer = new ArrayList<>();

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

  /*  public Collection<Message> getAnswer() {
        return answer;
    }

    public void setAnswer(Collection<Message> answer) {
        this.answer = answer;
    }*/

    @Override
    public String toString() {
        return "MessageDTO{" +
                "messageId=" + messageId +
                ", parentId=" + parentId +
                ", message='" + message + '\'' +
                ", time=" + time +
                ", ticket=" + ticket +
                ", user=" + user +
               // ", answers" + answer+
                '}';
    }
}

