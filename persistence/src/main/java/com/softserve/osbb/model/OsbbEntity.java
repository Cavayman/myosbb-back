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
public class OsbbEntity {
    private Integer osbbId;
    private String name;
    private String description;
    private Integer creatorId;
    private Collection<ContractEntity> contractsByOsbbId;
    private Collection<EventEntity> eventsByOsbbId;
    private Collection<HouseEntity> housesByOsbbId;
    private Collection<ReportEntity> reportsByOsbbId;
    private Collection<StaffEntity> staffsByOsbbId;

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
        return "OsbbEntity{" +
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
    public Collection<ContractEntity> getContractsByOsbbId() {
        return contractsByOsbbId;
    }

    public void setContractsByOsbbId(Collection<ContractEntity> contractsByOsbbId) {
        this.contractsByOsbbId = contractsByOsbbId;
    }

    @OneToMany(mappedBy = "osbb")
    public Collection<EventEntity> getEventsByOsbbId() {
        return eventsByOsbbId;
    }

    public void setEventsByOsbbId(Collection<EventEntity> eventsByOsbbId) {
        this.eventsByOsbbId = eventsByOsbbId;
    }

    @OneToMany(mappedBy = "osbbByOsbbId")
    public Collection<HouseEntity> getHousesByOsbbId() {
        return housesByOsbbId;
    }

    public void setHousesByOsbbId(Collection<HouseEntity> housesByOsbbId) {
        this.housesByOsbbId = housesByOsbbId;
    }

    @OneToMany(mappedBy = "osbbByOsbbId")
    public Collection<ReportEntity> getReportsByOsbbId() {
        return reportsByOsbbId;
    }

    public void setReportsByOsbbId(Collection<ReportEntity> reportsByOsbbId) {
        this.reportsByOsbbId = reportsByOsbbId;
    }

    @OneToMany(mappedBy = "osbbByOsbbId")
    public Collection<StaffEntity> getStaffsByOsbbId() {
        return staffsByOsbbId;
    }

    public void setStaffsByOsbbId(Collection<StaffEntity> staffsByOsbbId) {
        this.staffsByOsbbId = staffsByOsbbId;
    }
}
