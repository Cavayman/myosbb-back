package com.softserve.osbb.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by cavayman on 05.07.2016.
 */
@Entity
@Table(name = "house")
public class HouseEntity {
    private Integer houseId;
    private String adress;
    private Collection<ApartmentEntity> appartamentsByHouseId;
    private OsbbEntity osbbByOsbbId;

    @Id @GeneratedValue(strategy =  GenerationType.IDENTITY)
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

        HouseEntity that = (HouseEntity) o;

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

    @OneToMany(mappedBy = "houseByHouseId")
    public Collection<ApartmentEntity> getApartmentsByHouseId() {
        return appartamentsByHouseId;
    }

    public void setApartmentsByHouseId(Collection<ApartmentEntity> appartamentsByHouseId) {
        this.appartamentsByHouseId = appartamentsByHouseId;
    }

    @ManyToOne
    @JoinColumn(name = "osbb_id", referencedColumnName = "osbb_id")
    public OsbbEntity getOsbbByOsbbId() {
        return osbbByOsbbId;
    }

    public void setOsbbByOsbbId(OsbbEntity osbbByOsbbId) {
        this.osbbByOsbbId = osbbByOsbbId;
    }
}
