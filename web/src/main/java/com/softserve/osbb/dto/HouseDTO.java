package com.softserve.osbb.dto;

import com.softserve.osbb.model.House;
import com.softserve.osbb.model.Osbb;

/**
 * Created by nazar.dovhyy on 03.08.2016.
 */
public class HouseDTO {

    private Integer houseId;
    private String city;
    private String street;
    private String zipCode;
    private String description;
    private String osbbName;
    private Integer apartmentCount;


    public HouseDTO(House house) {
        if (house != null) {
            this.houseId = house.getHouseId();
            this.city = house.getCity();
            this.street = house.getStreet();
            this.zipCode = house.getZipCode();
            this.description = house.getDescription();
            this.apartmentCount = house.getApartments().size();
        }
    }


    public Integer getHouseId() {
        return houseId;
    }


    public void setHHData(Osbb osbb) {
        if (osbb != null) {
            this.osbbName = osbb.getName();
        }
    }


    public String getCity() {
        return city;
    }


    public String getStreet() {
        return street;
    }

    public String getZipCode() {
        return zipCode;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOsbbName() {
        return osbbName;
    }

    public void setOsbbName(String osbbName) {
        this.osbbName = osbbName;
    }

    public Integer getApartmentCount() {
        return apartmentCount;
    }

    public void setApartmentCount(Integer apartmentCount) {
        this.apartmentCount = apartmentCount;
    }
}
