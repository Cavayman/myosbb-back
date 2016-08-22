package com.softserve.osbb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * Created by cavayman on 05.07.2016.
 */
@Entity
@Table(name = "apartment")
public class Apartment {
    private Integer apartmentId;
    private Integer number;
    private House house;
    private Integer square;
    private List<User> users;
    private Integer owner;
    private Collection<Bill> bills;

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

    @Basic
    @Column(name = "square")
    public Integer getSquare() {
        return square;
    }

    public void setSquare(Integer square) {
        this.square = square;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "house_id", referencedColumnName = "house_id")
    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "apartment", cascade = CascadeType.ALL)
    @JsonIgnore
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    @Basic
    @Column(name="owner_id")
    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    @OneToMany(mappedBy = "apartment")
    @JsonIgnore
    public Collection<Bill> getBills() {
        return bills;
    }

    public void setBills(Collection<Bill> bills) {
        this.bills = bills;
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "apartmentId=" + apartmentId +
                ", number=" + number +
                ", square=" + square +
                ", users=" + users +
                ", bills=" + bills +
                '}';
    }
}
