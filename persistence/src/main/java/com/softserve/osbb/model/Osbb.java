package com.softserve.osbb.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by cavayman on 05.07.2016.
 */
@Entity
@Table(name = "osbb")
public class Osbb {
    private Integer osbbId;
    private String name;
    private String description;
    private Integer creatorId;
    private Collection<Contract> contractsByOsbbId;
    private Collection<Event> eventsByOsbbId;
    private Collection<House> housesByOsbbId;
    private Collection<Report> reportsByOsbbId;
    private Collection<Staff> staffsByOsbbId;

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

//                Do not use fields with lazy initialization in toString()
//                ", contractsByOsbbId=" + contractsByOsbbId +
//                ", eventsByOsbbId=" + eventsByOsbbId +
//                ", housesByOsbbId=" + housesByOsbbId +
//                ", reportsByOsbbId=" + reportsByOsbbId +
//                ", staffsByOsbbId=" + staffsByOsbbId +
                '}';
    }

    @OneToMany(mappedBy = "osbbByOsbbId")
    public Collection<Contract> getContractsByOsbbId() {
        return contractsByOsbbId;
    }

    public void setContractsByOsbbId(Collection<Contract> contractsByOsbbId) {
        this.contractsByOsbbId = contractsByOsbbId;
    }

    @OneToMany(mappedBy = "osbb")
    public Collection<Event> getEventsByOsbbId() {
        return eventsByOsbbId;
    }

    public void setEventsByOsbbId(Collection<Event> eventsByOsbbId) {
        this.eventsByOsbbId = eventsByOsbbId;
    }

    @OneToMany(mappedBy = "osbbByOsbbId")
    public Collection<House> getHousesByOsbbId() {
        return housesByOsbbId;
    }

    public void setHousesByOsbbId(Collection<House> housesByOsbbId) {
        this.housesByOsbbId = housesByOsbbId;
    }

    @OneToMany(mappedBy = "osbbByOsbbId")
    public Collection<Report> getReportsByOsbbId() {
        return reportsByOsbbId;
    }

    public void setReportsByOsbbId(Collection<Report> reportsByOsbbId) {
        this.reportsByOsbbId = reportsByOsbbId;
    }

    @OneToMany(mappedBy = "osbbByOsbbId")
    public Collection<Staff> getStaffsByOsbbId() {
        return staffsByOsbbId;
    }

    public void setStaffsByOsbbId(Collection<Staff> staffsByOsbbId) {
        this.staffsByOsbbId = staffsByOsbbId;
    }
}
