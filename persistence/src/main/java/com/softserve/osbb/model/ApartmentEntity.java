package com.softserve.osbb.model;

import javax.persistence.*;

/**
 * Created by Oleg on 01.07.2016.
 */
@Entity
@Table(name = "appartment")
public class ApartmentEntity {
    private int appartamentId;
    private Integer houseId;
    private Integer number;
    private Integer ownerId;

    @Id
    @Column(name = "appartament_id")
    public int getAppartamentId() {
        return appartamentId;
    }

    public void setAppartamentId(int appartamentId) {
        this.appartamentId = appartamentId;
    }

    @Basic
    @Column(name = "house_id")
    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    @Basic
    @Column(name = "number")
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Basic
    @Column(name = "owner_id")
    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApartmentEntity that = (ApartmentEntity) o;

        if (appartamentId != that.appartamentId) return false;
        if (houseId != null ? !houseId.equals(that.houseId) : that.houseId != null) return false;
        if (number != null ? !number.equals(that.number) : that.number != null) return false;
        if (ownerId != null ? !ownerId.equals(that.ownerId) : that.ownerId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = appartamentId;
        result = 31 * result + (houseId != null ? houseId.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (ownerId != null ? ownerId.hashCode() : 0);
        return result;
    }
}