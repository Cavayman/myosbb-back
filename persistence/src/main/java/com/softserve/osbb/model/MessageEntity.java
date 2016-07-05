package com.softserve.osbb.model;

import javax.persistence.*;

/**
 * Created by Kris on 05.07.2016.
 */
@Entity
@Table(name = "message", schema = "public", catalog = "myosbb")
public class MessageEntity {
    private Integer messageId;
    private String time;
    private String message;
    private String description;
    private TicketEntity ticketByTicketId;
    private UserEntity user;

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
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
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
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessageEntity that = (MessageEntity) o;

        if (messageId != null ? !messageId.equals(that.messageId) : that.messageId != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (message != null ? !message.equals(that.message) : that.message != null) return false;

        return true;
    }
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int result = messageId != null ? messageId.hashCode() : 0;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "ticket_id", referencedColumnName = "ticket_id")
    public TicketEntity getTicketByTicketId() {
        return ticketByTicketId;
    }

    public void setTicketByTicketId(TicketEntity ticketByTicketId) {
        this.ticketByTicketId = ticketByTicketId;
    }
}
