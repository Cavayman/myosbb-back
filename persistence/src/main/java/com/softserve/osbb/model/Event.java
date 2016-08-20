package com.softserve.osbb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.softserve.osbb.model.enums.Periodicity;
import com.softserve.osbb.utils.CustomLocalDateDeserializer;
import com.softserve.osbb.utils.CustomLocalDateSerializer;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by nataliia on 05.07.2016.
 */

@Entity
@Table(name = "event")
public class Event {

    private Integer eventId;
    private String name;
    private LocalDate date;
    private String description;
    private String author;
    private Osbb osbb;
    private Periodicity periodicity;
    private List<Attachment> attachments;

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
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    @Column(name = "date")
    public LocalDate getDate() {
        return date;
    }

    @JsonDeserialize(using = CustomLocalDateDeserializer.class)
    public void setDate(LocalDate date) {
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
    @JsonIgnore
    @JoinColumn(name = "osbb_id", referencedColumnName = "osbb_id")
    public Osbb getOsbb() {
        return osbb;
    }

    public void setOsbb(Osbb osbb) {
        this.osbb = osbb;
    }

    @Basic
    @Column(name = "repeat")
    @Enumerated(EnumType.STRING)
    public Periodicity getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(Periodicity periodicity) {
        this.periodicity = periodicity;
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

}
