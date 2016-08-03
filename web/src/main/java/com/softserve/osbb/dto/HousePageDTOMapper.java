package com.softserve.osbb.dto;

import com.softserve.osbb.model.House;

/**
 * Created by nazar.dovhyy on 03.08.2016.
 */
public class HousePageDTOMapper {


    public static HousePageDTO mapHouseEntityToDTO(House house) {
        HousePageDTO housePageDTO = new HousePageDTO();
        if (house != null) {
            housePageDTO.setHouseId(house.getHouseId());
            housePageDTO.setCity(house.getCity());
            housePageDTO.setStreet(house.getStreet());
            housePageDTO.setZipCode(house.getZipCode());
            housePageDTO.setHead(house.getOsbb().getCreator());
        }
        return housePageDTO;
    }
}
