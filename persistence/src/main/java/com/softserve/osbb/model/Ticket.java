package com.softserve.osbb.model;

import javax.persistence.*;
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
    private String time;
    private Collection<Message> messagesByTicketId;
    private User users;

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
    @Column(name = "time")
    public String getTime() {
        return time;
    }



    public void setTime(String time) {
        this.time = time;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket that = (Ticket) o;

        if (ticketId != null ? !ticketId.equals(that.ticketId) : that.ticketId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ticketId != null ? ticketId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "ticketByTicketId")
    public Collection<Message> getMessagesByTicketId() {
        return messagesByTicketId;
    }

    public void setMessagesByTicketId(Collection<Message> messagesByTicketId) {
        this.messagesByTicketId = messagesByTicketId;
    }
}
