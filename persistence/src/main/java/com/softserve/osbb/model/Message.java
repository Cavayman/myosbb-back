package com.softserve.osbb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Kris on 05.07.2016.
 */
@Entity
@Table(name = "message")
public class Message {
    private Integer messageId;
    private Integer parentId;
    private String message;
    private Timestamp time;
    private Ticket ticket;
    private User user;

    public Message() {
    }

    public Message(String message) {

        this.message = message;
    }

    public Message(String message, Timestamp time) {
        this.message = message;
        this.time = time;
    }

    @Id
    @Column(name = "message_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    @Basic
    @Column(name = "time")
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Basic
    @Column(name = "message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ticket_id", referencedColumnName = "ticket_id")
    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    @Basic
    @Column(name = "parentId")
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                "parrentId=" + parentId +
                ", message='" + message + '\'' +
                ", time=" + time +
                '}';
    }
}
