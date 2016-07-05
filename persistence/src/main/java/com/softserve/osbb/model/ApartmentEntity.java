package com.softserve.osbb.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * Created by cavayman on 05.07.2016.
 */
@Entity
@Table(name = "apartment")
public class ApartmentEntity {
    private Integer apartmentId;
    private Integer number;
    private HouseEntity houseByHouseId;
    private UserEntity userByOwnerId;
    private List<UserEntity> users;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "apartment_id")
    public Integer getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(Integer apartmentId) {
        this.apartmentId = apartmentId;
    }

    @Basic
    @Column(name = "number")
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ApartmentEntity)) return false;

        ApartmentEntity that = (ApartmentEntity) o;

        if (getApartmentId() != null ? !getApartmentId().equals(that.getApartmentId()) : that.getApartmentId() != null)
            return false;
        if (getNumber() != null ? !getNumber().equals(that.getNumber()) : that.getNumber() != null) return false;
        if (getHouseByHouseId() != null ? !getHouseByHouseId().equals(that.getHouseByHouseId()) : that.getHouseByHouseId() != null)
            return false;
        if (getUserByOwnerId() != null ? !getUserByOwnerId().equals(that.getUserByOwnerId()) : that.getUserByOwnerId() != null)
            return false;
        return getUsers() != null ? getUsers().equals(that.getUsers()) : that.getUsers() == null;

    }

    @Override
    public int hashCode() {
        int result = getApartmentId() != null ? getApartmentId().hashCode() : 0;
        result = 31 * result + (getNumber() != null ? getNumber().hashCode() : 0);
        result = 31 * result + (getHouseByHouseId() != null ? getHouseByHouseId().hashCode() : 0);
        result = 31 * result + (getUserByOwnerId() != null ? getUserByOwnerId().hashCode() : 0);
        result = 31 * result + (getUsers() != null ? getUsers().hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "house_id", referencedColumnName = "house_id")
    public HouseEntity getHouseByHouseId() {
        return houseByHouseId;
    }

    public void setHouseByHouseId(HouseEntity houseByHouseId) {
        this.houseByHouseId = houseByHouseId;
    }

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "user_id")
    public UserEntity getUserByOwnerId() {
        return userByOwnerId;
    }

    public void setUserByOwnerId(UserEntity userByOwnerId) {
        this.userByOwnerId = userByOwnerId;
    }

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "apartments")
    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }


}
