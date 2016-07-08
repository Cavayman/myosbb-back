package com.softserve.osbb.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.List;

/**
 * Created by cavayman on 05.07.2016.
 */
@Entity
@Table(name = "apartment")
public class Apartment {
    private Integer apartmentId;
    private Integer number;
    private House houseByHouseId;
    private User userByOwnerId;
    private List<User> users;

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
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @ManyToOne
    @JoinColumn(name = "house_id", referencedColumnName = "house_id")
    public House getHouseByHouseId() {
        return houseByHouseId;
    }

    public void setHouseByHouseId(House houseByHouseId) {
        this.houseByHouseId = houseByHouseId;
    }

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "user_id")
    public User getUserByOwnerId() {
        return userByOwnerId;
    }

    public void setUserByOwnerId(User userByOwnerId) {
        this.userByOwnerId = userByOwnerId;
    }

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "apartments")
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }


}
