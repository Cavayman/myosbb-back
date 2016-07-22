package com.softserve.osbb.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.softserve.osbb.utils.CustomLocalDateTimeDeserializer;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

/**
 * Created by cavayman on 05.07.2016.
 */

@Entity
@Table(name = "ticket")
public class Ticket {
    private Integer ticketId;
    private String name;
    private String description;
    private String state;
    private LocalDate stateTime;
    private LocalDate time;
    private User user;
    private User assignedTo;
    private Collection<Message> messages;
    private Collection<Attachment> attachments;

    public Ticket() {
    }

    public Ticket(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Ticket(String name, String description, User user) {
        this.name = name;
        this.description = description;
        this.user = user;
    }

    @Id
    @Column(name = "ticket_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    @Column(name = "time")
    public LocalDate getTime() {
        return time;
    }

    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    public void setTime(LocalDate time) {
        this.time = time;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Basic
    @Column(name = "state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    @Column(name = "state_time")
    public LocalDate getStateTime() {
        return stateTime;
    }

    public void setStateTime(LocalDate stateTime) {
        this.stateTime = stateTime;
    }


    public User getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(User assignedTo) {
        this.assignedTo = assignedTo;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @OneToMany(mappedBy = "ticket")
    public Collection<Message> getMessages() {
        return messages;
    }

    public void setMessages(Collection<Message> messages) {
        this.messages = messages;
    }

    @OneToMany(fetch = FetchType.LAZY)
    public Collection<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(Collection<Attachment> attachments) {
        this.attachments = attachments;
    }
}
