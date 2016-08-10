package com.softserve.osbb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.softserve.osbb.utils.CustomLocalDateTimeDeserializer;
import com.softserve.osbb.utils.CustomLocalDateTimeSerializer;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by Kris on 05.07.2016.
 */
@Entity
@Table(name = "message")
public class Message {
    private Integer messageId;
    private String description;
    private String message;
    private LocalDate time;
    private Ticket ticket;
    private User user;

    public Message() {
    }

    public Message(String description, String message) {
        this.description = description;
        this.message = message;
    }

    public Message(String description, String message, LocalDate time) {
        this.description = description;
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
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @Column(name = "time")
    public LocalDate getTime() {
        return time;
    }

    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    public void setTime(LocalDate time) {
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", description='" + description + '\'' +
                ", message='" + message + '\'' +
                ", time=" + time +
                '}';
    }
}
