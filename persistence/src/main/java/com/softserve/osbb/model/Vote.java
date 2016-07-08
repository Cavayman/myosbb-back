package com.softserve.osbb.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cavayman on 05.07.2016.
 */
@Entity
@Table(name = "vote")
public class Vote {
    private Integer voteId;
    private Integer iventId;
    private Integer voteValue;
    private Date time;
    private Event eventByEventId;
    private User users;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vote_id")
    public Integer getVoteId() {
        return voteId;
    }

    public void setVoteId(Integer voteId) {
        this.voteId = voteId;
    }

    @Basic
    @Column(name = "ivent_id")
    public Integer getIventId() {
        return iventId;
    }

    public void setIventId(Integer iventId) {
        this.iventId = iventId;
    }

    @Basic
    @Column(name = "vote_value")
    public Integer getVoteValue() {
        return voteValue;
    }

    public void setVoteValue(Integer voteValue) {
        this.voteValue = voteValue;
    }

    @Basic
    @Column(name = "time")
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
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
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return "Vote{" +
                "voteId=" + voteId +
                ", iventId=" + iventId +
                ", voteValue=" + voteValue +
                ", time=" + time +
                ", eventByEventId=" + eventByEventId +
                ", userEntity=" + users +
                '}';
    }

    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "event_id")
    public Event getEventByEventId() {
        return eventByEventId;
    }

    public void setEventByEventId(Event eventByEventId) {
        this.eventByEventId = eventByEventId;
    }
}
