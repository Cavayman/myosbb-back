package com.softserve.osbb.model;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vote that = (Vote) o;

        if (voteId != null ? !voteId.equals(that.voteId) : that.voteId != null) return false;
        if (iventId != null ? !iventId.equals(that.iventId) : that.iventId != null) return false;
        if (voteValue != null ? !voteValue.equals(that.voteValue) : that.voteValue != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (eventByEventId != null ? !eventByEventId.equals(that.eventByEventId) : that.eventByEventId != null)
            return false;
        return users != null ? users.equals(that.users) : that.users == null;

    }

    @Override
    public int hashCode() {
        int result = voteId != null ? voteId.hashCode() : 0;
        result = 31 * result + (iventId != null ? iventId.hashCode() : 0);
        result = 31 * result + (voteValue != null ? voteValue.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (eventByEventId != null ? eventByEventId.hashCode() : 0);
        result = 31 * result + (users != null ? users.hashCode() : 0);
        return result;
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
