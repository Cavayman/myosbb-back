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
    private String adress;
    private Collection<Apartment> appartamentsByHouseId;
    private Osbb osbbByOsbbId;

    public House() {
        //default
    }

    public House(String address) {
        this.adress = address;
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
    @Column(name = "adress")
    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @OneToMany(mappedBy = "houseByHouseId", cascade = CascadeType.ALL)
    public Collection<Apartment> getApartmentsByHouseId() {
        return appartamentsByHouseId;
    }

    public void setApartmentsByHouseId(Collection<Apartment> appartamentsByHouseId) {
        this.appartamentsByHouseId = appartamentsByHouseId;
    }

    @ManyToOne
    @JoinColumn(name = "osbb_id", referencedColumnName = "osbb_id")
    public Osbb getOsbbByOsbbId() {
        return osbbByOsbbId;
    }

    public void setOsbbByOsbbId(Osbb osbbByOsbbId) {
        this.osbbByOsbbId = osbbByOsbbId;
    }
}
