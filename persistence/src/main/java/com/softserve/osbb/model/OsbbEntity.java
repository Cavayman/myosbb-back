package com.softserve.osbb.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by cavayman on 05.07.2016.
 */
@Entity
@Table(name = "osbb", schema = "public", catalog = "myosbb")
public class OsbbEntity {
    private Integer osbbId;
    private String name;
    private String description;
    private Short creatorId;
    private Collection<ContractEntity> contractsByOsbbId;
    private Collection<EventEntity> eventsByOsbbId;
    private Collection<HouseEntity> housesByOsbbId;
    private Collection<ReportEntity> reportsByOsbbId;
    private Collection<StaffEntity> staffsByOsbbId;

    @Id
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
    public Short getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Short creatorId) {
        this.creatorId = creatorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OsbbEntity that = (OsbbEntity) o;

        if (osbbId != null ? !osbbId.equals(that.osbbId) : that.osbbId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (creatorId != null ? !creatorId.equals(that.creatorId) : that.creatorId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = osbbId != null ? osbbId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (creatorId != null ? creatorId.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "osbbByOsbbId")
    public Collection<ContractEntity> getContractsByOsbbId() {
        return contractsByOsbbId;
    }

    public void setContractsByOsbbId(Collection<ContractEntity> contractsByOsbbId) {
        this.contractsByOsbbId = contractsByOsbbId;
    }

    @OneToMany(mappedBy = "osbbByOsbbId")
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
