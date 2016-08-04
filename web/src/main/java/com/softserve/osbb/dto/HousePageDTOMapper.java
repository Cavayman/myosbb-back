package com.softserve.osbb.dto;

import com.softserve.osbb.model.House;
import com.softserve.osbb.model.Osbb;
import com.softserve.osbb.model.User;

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
            Osbb osbb = house.getOsbb();
            if (osbb != null) {
                User creator = osbb.getCreator();
                housePageDTO.setHead(creator);
            }
        }
        return housePageDTO;
    }
}
