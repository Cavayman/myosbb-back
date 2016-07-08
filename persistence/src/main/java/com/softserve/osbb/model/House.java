package com.softserve.osbb.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by cavayman on 05.07.2016.
 */
@Entity
@Table(name = "house")
public class House {
    private Integer houseId;
    private String address;
    private Collection<Apartment> apartments;
    private Osbb osbb;

    public House() {
        //default
    }

    public House(String address) {
        this.address = address;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "house_id")
    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL)
    public Collection<Apartment> getApartments() {
        return apartments;
    }

    public void setApartments(Collection<Apartment> appartaments) {
        this.apartments = appartaments;
    }

    @ManyToOne
    @JoinColumn(name = "osbb_id", referencedColumnName = "osbb_id")
    public Osbb getOsbb() {
        return osbb;
    }

    public void setOsbb(Osbb osbb) {
        this.osbb = osbb;
    }
}
