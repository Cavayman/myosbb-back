package com.softserve.osbb.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by cavayman on 05.07.2016.
 */
@Entity
@Table(name = "event", schema = "public", catalog = "myosbb")
public class EventEntity {
    private Integer eventId;
    private String name;
    private String description;
    private String author;
    private OsbbEntity osbbByOsbbId;
    private Collection<VoteEntity> votesByEventId;

    @Id
    @Column(name = "event_id")
    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
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
    @Column(name = "author")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventEntity that = (EventEntity) o;

        if (eventId != null ? !eventId.equals(that.eventId) : that.eventId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (author != null ? !author.equals(that.author) : that.author != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = eventId != null ? eventId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "osbb_id", referencedColumnName = "osbb_id")
    public OsbbEntity getOsbbByOsbbId() {
        return osbbByOsbbId;
    }

    public void setOsbbByOsbbId(OsbbEntity osbbByOsbbId) {
        this.osbbByOsbbId = osbbByOsbbId;
    }

    @OneToMany(mappedBy = "eventByEventId")
    public Collection<VoteEntity> getVotesByEventId() {
        return votesByEventId;
    }

    public void setVotesByEventId(Collection<VoteEntity> votesByEventId) {
        this.votesByEventId = votesByEventId;
    }
}
