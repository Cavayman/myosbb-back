package com.softserve.osbb.dto;

import com.softserve.osbb.model.Apartment;
import com.softserve.osbb.model.Osbb;

import java.util.List;

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
    private Integer numberOfInhabitants;


    public HouseDTO(HouseDTOBuilder houseDTOBuilder) {
        this.houseId = houseDTOBuilder.houseId;
        this.city = houseDTOBuilder.city;
        this.street = houseDTOBuilder.street;
        this.zipCode = houseDTOBuilder.zipCode;
        this.description = houseDTOBuilder.description;
        this.osbbName = houseDTOBuilder.osbbName;
        this.apartmentCount = houseDTOBuilder.apartmentCount;
        this.numberOfInhabitants = houseDTOBuilder.numberOfInhabitants;
    }

    public Integer getHouseId() {
        return houseId;
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

    public String getOsbbName() {
        return osbbName;
    }

    public Integer getApartmentCount() {
        return apartmentCount;
    }

    public Integer getNumberOfInhabitants() {
        return numberOfInhabitants;
    }

    public static class HouseDTOBuilder {
        private Integer houseId;
        private String city;
        private String street;
        private String zipCode;
        private String description;
        private String osbbName;
        private Integer apartmentCount;
        private Integer numberOfInhabitants;


        public HouseDTOBuilder setHouseId(final Integer houseId) {
            this.houseId = houseId;
            return this;
        }

        public HouseDTOBuilder setCity(final String city) {
            this.city = city;
            return this;
        }

        public HouseDTOBuilder setStreet(final String street) {
            this.street = street;
            return this;
        }

        public HouseDTOBuilder setZipCode(final String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public HouseDTOBuilder setDescription(final String description) {
            this.description = description;
            return this;
        }

        public HouseDTOBuilder setOsbbName(final Osbb osbb) {
            if (osbb != null) {
                this.osbbName = osbb.getName();
            }
            return this;
        }

        public HouseDTOBuilder setApartmentCount(final List<Apartment> apartments) {
            if (apartments != null) {
                this.apartmentCount = apartments.size();
            }
            return this;
        }

        public HouseDTOBuilder setNumberOfInhabitants(List<Apartment> apartments) {
            int inhabitantsCount = 0;
         /*   if (apartments != null) {
                for (Apartment apartment : apartments) {
                    if (apartment.getUser() != null) {
                        inhabitantsCount += 1;
                    }
                }
            }*/
            this.numberOfInhabitants = inhabitantsCount;
            return this;
        }

        public HouseDTO build() {
            return new HouseDTO(this);
        }
    }

    @Override
    public String toString() {
        return "HouseDTO{" +
                "houseId=" + houseId +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", description='" + description + '\'' +
                ", osbbName='" + osbbName + '\'' +
                ", apartmentCount=" + apartmentCount +
                ", numberOfInhabitants=" + numberOfInhabitants +
                '}';
    }
}
