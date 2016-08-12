package com.softserve.osbb.dto.mappers;

import com.softserve.osbb.dto.HouseDTO;
import com.softserve.osbb.model.House;

/**
 * Created by nazar.dovhyy on 03.08.2016.
 */
public class HouseDTOMapper {


    public static HouseDTO mapHouseEntityToDTO(House house) {
        HouseDTO houseDTO = new HouseDTO(house);
        houseDTO.setHHData(house.getOsbb());
        return houseDTO;
    }
}
