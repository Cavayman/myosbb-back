package com.softserve.osbb.model;

import com.fasterxml.jackson.annotation.*;
import com.softserve.osbb.model.enums.Periodicity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by nataliia on 05.07.2016.
 */

@Entity
@Table(name = "event")
public class Event {

    private Integer eventId;
    private String title;
    private Timestamp startTime;
    private Timestamp endTime;
    private String description;
    private String author;
    private Osbb osbb;
    private Periodicity repeat;
    private List<Attachment> attachments;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    @JsonProperty(value = "id")
    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "start_time")
    @JsonProperty(value = "start")
    @JsonFormat(pattern = "yyyy-MM-dd'T'hh:mm")
    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "end_time")
    @JsonProperty(value = "end")
    @JsonFormat(pattern = "yyyy-MM-dd'T'hh:mm")
    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
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
    @JsonIgnore
    @JoinColumn(name = "osbb_id", referencedColumnName = "osbb_id")
    public Osbb getOsbb() {
        return osbb;
    }

    public void setOsbb(Osbb osbb) {
        this.osbb = osbb;
    }

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "repeat", columnDefinition = "varchar(45) default 'ONE_TIME'")
    public Periodicity getRepeat() {
        return repeat;
    }

    public void setRepeat(Periodicity periodicity) {
        this.repeat = repeat;
    }

    @OneToMany(fetch = FetchType.LAZY)
    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
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
    public Event clone() {
        Event clone = new Event();
        clone.eventId = this.eventId;
        clone.title = this.title;
        clone.startTime = this.startTime;
        clone.endTime = this.endTime;
        clone.description = this.description;
        clone.author = this.author;
        clone.osbb = this.osbb;
        clone.repeat = this.repeat;
        clone.attachments = this.attachments;
        return clone;
    }
}
