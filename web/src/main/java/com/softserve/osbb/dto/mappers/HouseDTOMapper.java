package com.softserve.osbb.dto.mappers;

import com.softserve.osbb.dto.HouseDTO;
import com.softserve.osbb.model.Apartment;
import com.softserve.osbb.model.House;

import java.util.List;

/**
 * Created by nazar.dovhyy on 03.08.2016.
 */
public class HouseDTOMapper {

    public static HouseDTO mapHouseEntityToDTO(House house) {
        HouseDTO houseDTO = null;
        if (house != null) {
            houseDTO = new HouseDTO.HouseDTOBuilder()
                    .setHouseId(house.getHouseId())
                    .setCity(house.getCity())
                    .setDescription(house.getDescription())
                    .setStreet(house.getStreet())
                    .setZipCode(house.getZipCode())
                    .setOsbbName(house.getOsbb())
                    .setApartmentCount((List<Apartment>) house.getApartments())
                    .setNumberOfInhabitants((List<Apartment>) house.getApartments())
                    .build();
        }
        return houseDTO;
    }
}
