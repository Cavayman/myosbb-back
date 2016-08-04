package com.softserve.osbb.dto;

import com.softserve.osbb.model.House;
import com.softserve.osbb.model.Osbb;
import com.softserve.osbb.model.User;

/**
 * Created by nazar.dovhyy on 03.08.2016.
 */
public class HouseDTO {

    private Integer houseId;
    private String city;
    private String street;
    private String zipCode;
    private String headFirstName;
    private String headLastName;
    private String phoneNumber;
    private String email;


    public HouseDTO(House house) {
        if (house != null) {
            this.houseId = house.getHouseId();
            this.city = house.getCity();
            this.street = house.getStreet();
            this.zipCode = house.getZipCode();
        }
    }


    public Integer getHouseId() {
        return houseId;
    }


    public void setHHData(Osbb osbb) {
        if (osbb != null) {
            User houseHead = osbb.getCreator();
            this.setHeadFirstName(houseHead.getFirstName());
            this.setHeadLastName(houseHead.getLastName());
            this.setEmail(houseHead.getEmail());
            this.setPhoneNumber(houseHead.getPhoneNumber());
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

    public String getHeadFirstName() {
        return headFirstName;
    }

    public void setHeadFirstName(String headFirstName) {
        this.headFirstName = headFirstName;
    }

    public String getHeadLastName() {
        return headLastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHeadLastName(String headLastName) {
        this.headLastName = headLastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
