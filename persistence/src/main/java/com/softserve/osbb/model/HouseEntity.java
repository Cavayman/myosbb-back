package com.softserve.osbb.model;

import javax.persistence.*;

/**
 * Created by nazar.dovhyy on 04.07.2016.
 */
@Entity
@Table(name = "house")
public class HouseEntity {
    private Long houseId;
    private String address;

    protected HouseEntity(){

    }

    public HouseEntity(String address){
        this.address = address;
    }


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "house_id", nullable = false)
    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    @Basic
    @Column(name = "address", nullable = true, length = 100)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HouseEntity houseEntity = (HouseEntity) o;

        if (houseId != null ? !houseId.equals(houseEntity.houseId) : houseEntity.houseId != null) return false;
        if (address != null ? !address.equals(houseEntity.address) : houseEntity.address != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = houseId != null ? houseId.hashCode() : 0;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "HouseEntity{" +
                "houseId=" + houseId +
                ", address='" + address + '\'' +
                '}';
    }
}
