package com.softserve.osbb.model;

import javax.persistence.*;

/**
 * Created by cavayman on 05.07.2016.
 */
@Entity
@Table(name = "messege")
public class MassegeEntity {
    private Integer massageId;
    private String time;
    private String massage;
    private String description;
    private TicketEntity ticketByTicketId;
    private UserEntity users;

    @Id
    @Column(name = "massage_id")
    public Integer getMassageId() {
        return massageId;
    }

    public void setMassageId(Integer massageId) {
        this.massageId = massageId;
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
    @Column(name = "massage")
    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public UserEntity getUsers() {
        return users;
    }

    public void setUsers(UserEntity users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MassegeEntity that = (MassegeEntity) o;

        if (massageId != null ? !massageId.equals(that.massageId) : that.massageId != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (massage != null ? !massage.equals(that.massage) : that.massage != null) return false;

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
        int result = massageId != null ? massageId.hashCode() : 0;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (massage != null ? massage.hashCode() : 0);
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
