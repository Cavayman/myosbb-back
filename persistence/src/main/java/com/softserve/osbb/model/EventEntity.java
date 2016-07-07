package com.softserve.osbb.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by nkharabaruk on 05.07.2016.
 */
@Entity
@Table(name = "event")
public class EventEntity {

    public enum Repeat {EVERY_DAY, EVERY_WEEK, EVERY_MONTH, EVERY_YEAR, NEVER}

    private Integer eventId;
    private String name;
    private Date date;
    private String description;
    private String author;
    private OsbbEntity osbb;
    private List<VoteEntity> votesByEventId;
    private Repeat repeat;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    @ManyToOne
    @JoinColumn(name = "osbb_id", referencedColumnName = "osbb_id")
    public OsbbEntity getOsbb() {
        return osbb;
    }

    public void setOsbb(OsbbEntity osbb) {
        this.osbb = osbb;
    }

    @OneToMany(mappedBy = "eventByEventId")
    public List<VoteEntity> getVotesByEventId() {
        return votesByEventId;
    }

    public void setVotesByEventId(List<VoteEntity> votesByEventId) {
        this.votesByEventId = votesByEventId;
    }

    @Basic
    @Column(name = "repeat")
    @Enumerated(EnumType.STRING)
    public Repeat getRepeat() {
        return repeat;
    }

    public void setRepeat(Repeat repeat) {
        this.repeat = repeat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventEntity entity = (EventEntity) o;

        if (!eventId.equals(entity.eventId)) return false;
        if (name != null ? !name.equals(entity.name) : entity.name != null) return false;
        if (date != null ? date.getTime() != entity.date.getTime() : entity.date != null) return false;
        if (description != null ? !description.equals(entity.description) : entity.description != null) return false;
        if (author != null ? !author.equals(entity.author) : entity.author != null) return false;
        return repeat == entity.repeat;

    }

    @Override
    public int hashCode() {
        int result = eventId.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (repeat != null ? repeat.hashCode() : 0);
        return result;
    }
}
