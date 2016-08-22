package com.softserve.osbb.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Roman on 05.07.2016.
 */
@Entity
@Table(name = "vote")
public class Vote {
    private Integer voteId;
    private String description;
    private Boolean available = true;
    private Timestamp startTime;
    private Timestamp endTime;
    private User user;
    private List<Option> options;

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
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "available", columnDefinition = "boolean default true", nullable = false)
    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    @Basic
    @Column(name = "start_time")
    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "end_time")
    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // @JsonIgnore
    //@JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vote", cascade = CascadeType.ALL)
    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
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
                ", description='" + description + '\'' +
                '}';
    }
}
