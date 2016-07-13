package com.softserve.osbb.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Roman on 05.07.2016.
 */
@Entity
@Table(name = "osbb")
public class Osbb {
    private Integer osbbId;
    private String name;
    private String description;
    private Integer creatorId;
    private Collection<Contract> contracts;
    private Collection<Event> events;
    private Collection<House> houses;
    private Collection<Report> reports;
    private Collection<Staff> staffs;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "osbb_id")
    public Integer getOsbbId() {
        return osbbId;
    }

    public void setOsbbId(Integer osbbId) {
        this.osbbId = osbbId;
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
    @Column(name = "creator_id")
    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
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
        return "Osbb{" +
                "osbbId=" + osbbId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", creatorId=" + creatorId +
                '}';
    }

    @OneToMany(mappedBy = "osbb")
    public Collection<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(Collection<Contract> contracts) {
        this.contracts = contracts;
    }

    @OneToMany(mappedBy = "osbb")
    public Collection<Event> getEvents() {
        return events;
    }

    public void setEvents(Collection<Event> events) {
        this.events = events;
    }

    @OneToMany(mappedBy = "osbb")
    public Collection<House> getHouses() {
        return houses;
    }

    public void setHouses(Collection<House> houses) {
        this.houses = houses;
    }

    @OneToMany(mappedBy = "osbb")
    public Collection<Report> getReports() {
        return reports;
    }

    public void setReports(Collection<Report> reports) {
        this.reports = reports;
    }

    @OneToMany(mappedBy = "osbb")
    public Collection<Staff> getStaffs() {
        return staffs;
    }

    public void setStaffs(Collection<Staff> staffs) {
        this.staffs = staffs;
    }
}
