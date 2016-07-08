package com.softserve.osbb.model;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        House that = (House) o;

        if (houseId != null ? !houseId.equals(that.houseId) : that.houseId != null) return false;
        if (adress != null ? !adress.equals(that.adress) : that.adress != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = houseId != null ? houseId.hashCode() : 0;
        result = 31 * result + (adress != null ? adress.hashCode() : 0);
        return result;
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
